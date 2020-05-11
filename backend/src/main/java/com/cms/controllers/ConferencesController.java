package com.cms.controllers;

import com.cms.dto.conference.ConferenceDto;
import com.cms.dto.conference.CreateConferenceDto;
import com.cms.dto.conference.SubmissionDto;
import com.cms.model.Conference;
import com.cms.services.ConferencesService;
import com.cms.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferences")
public class ConferencesController {

    @Autowired
    private ConferencesService conferencesService;

    @PostMapping
    public ResponseEntity<ConferenceDto> createConference(@RequestBody CreateConferenceDto conferenceDto) {

        ConferenceDto createdConferenceDto = conferencesService.createConference(conferenceDto);
        return new ResponseEntity<>(createdConferenceDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConferenceDto>> getAllConferences() {
        return ResponseEntity.ok(conferencesService.getAllConferences());
    }

    @PostMapping("/{conferenceId}/submissions")
    public ResponseEntity<SubmissionDto> addSubmission(@RequestBody SubmissionDto submissionDto,
                                                       @PathVariable String conferenceId) {
        return ResponseEntity.ok(conferencesService.addSubmission(conferenceId, submissionDto));
    }

    @PostMapping("/{conferenceId}/submissions/{submissionId}/like")
    public ResponseEntity<SubmissionDto> likeSubmission(@PathVariable String conferenceId,
                                                        @PathVariable String submissionId) {
        return ResponseEntity.ok(conferencesService.likeSubmission(conferenceId, submissionId));
    }
    @DeleteMapping("/{conferenceId}/submissions/{submissionId}/like")
    public ResponseEntity<SubmissionDto> unlikeSubmission(@PathVariable String conferenceId,
                                                        @PathVariable String submissionId) {
        return ResponseEntity.ok(conferencesService.unlikeSubmission(conferenceId, submissionId));
    }


}
