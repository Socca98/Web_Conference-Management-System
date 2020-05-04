package com.cms.utils;

import com.cms.dto.ConferenceDetailDto;
import com.cms.dto.ConferenceDto;
import com.cms.model.Conference;

public class ConferenceConverter {

    public static ConferenceDetailDto conferenceToConferenceDetail(Conference conference) {
        return new ConferenceDetailDto().id(conference.getId())
                .name(conference.getName())
                .startDate(conference.getStartDate())
                .endDate(conference.getEndDate())
                .website(conference.getWebsite())
                .abstractDeadline(conference.getAbstractDeadline())
                .proposalDeadline(conference.getProposalDeadline())
                .taxFee(conference.getTaxFee());
    }

    public static Conference conferenceDtoToConference(ConferenceDto conferenceDto) {
        return new Conference(conferenceDto.getName(), conferenceDto.getStartDate(), conferenceDto.getEndDate(),
                conferenceDto.getAbstractDeadline(), conferenceDto.getProposalDeadline());
    }

    public static ConferenceDto conferenceToConferenceDto(Conference conference) {
        return new ConferenceDto().id(conference.getId())
                .name(conference.getName())
                .startDate(conference.getStartDate())
                .endDate(conference.getEndDate())
                .website(conference.getWebsite())
                .abstractDeadline(conference.getAbstractDeadline())
                .proposalDeadline(conference.getProposalDeadline())
                .taxFee(conference.getTaxFee())
                .biddingDeadline(conference.getBiddingDeadline())
                .evaluationDeadline(conference.getEvaluationDeadline());
    }
}
