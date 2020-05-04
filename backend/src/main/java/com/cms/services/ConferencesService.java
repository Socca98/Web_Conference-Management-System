package com.cms.services;


import com.cms.dto.ConferenceDetailDto;
import com.cms.dto.ConferenceDto;
import com.cms.model.Conference;
import com.cms.repositories.ConferenceJpaRepository;
import com.cms.utils.ConferenceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferencesService {

    @Autowired
    ConferenceJpaRepository conferencesRepository;

    public ConferenceDto createConference(ConferenceDto conferenceDto) {
        Conference savedConference = conferencesRepository.save(ConferenceConverter.conferenceDtoToConference(conferenceDto));
        return ConferenceConverter.conferenceToConferenceDto(savedConference);
    }

}
