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
        createSections();
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

        registerUserDto.setFullName("Init User 6");
        registerUserDto.setUsername("init_user_6");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user6@gmail.com");
        registerUserDto.setWebpage("user6.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 7");
        registerUserDto.setUsername("init_user_7");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user7@gmail.com");
        registerUserDto.setWebpage("user7.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 8");
        registerUserDto.setUsername("init_user_8");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user8@gmail.com");
        registerUserDto.setWebpage("user8.com");
        initializationService.register(registerUserDto);

        registerUserDto.setFullName("Init User 9");
        registerUserDto.setUsername("init_user_9");
        registerUserDto.setPassword("root");
        registerUserDto.setAffiliation("UBB");
        registerUserDto.setEmail("user9@gmail.com");
        registerUserDto.setWebpage("user9.com");
        initializationService.register(registerUserDto);
    }

    private void createConferences() {
        CreateConferenceDto conferenceDto = new CreateConferenceDto();
        conferenceDto.setName("Conference 0");
        conferenceDto.setStartDate(1590692433);
        conferenceDto.setEndDate(1603911633);
        conferenceDto.setAbstractDeadline(1595962833);
        conferenceDto.setProposalDeadline(1593370833);
        conferenceDto.setBiddingDeadline(1598641233);
        conferenceDto.setEvaluationDeadline(1601319633);
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
        userRoleDto.setRole(Roles.CO_CHAIR);
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
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user5@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user6@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user7@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user8@gmail.com");
        userRoleDto.setRole(Roles.STEERING_COMMITTEE_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user9@gmail.com");
        userRoleDto.setRole(Roles.STEERING_COMMITTEE_MEMBER);
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
        userRoleDto.setRole(Roles.STEERING_COMMITTEE_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user1@gmail.com");
        userRoleDto.setRole(Roles.STEERING_COMMITTEE_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user2@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user3@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user4@gmail.com");
        userRoleDto.setRole(Roles.AUTHOR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user5@gmail.com");
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user6@gmail.com");
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user7@gmail.com");
        userRoleDto.setRole(Roles.PC_MEMBER);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user8@gmail.com");
        userRoleDto.setRole(Roles.CO_CHAIR);
        userRoleDtos.add(userRoleDto);

        userRoleDto = new UserRoleDto();
        userRoleDto.setEmail("user9@gmail.com");
        userRoleDto.setRole(Roles.CO_CHAIR);
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
        UserRoleDto userRole2 = new UserRoleDto();
        userRole2.setEmail("user2@gmail.com");
        UserRoleDto userRole3 = new UserRoleDto();
        userRole3.setEmail("user3@gmail.com");
        UserRoleDto userRole4 = new UserRoleDto();
        userRole4.setEmail("user4@gmail.com");
        UserRoleDto userRole5 = new UserRoleDto();
        userRole5.setEmail("user5@gmail.com");
        UserRoleDto userRole6 = new UserRoleDto();
        userRole6.setEmail("user6@gmail.com");
        UserRoleDto userRole7 = new UserRoleDto();
        userRole7.setEmail("user7@gmail.com");
        UserRoleDto userRole8 = new UserRoleDto();
        userRole8.setEmail("user8@gmail.com");
        UserRoleDto userRole9 = new UserRoleDto();
        userRole9.setEmail("user9@gmail.com");

        // UserDto for reuse
        UserDto userDto0 = new UserDto();
        userDto0.setEmail("user0@gmail.com");
        UserDto userDto1 = new UserDto();
        userDto1.setEmail("user1@gmail.com");
        UserDto userDto2 = new UserDto();
        userDto2.setEmail("user2@gmail.com");
        UserDto userDto3 = new UserDto();
        userDto3.setEmail("user3@gmail.com");
        UserDto userDto4 = new UserDto();
        userDto4.setEmail("user4@gmail.com");
        UserDto userDto5 = new UserDto();
        userDto5.setEmail("user5@gmail.com");
        UserDto userDto6 = new UserDto();
        userDto6.setEmail("user6@gmail.com");
        UserDto userDto7 = new UserDto();
        userDto7.setEmail("user7@gmail.com");
        UserDto userDto8 = new UserDto();
        userDto8.setEmail("user8@gmail.com");
        UserDto userDto9 = new UserDto();
        userDto9.setEmail("user9@gmail.com");

        SubmissionDto submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 0 of conference 0");
        submissionDto.setKeywords("first, number one, love");
        submissionDto.setTopics("nothing really");
        List<UserRoleDto> authors = new ArrayList<>();  // authors list
        authors.add(userRole2);
        authors.add(userRole3);
        authors.add(userRole5);
        authors.add(userRole6);
        submissionDto.setAuthors(authors);
        List<UserDto> likes = new ArrayList<>();  // likes list - UserDto has only email, setLikes is needed in service
        likes.add(userDto1);
        likes.add(userDto2);
        likes.add(userDto3);
        likes.add(userDto4);
        submissionDto.setLikes(likes);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 1 of conference 0");
        submissionDto.setKeywords("second, number two, passion");
        submissionDto.setTopics("nothing really but with more sour");
        authors = new ArrayList<>();
        authors.add(userRole2);
        authors.add(userRole6);
        authors.add(userRole7);
        submissionDto.setAuthors(authors);
        likes = new ArrayList<>();  // likes list
        likes.add(userDto2);
        likes.add(userDto1);
        likes.add(userDto0);
        submissionDto.setLikes(likes);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 2 of conference 0");
        submissionDto.setKeywords("aa, aaa, aa, a");
        submissionDto.setTopics("AA, AAA, AAAA");
        authors = new ArrayList<>();
        authors.add(userRole7);
        submissionDto.setAuthors(authors);
        likes = new ArrayList<>();  // likes list
        likes.add(userDto2);
        likes.add(userDto4);
        likes.add(userDto0);
        submissionDto.setLikes(likes);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 3 of conference 0");
        submissionDto.setKeywords("bb, bbb, bbbb, b, bb");
        submissionDto.setTopics("BB, BB, bb, BBB");
        authors = new ArrayList<>();
        authors.add(userRole6);
        authors.add(userRole7);
        submissionDto.setAuthors(authors);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 4 of conference 0");
        submissionDto.setKeywords("cc, c, c, ccc, cc");
        submissionDto.setTopics("CC, CCC, C, C");
        authors = new ArrayList<>();
        authors.add(userRole5);
        authors.add(userRole7);
        submissionDto.setAuthors(authors);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 5 of conference 0");
        submissionDto.setFinalVerdict("STRONG_ACCEPT");
        submissionDto.setKeywords("dd, ddd, dd, d");
        submissionDto.setTopics("DDD, DDD, D, DD");
        authors = new ArrayList<>();
        authors.add(userRole7);
        submissionDto.setAuthors(authors);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 6 of conference 0");
        submissionDto.setFinalVerdict("ACCEPT");
        submissionDto.setKeywords("third, number two, compassion");
        submissionDto.setTopics("more topics");
        authors = new ArrayList<>();
        authors.add(userRole5);
        submissionDto.setAuthors(authors);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 7 of conference 0");
        submissionDto.setFinalVerdict("WEAK_ACCEPT");
        submissionDto.setKeywords("ff, f, f, ff");
        submissionDto.setTopics("FFF, FF, F");
        authors = new ArrayList<>();
        authors.add(userRole6);
        submissionDto.setAuthors(authors);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        submissionDto = new SubmissionDto();
        submissionDto.setTitle("Submission 8 of conference 0");
        submissionDto.setFinalVerdict("STRONG_REJECT");
        submissionDto.setKeywords("gg, g, ggg, gggg");
        submissionDto.setTopics("GG, G, GGG");
        authors = new ArrayList<>();
        authors.add(userRole6);
        submissionDto.setAuthors(authors);
        conferencesService.addSubmission(firstConferenceId, submissionDto);

        // Second conference
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
        UserDto userDto4 = new UserDto();
        userDto4.setEmail("user4@gmail.com");
        UserDto userDto5 = new UserDto();
        userDto5.setEmail("user5@gmail.com");
        UserDto userDto6 = new UserDto();
        userDto6.setEmail("user6@gmail.com");
        UserDto userDto7 = new UserDto();
        userDto7.setEmail("user7@gmail.com");
        UserDto userDto8 = new UserDto();
        userDto8.setEmail("user8@gmail.com");
        UserDto userDto9 = new UserDto();
        userDto9.setEmail("user9@gmail.com");

        List<ConferenceDto> allConferences = conferencesService.getAllConferences();

        // Get all conferences
        String firstConferenceId = allConferences.get(0).getId();
        String secondConferenceId = allConferences.get(1).getId();

        // Get all submissions for each conference
        List<SubmissionDto> submissionsForFirstConference = conferencesService.getSubmissionsForConference(firstConferenceId);
        List<SubmissionDto> submissionsForSecondConference = conferencesService.getSubmissionsForConference(secondConferenceId);
        ReviewDto reviewDto;
        reviewDto = new ReviewDto();

        reviewDto.setVerdict(Verdict.NOT_REVIEWED);
        reviewDto.setUser(userDto2);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(0).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.NOT_REVIEWED);
        reviewDto.setUser(userDto3);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(0).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.NOT_REVIEWED);
        reviewDto.setUser(userDto4);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(0).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.WEAK_ACCEPT);
        reviewDto.setUser(userDto2);
        reviewDto.setRecommendation("recommendation user2 ~ submission 1");
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(1).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.ACCEPT);
        reviewDto.setUser(userDto3);
        reviewDto.setRecommendation("recommendation user3 ~ submission 1");
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(1).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.STRONG_ACCEPT);
        reviewDto.setUser(userDto4);
        reviewDto.setRecommendation("recommendation user4 ~ submission 2");
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(2).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.WEAK_ACCEPT);
        reviewDto.setUser(userDto3);
        reviewDto.setRecommendation("recommendation user3 ~ submission 2");
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(2).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.REJECT);
        reviewDto.setUser(userDto2);
        reviewDto.setRecommendation("recommendation user2 ~ submission 2");
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(2).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.STRONG_REJECT);
        reviewDto.setUser(userDto4);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(3).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.NOT_REVIEWED);
        reviewDto.setUser(userDto2);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(4).getId(), reviewDto);

        reviewDto.setVerdict(Verdict.NOT_REVIEWED);
        reviewDto.setUser(userDto3);
        conferencesService.addReview(firstConferenceId, submissionsForFirstConference.get(4).getId(), reviewDto);
    }

    private void createSections() {
        List<ConferenceDto> allConferences = conferencesService.getAllConferences();

        // Get all conferences
        String firstConferenceId = allConferences.get(0).getId();
        String secondConferenceId = allConferences.get(1).getId();

        // Get all submissions for each conference
        List<SubmissionDto> submissionsForFirstConference = conferencesService.getSubmissionsForConference(firstConferenceId);
        List<SubmissionDto> submissionsForSecondConference = conferencesService.getSubmissionsForConference(secondConferenceId);

        UserDto userDto0 = new UserDto();
        userDto0.setEmail("user0@gmail.com");
        UserDto userDto1 = new UserDto();
        userDto1.setEmail("user1@gmail.com");
        UserDto userDto2 = new UserDto();
        userDto2.setEmail("user2@gmail.com");
        UserDto userDto3 = new UserDto();
        userDto3.setEmail("user3@gmail.com");
        UserDto userDto4 = new UserDto();
        userDto4.setEmail("user4@gmail.com");
        UserDto userDto5 = new UserDto();
        userDto5.setEmail("user5@gmail.com");
        UserDto userDto6 = new UserDto();
        userDto6.setEmail("user6@gmail.com");
        UserDto userDto7 = new UserDto();
        userDto7.setEmail("user7@gmail.com");
        UserDto userDto8 = new UserDto();
        userDto8.setEmail("user8@gmail.com");
        UserDto userDto9 = new UserDto();
        userDto9.setEmail("user9@gmail.com");

        SubmissionDto submissionDto0 = submissionsForFirstConference.get(0);
        SubmissionDto submissionDto7 = submissionsForFirstConference.get(7);

        conferencesService.updateFinalVerdict(submissionDto0.getId(), Verdict.ACCEPT.toString());
        conferencesService.updateFinalVerdict(submissionDto7.getId(), Verdict.ACCEPT.toString());

        SectionDto sectionDto = new SectionDto();
        sectionDto.setSeats(3);
        sectionDto.setTitle("The one and only section");
        sectionDto.setSubmissions(List.of(submissionDto0, submissionDto7));  // only accepted papers
        sectionDto.setSectionChair(userDto8);
        sectionDto.setStartTime(1588386245);
        sectionDto.setEndTime(1588406245);
        sectionDto.setSpeakers(List.of(userDto1, userDto6));
        conferencesService.createSection(firstConferenceId, sectionDto);


//        sectionDto.setSeats(3);
//        sectionDto.setTitle("The second and only section");
//        sectionDto.setSubmissions(List.of(submissionDto7));  // only accepted papers
//        sectionDto.setSectionChair(userDto8);
//        sectionDto.setStartTime(1588386245);
//        sectionDto.setEndTime(1588406245);
//        sectionDto.setSpeakers(List.of(userDto1, userDto6));
//        conferencesService.createSection(firstConferenceId, sectionDto);
    }

}
