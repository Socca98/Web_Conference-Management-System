package com.cms.dto.conference;

import com.cms.dto.user.UserDto;
import com.cms.model.Verdict;

public class ReviewDto {

    private String reviewId;
    private UserDto user;
    private Verdict verdict;
    private String recommendation;
    private SubmissionDto submission;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public SubmissionDto getSubmission() {
        return submission;
    }

    public void setSubmission(SubmissionDto submission) {
        this.submission = submission;
    }
}
