package com.cms.utils;

import com.cms.dto.conference.ConferenceDto;
import com.cms.dto.conference.SubmissionDto;
import com.cms.model.Conference;
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
                .taxFee(conference.getTaxFee());
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
        submission.setTitle(submissionDto.getTitle());
        submission.setTopics(submissionDto.getTopics());
        //TODO add section here
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
                .title(submission.getTitle())
                .topics(submission.getTopics());
    }

    public static List<SubmissionDto> submissionToSubmissionDto(List<Submission> submission) {
        if (Objects.isNull(submission)) {
            return List.of();
        }
        return submission.stream().map(ConferenceConverter::submissionToSubmissionDto).collect(Collectors.toList());
    }
}