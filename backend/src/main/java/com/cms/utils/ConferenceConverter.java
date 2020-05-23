package com.cms.utils;

import com.cms.dto.conference.ConferenceDto;
import com.cms.dto.conference.ReviewDto;
import com.cms.dto.conference.SectionDto;
import com.cms.dto.conference.SubmissionDto;
import com.cms.model.Conference;
import com.cms.model.Review;
import com.cms.model.Section;
import com.cms.model.Submission;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ConferenceConverter {

    public static Conference conferenceDtoToConference(ConferenceDto conferenceDto) {
        Conference conference = new Conference();
        if (Objects.nonNull(conferenceDto.getId())) {
            conference.setConferenceId(conferenceDto.getId());
        }
        conference.setName(conferenceDto.getName());
        conference.setWebsite(conferenceDto.getWebsite());
        conference.setStartDate(conferenceDto.getStartDate());
        conference.setEndDate(conferenceDto.getEndDate());
        conference.setProposalDeadline(conferenceDto.getProposalDeadline());
        conference.setAbstractDeadline(conferenceDto.getAbstractDeadline());
        conference.setBiddingDeadline(conferenceDto.getBiddingDeadline());
        conference.setEvaluationDeadline(conferenceDto.getEvaluationDeadline());
        conference.setAllowFullPaper(conferenceDto.isAllowFullPaper());
        conference.setTaxFee(conferenceDto.getTaxFee());
        conference.setNrOfReviews(conferenceDto.getNrOfReviews());
        return conference;
    }

    public static ConferenceDto conferenceToConferenceDto(Conference conference) {
        return new ConferenceDto()
                .id(conference.getConferenceId())
                .submissions(submissionToSubmissionDto(conference.getSubmissions()))
                .name(conference.getName())
                .website(conference.getWebsite())
                .startDate(conference.getStartDate())
                .endDate(conference.getEndDate())
                .abstractDeadline(conference.getAbstractDeadline())
                .proposalDeadline(conference.getProposalDeadline())
                .biddingDeadline(conference.getBiddingDeadline())
                .evaluationDeadline(conference.getEvaluationDeadline())
                .taxFee(conference.getTaxFee())
                .nrOfReviews(conference.getNrOfReviews());
    }

    public static Submission submissionDtoToSubmission(SubmissionDto submissionDto) {
        Submission submission = new Submission();
        if (Objects.nonNull(submissionDto.getId())) {
            submission.setSubmissionId(submissionDto.getId());
        }
        submission.setAbstractPaper(submissionDto.getAbstractPaper());
        submission.setAuthors(UserConverter.userRoleDtoToUser(submissionDto.getAuthors()));
        submission.setFinalVerdict(submissionDto.getFinalVerdict());
        submission.setFullPaper(submissionDto.getFullPaper());
        submission.setKeywords(submissionDto.getKeywords());
        submission.setLikes(UserConverter.userDtoToUser(submissionDto.getLikes()));
        submission.setReviews(ConferenceConverter.reviewDtoToReview(submissionDto.getReviews()));
        submission.setTitle(submissionDto.getTitle());
        submission.setTopics(submissionDto.getTopics());
        return submission;
    }

    public static SubmissionDto submissionToSubmissionDto(Submission submission) {
        return new SubmissionDto()
                .id(submission.getSubmissionId())
                .abstractPaper(submission.getAbstractPaper())
                .authors(UserConverter.userDtoToAuthorUser(submission.getAuthors()))
                .finalVerdict(submission.getFinalVerdict())
                .fullPaper(submission.getFullPaper())
                .keywords(submission.getKeywords())
                .likes(UserConverter.userToUserDto(submission.getLikes()))
                .reviews(ConferenceConverter.reviewToReviewDto(submission.getReviews()))
                .title(submission.getTitle())
                .section(ConferenceConverter.sectionToSectionDto(submission.getSection()))
                .topics(submission.getTopics());
    }

    public static List<SubmissionDto> submissionToSubmissionDto(List<Submission> submission) {
        if (Objects.isNull(submission)) {
            return List.of();
        }
        return submission.stream().map(ConferenceConverter::submissionToSubmissionDto).collect(Collectors.toList());
    }

    public static ReviewDto reviewToReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setRecommendation(review.getRecommendation());
        reviewDto.setVerdict(review.getVerdict());
        reviewDto.setUser(UserConverter.userToUserDto(review.getUser()));
        reviewDto.setReviewId(review.getReviewId());
        return reviewDto;
    }

    public static List<ReviewDto> reviewToReviewDto(List<Review> review) {
        if (Objects.isNull(review)) {
            return List.of();
        }
        return review.stream().map(ConferenceConverter::reviewToReviewDto).collect(Collectors.toList());
    }

    public static Review reviewDtoToReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setRecommendation(reviewDto.getRecommendation());
        review.setVerdict(reviewDto.getVerdict());
        review.setReviewId(reviewDto.getReviewId());
        return review;
    }

    public static List<Review> reviewDtoToReview(List<ReviewDto> reviewDtos) {
        if (Objects.isNull(reviewDtos)) {
            return List.of();
        }
        return reviewDtos.stream().map(ConferenceConverter::reviewDtoToReview).collect(Collectors.toList());
    }

    public static Section sectionDtoToSection(SectionDto sectionDto) {
        Section section = new Section();
        section.setSectionId(sectionDto.getSectionId());
        section.setTitle(sectionDto.getTitle());
        section.setStartTime(sectionDto.getStartTime());
        section.setEndTime(sectionDto.getEndTime());
        section.setSeats(sectionDto.getSeats());
        return section;
    }

    public static SectionDto sectionToSectionDto(Section section) {
        if(Objects.isNull(section)) {
            return null;
        }
        SectionDto sectionDto = new SectionDto();
        sectionDto.setSectionId(section.getSectionId());
        sectionDto.setTitle(section.getTitle());
        sectionDto.setStartTime(section.getStartTime());
        sectionDto.setEndTime(section.getEndTime());
        sectionDto.setSeats(section.getSeats());
        sectionDto.setSpeakers(UserConverter.userToUserDto(section.getSpeakers()));
        sectionDto.setListeners(UserConverter.userToUserDto(section.getListeners()));
        return sectionDto;
    }

    public static List<SectionDto> sectionToSectionDto(List<Section> sections) {
        if (Objects.isNull(sections)) {
            return List.of();
        }
        return sections.stream().map(ConferenceConverter::sectionToSectionDto).collect(Collectors.toList());
    }
}