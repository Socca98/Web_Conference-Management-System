package com.cms.services;


import com.cms.dto.conference.*;
import com.cms.dto.user.UserDto;
import com.cms.exception.IssException;
import com.cms.model.*;
import com.cms.repositories.*;
import com.cms.utils.ConferenceConverter;
import com.cms.utils.UserConverter;
import io.jsonwebtoken.lang.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConferencesService {
    private Logger logger = LoggerFactory.getLogger(ConferencesService.class);

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

    public ConferenceDto createConference(CreateConferenceDto conferenceDto) {
        if (!securityService.isAdmin()) {
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
        Conference conference = conferencesRepository.getOne(conferenceId);
        Submission submission = ConferenceConverter.submissionDtoToSubmission(submissionDto);
        List<User> users = usersService.sendInvitationIfNeeded(submissionDto.getAuthors());
        submission.setAuthors(users);
        submission.setConference(conference);

        // Populate users from field 'likes' with their complete attributes (id, fullName, etc.)
        List<User> usersFullDetailed = new ArrayList<>();
        for(User userEmail : submission.getLikes()) {
            usersFullDetailed.add(usersService.getUserByEmail(userEmail.getEmail()));
        }
        submission.setLikes(usersFullDetailed);

        Submission save = submissionJpaRepository.save(submission);
        conference.getSubmissions().add(save);
        return ConferenceConverter.submissionToSubmissionDto(save);
    }


    public SubmissionDto likeSubmission(String conferenceId, String submissionId) {
        String usernameFromContext = securityService.getUsernameFromContext();
        User user = usersService.getUser(usernameFromContext);

        // Verify role authorization
        Conference conference = conferencesRepository.getOne(conferenceId);
        List<Role> possibleExistingRole = conference.getRoles()
                .stream()
                .filter(role -> role.getUser().getEmail().equals(user.getEmail()))
                .collect(Collectors.toList());
        if (possibleExistingRole.size() == 1) {
            Roles role = possibleExistingRole.get(0).getRole();
            if (role != Roles.PC_MEMBER && role != Roles.CHAIR && role != Roles.CO_CHAIR) {
                throw new IssException("Not permitted for this user!", HttpStatus.BAD_REQUEST);
            }
        }

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

        // Verify role authorization
        Conference conference = conferencesRepository.getOne(conferenceId);
        List<Role> possibleExistingRole = conference.getRoles()
                .stream()
                .filter(role -> role.getUser().getEmail().equals(user.getEmail()))
                .collect(Collectors.toList());
        if (possibleExistingRole.size() == 1) {
            Roles role = possibleExistingRole.get(0).getRole();
            if (role != Roles.PC_MEMBER && role != Roles.CHAIR && role != Roles.CO_CHAIR) {
                throw new IssException("Not permitted for this user!", HttpStatus.BAD_REQUEST);
            }
        }

        // Remove like
        Submission submission = submissionJpaRepository.getOne(submissionId);
        if (submission.getLikes().contains(user)) {
            submission.getLikes().remove(user);
            Submission save = submissionJpaRepository.save(submission);
            return ConferenceConverter.submissionToSubmissionDto(save);
        }

        return ConferenceConverter.submissionToSubmissionDto(submission);
    }

    public ReviewDto addReview(String conferenceId, String submissionId, ReviewDto reviewDto) {
        Conference conference = conferencesRepository.getOne(conferenceId);
        List<Role> possibleExistingRole = conference.getRoles()
                .stream()
                .filter(role -> role.getUser().getEmail().equals(reviewDto.getUser().getEmail()))
                .collect(Collectors.toList());
        if (possibleExistingRole.size() == 1) {
            Roles role = possibleExistingRole.get(0).getRole();
            if (role != Roles.PC_MEMBER && role != Roles.CHAIR && role != Roles.CO_CHAIR) {
                throw new IssException("Not permitted for this user!", HttpStatus.BAD_REQUEST);
            }
        } else {
            UserDto reviewDtoUser = reviewDto.getUser();
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setFullName(reviewDtoUser.getFullName());
            userRoleDto.setRole(Roles.PC_MEMBER);
            userRoleDto.setEmail(reviewDtoUser.getEmail());
            userRoleDto.setAffiliation(reviewDtoUser.getAffiliation());
            User createdUser = usersService.sendInvitationIfNeeded(List.of(userRoleDto)).get(0);
            Role role = new Role();
            role.setConference(conference);
            role.setUser(createdUser);
            role.setRole(Roles.PC_MEMBER);
            conference.getRoles().add(role);
            roleJpaRepository.save(role);
        }
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
        Review review = reviewJpaRepository.getOne(reviewId);
        Submission submission = review.getSubmission();
        submission.getReviews().remove(review);
        review.setSubmission(null);
        submissionJpaRepository.save(submission);
        reviewJpaRepository.deleteById(reviewId);
    }

    public SectionDto createSection(String conferenceId, SectionDto sectionDto) {
        Section section = ConferenceConverter.sectionDtoToSection(sectionDto);

        User sectionChair = usersService.getUserByEmail(sectionDto.getSectionChair().getEmail());
        Conference conference = conferencesRepository.getOne(conferenceId);
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
        List<Submission> submissions = sectionDto.getSubmissions().stream()
                .map(submissionDto -> submissionJpaRepository.getOne(submissionDto.getId()))
                .collect(Collectors.toList());
        section.setSubmissions(submissions);
        Section save = sectionJpaRepository.save(section);

        submissions.stream()
                .peek(submission -> submission.setSection(save))
                .forEach(submission -> submissionJpaRepository.save(submission));
        return ConferenceConverter.sectionToSectionDto(save);
    }

    public SectionDto updateSection(String sectionId, SectionDto sectionDto) {
        Section section = sectionJpaRepository.getOne(sectionId);
        section.setSeats(sectionDto.getSeats());
        section.setTitle(sectionDto.getTitle());
        section.setStartTime(sectionDto.getStartTime());
        section.setEndTime(sectionDto.getEndTime());
        if (Objects.nonNull(sectionDto.getSectionChair().getEmail()) &&
                !section.getSectionChair().getEmail().equals(sectionDto.getSectionChair().getEmail())) {
            User user = usersService.getUserByEmail(section.getSectionChair().getEmail());
            section.setSectionChair(user);
        }
        Section saved = sectionJpaRepository.save(section);
        return ConferenceConverter.sectionToSectionDto(saved);
    }

    public List<SectionDto> getAllSectionsForConference(String conferenceId) {
        List<Section> allByConference = sectionJpaRepository.findAllByConference(conferencesRepository.getOne(conferenceId));
        return ConferenceConverter.sectionToSectionDtoWithSubmissions(allByConference);
    }

    public void removeSection(String sectionId) {
        Section one = sectionJpaRepository.getOne(sectionId);
        one.getSubmissions()
                .forEach(submission -> {
                    submission.setSection(null);
                    submissionJpaRepository.save(submission);
                });

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
        if (!section.getListeners().contains(user) && section.getSeats() > 0) {
            section.getListeners().add(user);
            section.setSeats(section.getSeats() - 1);
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

    public List<ReviewDto> getReviewsForStatus(String submissionId, String status) {
        DirectoryStream.Filter<Verdict> filter;
        switch (status) {
            case "accepted":
                filter = Verdict::accepted;
                break;
            case "rejected":
                filter = Verdict::rejectedOrNotReviewed;
                break;
            default:
                filter = Verdict::all;
                break;
        }
        return submissionJpaRepository.getOne(submissionId)
                .getReviews()
                .stream()
                .filter(review -> {
                    try {
                        return filter.accept(review.getVerdict());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .map(ConferenceConverter::reviewToReviewDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsForConference(String conferenceId, String status) {
        DirectoryStream.Filter<Verdict> filter;
        switch (status) {
            case "accepted":
                filter = Verdict::accepted;
                break;
            case "rejected":
                filter = Verdict::rejectedOrNotReviewed;
                break;
            default:
                filter = Verdict::all;
                break;
        }
        Conference conference = conferencesRepository.getOne(conferenceId);
        String username = securityService.getUsernameFromContext();
        List<Role> probablyRole = conference.getRoles().stream()
                .filter(role -> username.equals(role.getUser().getUsername())).collect(Collectors.toList());
        if (probablyRole.size() == 1 ) {
            List<Review> allReviews = new ArrayList<>();
            conference.getSubmissions()
                    .stream()
                    .map(Submission::getReviews)
                    .forEach(allReviews::addAll);
            return allReviews.stream()
                    .filter(review -> username.equals(review.getUser().getUsername()))
                    .filter(review -> {
                        try {
                            return filter.accept(review.getVerdict());
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .map(review -> {
                        ReviewDto reviewDto = ConferenceConverter.reviewToReviewDto(review);
                        reviewDto.setSubmission(ConferenceConverter.submissionToSubmissionDtoSimple(review.getSubmission()));
                        return reviewDto;
                    })
                    .collect(Collectors.toList());
        }

        if (securityService.isAdmin() || (probablyRole.size() == 1 && probablyRole.get(0).getRole() == Roles.CO_CHAIR)) {
            List<Review> allReviews = new ArrayList<>();
            conference.getSubmissions()
                    .stream()
                    .map(Submission::getReviews)
                    .forEach(allReviews::addAll);
            return allReviews.stream()
                    .filter(review -> {
                        try {
                            return filter.accept(review.getVerdict());
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .map(review -> {
                        ReviewDto reviewDto = ConferenceConverter.reviewToReviewDto(review);
                        reviewDto.setSubmission(ConferenceConverter.submissionToSubmissionDtoSimple(review.getSubmission()));
                        return reviewDto;
                    })
                    .collect(Collectors.toList());
        }

        throw new IssException("You have no right to get this information.", HttpStatus.BAD_REQUEST);
    }

    public List<ReviewDto> getReviewsForOthers(String conferenceId) {
        User user = usersService.getUser(securityService.getUsernameFromContext());
        List<Review> allReviews = new ArrayList<>();
        conferencesRepository.getOne(conferenceId)
                .getSubmissions()
                .stream()
                .map(Submission::getReviews)
                .forEach(allReviews::addAll);
        List<Review> others = new ArrayList<>();
        allReviews.stream()
                .filter(review -> review.getUser().getEmail().equals(user.getEmail()))
                .filter(review -> Verdict.acceptedOrRejected(review.getVerdict()))
                .map(Review::getSubmission)
                .map(Submission::getReviews)
                .peek(reviews -> reviews.removeIf(review -> review.getUser().getEmail().equals(user.getEmail())))
                .forEach(others::addAll);
        return others.stream()
                .map(review -> {
                    ReviewDto reviewDto = ConferenceConverter.reviewToReviewDto(review);
                    reviewDto.setSubmission(ConferenceConverter.submissionToSubmissionDtoSimple(review.getSubmission()));
                    return reviewDto;
                })
                .collect(Collectors.toList());
    }

    public SubmissionDto getSubmission(String submissionId) {
        return ConferenceConverter.submissionToSubmissionDto(submissionJpaRepository.getOne(submissionId));
    }

    public SubmissionDto updateFinalVerdict(String submissionId, String verdict) {
        Submission submission = submissionJpaRepository.getOne(submissionId);
        submission.setFinalVerdict(verdict);
        return ConferenceConverter.submissionToSubmissionDto(submissionJpaRepository.save(submission));

    }

    public SectionDto getSection(String sectionId) {
        return ConferenceConverter.sectionToSectionDtoWithSubmissions(sectionJpaRepository.getOne(sectionId));
    }

//    @Scheduled(initialDelay = 10000, fixedDelay = 30000)
//    @Transactional
//    public void updateFinalVerdict() {
//        logger.info("Updating final verdicts");
//        long time = Calendar.getInstance().getTime().getTime() / 1000;
//        submissionJpaRepository.findAll()
//                .stream()
//                .filter(submission -> Objects.isNull(submission.getFinalVerdict()))
//                .filter(submission -> submission.getConference().getEvaluationDeadline() < time)
//                .filter(submission -> submission.getReviews().stream().map(review -> Verdict.rejectedOrNotReviewed(review.getVerdict())).count() > 0)
//                .forEach(submission -> {
//                    logger.info("Rejecting submission=[{}]", submission.getSubmissionId());
//
//                    submission.setFinalVerdict(Verdict.REJECT.toString());
//                    submissionJpaRepository.save(submission);
//                });
//        submissionJpaRepository.findAll()
//                .stream()
//                .filter(submission -> Objects.isNull(submission.getFinalVerdict()))
//                .filter(submission -> submission.getConference().getNrOfReviews() >= submission.getReviews().size())
//                .filter(submission -> submission.getConference().getEvaluationDeadline() < time)
//                .filter(submission -> submission.getReviews().stream().map(review -> Verdict.rejectedOrNotReviewed(review.getVerdict())).count() == 0)
//                .forEach(submission -> {
//                    logger.info("Accepting submission=[{}]", submission.getSubmissionId());
//                    submission.setFinalVerdict(Verdict.ACCEPT.toString());
//                    submissionJpaRepository.save(submission);
//                });
//    }
}
