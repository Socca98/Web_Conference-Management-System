package com.cms.dto.conference;

import com.cms.dto.user.UserDto;

import java.util.List;

public class ConferenceDto {

    private String id;
    private String name;
    private String website;
    private Integer startDate;
    private Integer endDate;
    private Integer proposalDeadline;
    private Integer abstractDeadline;
    private Integer biddingDeadline;
    private Integer evaluationDeadline;
    private boolean allowFullPaper;
    private Integer taxFee;

    private List<SubmissionDto> submissions;
    private List<UserRoleDto> users;
    private Integer nrOfReviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(Integer taxFee) {
        this.taxFee = taxFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public Integer getProposalDeadline() {
        return proposalDeadline;
    }

    public void setProposalDeadline(Integer proposalDeadline) {
        this.proposalDeadline = proposalDeadline;
    }

    public Integer getAbstractDeadline() {
        return abstractDeadline;
    }

    public void setAbstractDeadline(Integer abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
    }

    public Integer getBiddingDeadline() {
        return biddingDeadline;
    }

    public void setBiddingDeadline(Integer biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
    }

    public Integer getEvaluationDeadline() {
        return evaluationDeadline;
    }

    public void setEvaluationDeadline(Integer evaluationDeadline) {
        this.evaluationDeadline = evaluationDeadline;
    }

    public boolean isAllowFullPaper() {
        return allowFullPaper;
    }

    public void setAllowFullPaper(boolean allowFullPaper) {
        this.allowFullPaper = allowFullPaper;
    }

    public ConferenceDto name(String name) {
        this.name = name;
        return this;
    }

    public ConferenceDto website(String website) {
        this.website = website;
        return this;
    }

    public ConferenceDto startDate(Integer startDate) {
        this.startDate = startDate;
        return this;
    }

    public ConferenceDto endDate(Integer endDate) {
        this.endDate = endDate;
        return this;
    }

    public ConferenceDto proposalDeadline(Integer proposalDeadline) {
        this.proposalDeadline = proposalDeadline;
        return this;
    }

    public ConferenceDto abstractDeadline(Integer abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
        return this;
    }

    public ConferenceDto biddingDeadline(Integer biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
        return this;
    }

    public ConferenceDto evaluationDeadline(Integer evaluationDeadline) {
        this.evaluationDeadline = evaluationDeadline;
        return this;
    }


    public ConferenceDto allowFullPaper(boolean allowFullPaper) {
        this.allowFullPaper = allowFullPaper;
        return this;
    }

    public ConferenceDto taxFee(Integer taxFee) {
        this.taxFee = taxFee;
        return this;
    }

    public ConferenceDto id(String id) {
        this.id = id;
        return this;
    }


    public List<SubmissionDto> getSubmissions() {
        return submissions;
    }

    public ConferenceDto submissions(List<SubmissionDto> submissions) {
        this.submissions = submissions;
        return this;
    }
    public void setSubmissions(List<SubmissionDto> submissions) {
        this.submissions = submissions;
    }

    public List<UserRoleDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserRoleDto> users) {
        this.users = users;
    }

    public ConferenceDto nrOfReviews(Integer nrOfReviews) {
        this.nrOfReviews = nrOfReviews;
        return this;
    }

    public Integer getNrOfReviews() {
        return nrOfReviews;
    }

    public void setNrOfReviews(Integer nrOfReviews) {
        this.nrOfReviews = nrOfReviews;
    }
}
