package com.cms.services;


import com.cms.controllers.UsersController;
import com.cms.dto.conference.*;
import com.cms.dto.user.RegisterUserDto;
import com.cms.dto.user.UserDto;
import com.cms.exception.IssException;
import com.cms.model.*;
import com.cms.repositories.*;
import com.cms.utils.ConferenceConverter;
import com.cms.utils.UserConverter;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class ConferencesService {

    @Autowired
    private ConferenceJpaRepository conferencesRepository;

    @Autowired
    private SubmissionJpaRepository submissionJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private ReviewJpaRepository reviewJpaRepository;

    @Autowired
    private SectionJpaRepository sectionJpaRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private SecurityService securityService;

    @Value("${predefined.admin.username}")
    private String adminUsername;

    public ConferenceDto createConference(CreateConferenceDto conferenceDto) {
        if (!securityService.getUsernameFromContext().equals(adminUsername)) {
            throw new IssException("This operation is only permitted for the Chair!", HttpStatus.BAD_REQUEST);
        }
        Conference savedConference = conferencesRepository.save(ConferenceConverter.conferenceDtoToConference(conferenceDto));
        addUsersToConference(savedConference.getConferenceId(), conferenceDto.getUsers());
        return ConferenceConverter.conferenceToConferenceDto(savedConference);
    }

    public List<ConferenceDto> getAllConferences() {
        List<Conference> conferenceList = conferencesRepository.findAll();
        return conferenceList.stream()
                .map(ConferenceConverter::conferenceToConferenceDto)
                .map(conferenceDto -> conferenceDto.submissions(null))
                .collect(Collectors.toList());
    }

    public void addUsersToConference(String conferenceId, List<UserRoleDto> userRoleDtos) {
        List<User> users = usersService.sendInvitationIfNeeded(userRoleDtos);
        Conference conference = conferencesRepository.getOne(conferenceId);
        Map<String, UserRoleDto> map = userRoleDtos.stream().collect(Collectors.toMap(UserRoleDto::getEmail, user -> user));
        List<Role> rolesToSave = users.stream().map(user -> {
            Role role = new Role();
            role.setConference(conference);
            role.setUser(user);
            role.setRole(map.get(user.getEmail()).getRole());
            return role;
        }).collect(Collectors.toList());
        for (Role role : rolesToSave) {
            roleJpaRepository.save(role);
        }
        if (Objects.nonNull(conference.getRoles())) {
            conference.getRoles().addAll(rolesToSave);
        } else {
            conference.setRoles(rolesToSave);
        }
        conferencesRepository.save(conference);
    }

    public ConferenceDto getConference(String conferenceId) {
        Optional<Conference> conferenceOptional = conferencesRepository.findById(conferenceId);
        if (conferenceOptional.isEmpty()) {
            throw new IssException("Not found!", HttpStatus.NOT_FOUND);
        }
        String username = securityService.getUsernameFromContext();
        Conference conference = conferenceOptional.get();
        List<Role> hasRoleToConference = conference.getRoles()
                .stream()
                .filter(role -> Objects.equals(role.getUser().getUsername(), username))
                .filter(role -> Objects.nonNull(role.getUser().getUsername()))
                .collect(Collectors.toList());
        if (Collections.isEmpty(hasRoleToConference) && !securityService.isAdmin()) {
            throw new IssException("Unauthorized!", HttpStatus.UNAUTHORIZED);
        }
        ConferenceDto conferenceDto = ConferenceConverter.conferenceToConferenceDto(conference);
        conferenceDto.setUsers(UserConverter.roleToUserRoleDto(conference.getRoles()));
        return conferenceDto;

    }

    public SubmissionDto addSubmission(String conferenceId, SubmissionDto submissionDto) {

        Conference conference = new Conference();
        conference.setConferenceId(conferenceId);
        Submission submission = ConferenceConverter.submissionDtoToSubmission(submissionDto);
        List<User> users = usersService.sendInvitationIfNeeded(submissionDto.getAuthors());
        submission.setAuthors(users);

        submission.setConference(conference);
        Submission save = submissionJpaRepository.save(submission);
        return ConferenceConverter.submissionToSubmissionDto(save);
    }


    public SubmissionDto likeSubmission(String conferenceId, String submissionId) {
        String usernameFromContext = securityService.getUsernameFromContext();
        User user = usersService.getUser(usernameFromContext);
        Submission submission = submissionJpaRepository.getOne(submissionId);
        if (!submission.getLikes().contains(user)) {
            submission.getLikes().add(user);
            Submission save = submissionJpaRepository.save(submission);
            return ConferenceConverter.submissionToSubmissionDto(save);
        }

        return ConferenceConverter.submissionToSubmissionDto(submission);
    }

    public SubmissionDto unlikeSubmission(String conferenceId, String submissionId) {
        String usernameFromContext = securityService.getUsernameFromContext();
        User user = usersService.getUser(usernameFromContext);
        Submission submission = submissionJpaRepository.getOne(submissionId);
        if (submission.getLikes().contains(user)) {
            submission.getLikes().remove(user);
            Submission save = submissionJpaRepository.save(submission);
            return ConferenceConverter.submissionToSubmissionDto(save);
        }

        return ConferenceConverter.submissionToSubmissionDto(submission);
    }

    public ReviewDto addReview(String conferenceId, String submissionId, ReviewDto reviewDto) {
        Submission submission = submissionJpaRepository.getOne(submissionId);
        User user = usersService.getUserByEmail(reviewDto.getUser().getEmail());
        Review review = ConferenceConverter.reviewDtoToReview(reviewDto);
        review.setSubmission(submission);
        review.setUser(user);
        reviewJpaRepository.save(review);
        submission.getReviews().add(review);
        submissionJpaRepository.save(submission);
        return ConferenceConverter.reviewToReviewDto(review);
    }

    public List<ReviewDto> addReview(String conferenceId, String submissionId, List<ReviewDto> reviewDtos) {
        return reviewDtos.stream().map(reviewDto -> addReview(conferenceId, submissionId, reviewDto)).collect(Collectors.toList());
    }

    public ReviewDto updateReview(String reviewId, ReviewDto reviewDto) {
        Review review = reviewJpaRepository.getOne(reviewId);
        if (Objects.nonNull(reviewDto.getRecommendation())) {
            review.setRecommendation(reviewDto.getRecommendation());
        }
        if (Objects.nonNull(reviewDto.getVerdict())) {
            review.setVerdict(reviewDto.getVerdict());
        }
        return ConferenceConverter.reviewToReviewDto(reviewJpaRepository.save(review));
    }

    public void removeReview(String reviewId) {
        reviewJpaRepository.deleteById(reviewId);
    }

    public SectionDto createSection(String conferenceId, String submissionId, SectionDto sectionDto) {
        Section section = ConferenceConverter.sectionDtoToSection(sectionDto);

        User sectionChair = usersService.getUserByEmail(sectionDto.getSectionChair().getEmail());
        Conference conference = conferencesRepository.getOne(conferenceId);
        Submission submission = submissionJpaRepository.getOne(submissionId);
        if (Objects.nonNull(sectionDto.getListeners())) {
            List<User> listeners = sectionDto.getListeners().stream()
                    .map(userDto -> usersService.getUserByEmail(userDto.getEmail()))
                    .collect(Collectors.toList());
            section.setListeners(listeners);
        }
        if (Objects.nonNull(sectionDto.getSpeakers())) {

            List<User> speakers = sectionDto.getSpeakers().stream()
                    .map(userDto -> usersService.getUserByEmail(userDto.getEmail()))
                    .collect(Collectors.toList());
            section.setSpeakers(speakers);
        }
        section.setSectionChair(sectionChair);
        section.setConference(conference);
        section.setSubmission(submission);
        Section save = sectionJpaRepository.save(section);
        submission.setSection(save);
        submissionJpaRepository.save(submission);
        return ConferenceConverter.sectionToSectionDto(save);
    }

    public SectionDto updateSection(String sectionId, SectionDto sectionDto) {
        Section section = sectionJpaRepository.getOne(sectionId);
        section.setSeats(sectionDto.getSeats());
        section.setTitle(sectionDto.getTitle());
        section.setStartTime(sectionDto.getStartTime());
        section.setEndTime(sectionDto.getEndTime());
        if(Objects.nonNull(sectionDto.getSectionChair().getEmail()) &&
                !section.getSectionChair().getEmail().equals(sectionDto.getSectionChair().getEmail())) {
            User user = usersService.getUserByEmail(section.getSectionChair().getEmail());
            section.setSectionChair(user);
        }
        Section saved = sectionJpaRepository.save(section);
        return ConferenceConverter.sectionToSectionDto(saved);
    }

    public List<SectionDto> getAllSectionsForConference(String conferenceId) {
        List<Section> allByConference = sectionJpaRepository.findAllByConference(conferencesRepository.getOne(conferenceId));
        return ConferenceConverter.sectionToSectionDto(allByConference);
    }

    public void removeSection(String sectionId) {
        Section one = sectionJpaRepository.getOne(sectionId);
        Submission submission = one.getSubmission();
        submission.setSection(null);
        submissionJpaRepository.save(submission);

        sectionJpaRepository.deleteById(sectionId);
    }

    public SubmissionDto updateSubmission(String submissionId, SubmissionDto submissionDto) {
        Submission submission = submissionJpaRepository.getOne(submissionId);
        submission.setAbstractPaper(submissionDto.getAbstractPaper());
        submission.setFullPaper(submissionDto.getFullPaper());
        submission.setKeywords(submissionDto.getKeywords());
        submission.setTopics(submissionDto.getTopics());
        submission.setTitle(submissionDto.getTitle());
        Submission save = submissionJpaRepository.save(submission);
        return ConferenceConverter.submissionToSubmissionDto(save);
    }

    public List<SubmissionDto> getSubmissionsForConference(String conferenceId) {

        List<Submission> submissions = conferencesRepository.getOne(conferenceId).getSubmissions();
        return ConferenceConverter.submissionToSubmissionDto(submissions);
    }

    public ConferenceDto updateConference(String conferenceId, ConferenceDto conferenceDto) {
        Conference conference = conferencesRepository.getOne(conferenceId);
        conference.setProposalDeadline(conferenceDto.getProposalDeadline());
        conference.setAbstractDeadline(conferenceDto.getAbstractDeadline());
        Conference save = conferencesRepository.save(conference);
        return ConferenceConverter.conferenceToConferenceDto(save);
    }

    public void attendSection(String sectionId) {
        Section section = sectionJpaRepository.getOne(sectionId);
        User user = usersService.getUser(securityService.getUsernameFromContext());
        if (!section.getListeners().contains(user) && section.getListeners().size() < section.getSeats()) {
            section.getListeners().add(user);
            sectionJpaRepository.save(section);
            return;
        }
        throw new IssException("Already attending section or it is full!", HttpStatus.BAD_REQUEST);
    }

    public void unattendSection(String sectionId) {
        Section section = sectionJpaRepository.getOne(sectionId);
        User user = usersService.getUser(securityService.getUsernameFromContext());
        if (section.getListeners().contains(user)) {
            section.getListeners().remove(user);
            sectionJpaRepository.save(section);
        }
    }

}
