package com.cms.controllers;

import com.cms.dto.conference.*;
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

    @GetMapping("/{conferenceId}")
    public ResponseEntity<ConferenceDto> getConference(@PathVariable String conferenceId) {
        return ResponseEntity.ok(conferencesService.getConference(conferenceId));
    }

    @PutMapping("/{conferenceId}/users")
    public ResponseEntity<Void> addUserToConference(@PathVariable String conferenceId,
                                                    @RequestBody List<UserRoleDto> userRoleDtos) {
        conferencesService.addUsersToConference(conferenceId, userRoleDtos);
        return ResponseEntity.ok().build();
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

    @PostMapping("/{conferenceId}/submissions/{submissionId}/review")
    public ResponseEntity<ReviewDto> addReviewer(@PathVariable String conferenceId,
                                                 @PathVariable String submissionId,
                                                 @RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(conferencesService.addReview(conferenceId, submissionId, reviewDto));
    }

    @PutMapping("/{conferenceId}/submissions/{submissionId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> editReviewer(@PathVariable String conferenceId,
                                                  @PathVariable String submissionId,
                                                  @PathVariable String reviewId,
                                                  @RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(conferencesService.updateReview(reviewId, reviewDto));
    }

    @DeleteMapping("/{conferenceId}/submissions/{submissionId}/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String conferenceId,
                                             @PathVariable String submissionId,
                                             @PathVariable String reviewId) {
        conferencesService.removeReview(reviewId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{conferenceId}/submissions/{submissionId}/section")
    public ResponseEntity<SectionDto> createSection(@PathVariable String conferenceId,
                                             @PathVariable String submissionId,
                                             @RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(conferencesService.createSection(conferenceId, submissionId, sectionDto));
    }

    @GetMapping("/{conferenceId}/sections")
    public ResponseEntity<List<SectionDto>> getAllSectionsForConference(@PathVariable String conferenceId) {
        return ResponseEntity.ok(conferencesService.getAllSectionsForConference(conferenceId));
    }

    @DeleteMapping("/{conferenceId}/submissions/{submissionId}/section/{sectionId}")
    public ResponseEntity<Void> deleteSection(@PathVariable String conferenceId,
                                             @PathVariable String submissionId,
                                             @PathVariable String sectionId) {
        conferencesService.removeSection(sectionId);
        return ResponseEntity.ok().build();
    }
}
