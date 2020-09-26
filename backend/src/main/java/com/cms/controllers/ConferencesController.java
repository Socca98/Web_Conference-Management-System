package com.cms.controllers;

import com.cms.dto.conference.*;
import com.cms.services.ConferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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

    @PutMapping("/{conferenceId}")
    public ResponseEntity<ConferenceDto> updateConference(@PathVariable String conferenceId,
                                                          @RequestBody ConferenceDto conferenceDto) {
        return ResponseEntity.ok(conferencesService.updateConference(conferenceId, conferenceDto));
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

    @PutMapping("/{conferenceId}/submissions/{submissionId}")
    public ResponseEntity<SubmissionDto> updateSubmission(@RequestBody SubmissionDto submissionDto,
                                                          @PathVariable String conferenceId,
                                                          @PathVariable String submissionId) {
        return ResponseEntity.ok(conferencesService.updateSubmission(submissionId, submissionDto));
    }

    @GetMapping("/{conferenceId}/submissions/{submissionId}")
    public ResponseEntity<SubmissionDto> updateSubmission(@PathVariable String conferenceId,
                                                          @PathVariable String submissionId) {
        return ResponseEntity.ok(conferencesService.getSubmission(submissionId));
    }

    @GetMapping("/{conferenceId}/submissions")
    public ResponseEntity<List<SubmissionDto>> getSubmissionsForConference(@PathVariable String conferenceId) {
        return ResponseEntity.ok(conferencesService.getSubmissionsForConference(conferenceId));
    }

    @PostMapping("/{conferenceId}/submissions/{submissionId}/final-verdict")
    public ResponseEntity<SubmissionDto> updateFinalVerdict(@PathVariable String conferenceId,
                                                            @PathVariable String submissionId,
                                                            @RequestBody String verdict) {
        return ResponseEntity.ok(conferencesService.updateFinalVerdict(submissionId, verdict));
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

    @GetMapping("/{conferenceId}/submissions/{submissionId}/review")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable String conferenceId,
                                                      @PathVariable String submissionId,
                                                      @RequestParam(required = false, defaultValue = "all") String status) {
        return ResponseEntity.ok(conferencesService.getReviewsForStatus(submissionId, status));
    }

    @GetMapping("/{conferenceId}/review/others")
    public ResponseEntity<List<ReviewDto>> getOthersReviews(@PathVariable String conferenceId) {
        return ResponseEntity.ok(conferencesService.getReviewsForOthers(conferenceId));
    }

    @GetMapping("/{conferenceId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsForConference(@PathVariable String conferenceId,
                                                                   @RequestParam(required = false, defaultValue = "all") String status) {
        return ResponseEntity.ok(conferencesService.getReviewsForConference(conferenceId, status));
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

    @PostMapping("/{conferenceId}/sections")
    public ResponseEntity<SectionDto> createSection(@PathVariable String conferenceId,
                                                    @RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(conferencesService.createSection(conferenceId, sectionDto));
    }

    @PutMapping("/{conferenceId}/sections/{sectionId}")
    public ResponseEntity<SectionDto> updateSection(@PathVariable String conferenceId,
                                                    @PathVariable String sectionId,
                                                    @RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(conferencesService.updateSection(sectionId, sectionDto));
    }

    @GetMapping("/{conferenceId}/sections/{sectionId}")
    public ResponseEntity<SectionDto> getSection(@PathVariable String conferenceId,
                                                    @PathVariable String sectionId) {
        return ResponseEntity.ok(conferencesService.getSection(sectionId));
    }

    @GetMapping("/{conferenceId}/sections")
    public ResponseEntity<List<SectionDto>> getAllSectionsForConference(@PathVariable String conferenceId) {
        return ResponseEntity.ok(conferencesService.getAllSectionsForConference(conferenceId));
    }

    @DeleteMapping("/{conferenceId}/sections/{sectionId}")
    public ResponseEntity<Void> deleteSection(@PathVariable String conferenceId,
                                              @PathVariable String sectionId) {
        conferencesService.removeSection(sectionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{conferenceId}/sections/{sectionId}/attend")
    public ResponseEntity<Void> attendSection(@PathVariable String conferenceId,
                                              @PathVariable String sectionId) {
        conferencesService.attendSection(sectionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{conferenceId}/sections/{sectionId}/attend")
    public ResponseEntity<Void> unattedSection(@PathVariable String conferenceId,
                                               @PathVariable String sectionId) {
        conferencesService.unattendSection(sectionId);
        return ResponseEntity.ok().build();
    }
}
