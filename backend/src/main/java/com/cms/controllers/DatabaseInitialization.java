package com.cms.controllers;

import com.cms.dto.conference.*;
import com.cms.dto.user.RegisterUserDto;
import com.cms.dto.user.UserDto;
import com.cms.model.Roles;
import com.cms.model.Verdict;
import com.cms.services.ConferencesService;
import com.cms.services.InitializationService;
import com.cms.services.SecurityService;
import com.cms.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/init-database")
public class DatabaseInitialization {

    @Autowired
    private ConferencesService conferencesService;
    @Autowired
    private InitializationService initializationService;

    @GetMapping
    public ResponseEntity<Void> init() {
        SecurityService.INITIALIZATION = true;
        createUsers();
        createConferences();
        createSubmission();
        createReviews();
        SecurityService.INITIALIZATION = false;
        return ResponseEntity.ok().build();
    }

    private void createUsers() {
        RegisterUserDto registerUserDto = new RegisterUserDto();

        registerUserDto.setFullName("The Admin");
        registerUserDto.setUsername("admin");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("admin@gmail.com");
        registerUserDto.setWebpage("companyadmin.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 0");
        registerUserDto.setUsername("init_user_0");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user0@gmail.com");
        registerUserDto.setWebpage("user0.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 1");
        registerUserDto.setUsername("init_user_1");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user1@gmail.com");
        registerUserDto.setWebpage("user1.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 2");
        registerUserDto.setUsername("init_user_2");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user2@gmail.com");
        registerUserDto.setWebpage("user2.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 3");
        registerUserDto.setUsername("init_user_3");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user3@gmail.com");
        registerUserDto.setWebpage("user3.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 4");
        registerUserDto.setUsername("init_user_4");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user4@gmail.com");
        registerUserDto.setWebpage("user4.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 5");
        registerUserDto.setUsername("init_user_5");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user5@gmail.com");
        registerUserDto.setWebpage("user5.com");
        initializationService.register(registerUserDto);
    }

    private void createConferences() {
        CreateConferenceDto conferenceDto = new CreateConferenceDto();
        conferenceDto.setName("Conference 0");
        conferenceDto.setStartDate(1588326245);
        conferenceDto.setEndDate(1588327000);
        conferenceDto.setAbstractDeadline(1588326500);
        conferenceDto.setProposalDeadline(1588326400);
        conferenceDto.setBiddingDeadline(1588326300);
        conferenceDto.setEvaluationDeadline(1588326800);
        conferenceDto.setAllowFullPaper(false);
        conferenceDto.setTaxFee(200);
        conferenceDto.setNrOfReviews(2);

        conferenceDto.setWebsite("conference1.com");
        UserRoleDto userRoleDto;
        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user0@gmail.com");
        userRoleDto.setRole(Roles.CO_CHAIR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user1@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user2@gmail.com");
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user3@gmail.com");
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user4@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        conferenceDto.setUsers(userRoleDtos);

        conferencesService.createConference(conferenceDto);
        conferenceDto = new CreateConferenceDto();
        conferenceDto.setName("Conference 1");
        conferenceDto.setStartDate(1588329245);
        conferenceDto.setEndDate(1588329000);
        conferenceDto.setAbstractDeadline(1588329500);
        conferenceDto.setProposalDeadline(1588329400);
        conferenceDto.setBiddingDeadline(1588329300);
        conferenceDto.setEvaluationDeadline(1588329800);
        conferenceDto.setAllowFullPaper(false);
        conferenceDto.setTaxFee(200);
        conferenceDto.setNrOfReviews(2);

        userRoleDtos = new ArrayList<>();
        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user0@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user1@gmail.com");
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user2@gmail.com");
        userRoleDto.setRole(Roles.CO_CHAIR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user3@gmail.com");
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user4@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user5@gmail.com");
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        conferenceDto.setUsers(userRoleDtos);
        conferencesService.createConference(conferenceDto);
    }


    private void createSubmission() {
        List<ConferenceDto> allConferences = conferencesService.getAllConferences();

        String firstConferenceId = allConferences.get(0).getId();
        String secondConferenceId = allConferences.get(1).getId();

        UserRoleDto userRole0 = new UserRoleDto();
        userRole0.setEmail("user0@gmail.com");

        UserRoleDto userRole1 = new UserRoleDto();
        userRole1.setEmail("user1@gmail.com");

        UserRoleDto userRole4 = new UserRoleDto();
        userRole4.setEmail("user4@gmail.com");

        SubmissionDto submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 0 of conference 0");
        submissionDto.setKeywords("first, number one, boss");
        submissionDto.setTopics("nothing really");

        List<UserRoleDto> authors = new ArrayList<>();
        authors.add(userRole1);
        authors.add(userRole4);
        submissionDto.setAuthors(authors);

        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 1 of conference 0");
        submissionDto.setKeywords("second, number two, slave");
        submissionDto.setTopics("nothing really but with more sour");

        authors = new ArrayList<>();
        authors.add(userRole4);
        submissionDto.setAuthors(authors);

        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 0 of conference 1");
        submissionDto.setKeywords("first of second, barosan");
        submissionDto.setTopics("nothing again");

        authors = new ArrayList<>();
        authors.add(userRole0);
        submissionDto.setAuthors(authors);

        conferencesService.addSubmission(secondConferenceId, submissionDto);
    }

    private void createReviews() {
        UserDto userDto1 = new UserDto();
        userDto1.setEmail("user1@gmail.com");
        UserDto userDto2 = new UserDto();
        userDto2.setEmail("user2@gmail.com");
        UserDto userDto3 = new UserDto();
        userDto3.setEmail("user3@gmail.com");
        UserDto userDto5 = new UserDto();
        userDto5.setEmail("user5@gmail.com");

        List<ConferenceDto> allConferences = conferencesService.getAllConferences();

        String firstConferenceId = allConferences.get(0).getId();
        String secondConferenceId = allConferences.get(1).getId();

        List<SubmissionDto> submissionsForFirstConference = conferencesService.getSubmissionsForConference(firstConferenceId);
        List<SubmissionDto> submissionsForSecondConference = conferencesService.getSubmissionsForConference(secondConferenceId);
        ReviewDto reviewDto;

        reviewDto = new ReviewDto();
        reviewDto.setVerdict(Verdict.WEAK_ACCEPT);
        reviewDto.setUser(userDto2);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(0).getId(), reviewDto);
        reviewDto.setVerdict(Verdict.ACCEPT);
        reviewDto.setUser(userDto3);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(0).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.NOT_REVIEWED);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(1).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.STRONG_ACCEPT);
        reviewDto.setUser(userDto1);
        conferencesService.addReview(secondConferenceId, submissionsForSecondConference.get(0).getId(), reviewDto);
        reviewDto.setVerdict(Verdict.NOT_REVIEWED);
        reviewDto.setUser(userDto3);
        conferencesService.addReview(secondConferenceId, submissionsForSecondConference.get(0).getId(), reviewDto);
        reviewDto.setVerdict(Verdict.ACCEPT);
        reviewDto.setUser(userDto5);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(0).getId(), reviewDto);
    }



}
