package com.cms.controllers;

import com.cms.dto.ConferenceDetailDto;
import com.cms.dto.ConferenceDto;
import com.cms.services.ConferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conferences")
public class ConferencesController {

    @Autowired
    ConferencesService conferencesService;

    @PostMapping()
    public ResponseEntity<ConferenceDto> createConference(@RequestBody ConferenceDto conferenceDto) {

        ConferenceDto createdConferenceDto = conferencesService.createConference(conferenceDto);
        return new ResponseEntity<>(conferenceDto, HttpStatus.CREATED);
    }

}
