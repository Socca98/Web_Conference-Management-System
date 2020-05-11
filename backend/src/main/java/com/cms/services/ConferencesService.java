package com.cms.services;


import com.cms.dto.conference.ConferenceDto;
import com.cms.dto.conference.CreateConferenceDto;
import com.cms.dto.conference.SubmissionDto;
import com.cms.dto.conference.UserRoleDto;
import com.cms.dto.user.UserDto;
import com.cms.exception.IssException;
import com.cms.model.Conference;
import com.cms.model.Role;
import com.cms.model.Submission;
import com.cms.model.User;
import com.cms.repositories.ConferenceJpaRepository;
import com.cms.repositories.RoleJpaRepository;
import com.cms.repositories.SubmissionJpaRepository;
import com.cms.utils.ConferenceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Map;
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
    private UsersService usersService;

    @Autowired
    private SecurityService securityService;

    @Value("${predefined.admin.username}")
    private String adminUsername;

    public ConferenceDto createConference(CreateConferenceDto conferenceDto) {
        if ( ! securityService.getUsernameFromContext().equals(adminUsername)) {
            throw new IssException("This operation is only permitted for the Chair!", HttpStatus.BAD_REQUEST);
        }
        Conference savedConference = conferencesRepository.save(ConferenceConverter.conferenceDtoToConference(conferenceDto));
        List<User> users = usersService.sendInvitationIfNeeded(conferenceDto.getUsers());
        Map<String, UserRoleDto> map = conferenceDto.getUsers().stream().collect(Collectors.toMap(UserRoleDto::getEmail, user -> user));
        List<Role> rolesToSave = users.stream().map(user -> {
            Role role = new Role();
            role.setConference(savedConference);
            role.setUser(user);
            role.setRole(map.get(user.getEmail()).getRole());
            return role;
        }).collect(Collectors.toList());
        for (Role role : rolesToSave) {
            roleJpaRepository.save(role);
        }
        return ConferenceConverter.conferenceToConferenceDto(savedConference);
    }

    public List<ConferenceDto> getAllConferences() {
        List<Conference> conferenceList = conferencesRepository.findAll();
        return conferenceList.stream()
                .map(ConferenceConverter::conferenceToConferenceDto)
                .collect(Collectors.toList());
    }

    public SubmissionDto addSubmission(String conferenceId, SubmissionDto submissionDto) {

        Conference conference = new Conference();
        conference.setConferenceId(conferenceId);
        Submission submission = ConferenceConverter.submissionDtoToSubmission(submissionDto);
        List<User> users = usersService.sendInvitationIfNeeded(submissionDto.getAuthors());

        submission.setConference(conference);
        Submission save = submissionJpaRepository.save(submission);
        return ConferenceConverter.submissionToSubmissionDto(save);
    }


    public SubmissionDto likeSubmission(String conferenceId, String submissionId) {
        String usernameFromContext = securityService.getUsernameFromContext();
        User user = usersService.getUser(usernameFromContext);
        Submission submission = submissionJpaRepository.getOne(submissionId);
        if (! submission.getLikes().contains(user)) {
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
}
