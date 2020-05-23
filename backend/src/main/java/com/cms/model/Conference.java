package com.cms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
public class Conference {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String conferenceId;
    private String name;
    private String website;
    private Integer startDate;
    private Integer endDate;
    private Integer proposalDeadline;
    private Integer abstractDeadline;
    private Integer biddingDeadline;
    private Integer evaluationDeadline;
    private Boolean allowFullPaper;
    private Integer taxFee;
    private Integer nrOfReviews;

    @OneToMany(mappedBy="conference", orphanRemoval = true)
    private List<Submission> submissions;

    @OneToMany(mappedBy = "conference", orphanRemoval = true)
    private List<Role> roles;

    public void setConferenceId(String id) {
        this.conferenceId = id;
    }

    public String getConferenceId() {
        return conferenceId;
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

    public Boolean getAllowFullPaper() {
        return allowFullPaper;
    }

    public void setAllowFullPaper(Boolean allowFullPaper) {
        this.allowFullPaper = allowFullPaper;
    }

    public Integer getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(Integer taxFee) {
        this.taxFee = taxFee;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getNrOfReviews() {
        return nrOfReviews;
    }

    public void setNrOfReviews(Integer nrOfReviews) {
        this.nrOfReviews = nrOfReviews;
    }
}

