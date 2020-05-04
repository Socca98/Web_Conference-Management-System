package com.cms.dto;

public class ConferenceDto {

    private int id;
    private String name;
    private String website;
    private int startDate;
    private int endDate;
    private int proposalDeadline;
    private int abstractDeadline;
    private int biddingDeadline;
    private int evaluationDeadline;
    private boolean allowFullPaper;
    private int taxFee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(int taxFee) {
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

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getProposalDeadline() {
        return proposalDeadline;
    }

    public void setProposalDeadline(int proposalDeadline) {
        this.proposalDeadline = proposalDeadline;
    }

    public int getAbstractDeadline() {
        return abstractDeadline;
    }

    public void setAbstractDeadline(int abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
    }

    public int getBiddingDeadline() {
        return biddingDeadline;
    }

    public void setBiddingDeadline(int biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
    }

    public int getEvaluationDeadline() {
        return evaluationDeadline;
    }

    public void setEvaluationDeadline(int evaluationDeadline) {
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

    public ConferenceDto startDate(int startDate) {
        this.startDate = startDate;
        return this;
    }

    public ConferenceDto endDate(int endDate) {
        this.endDate = endDate;
        return this;
    }

    public ConferenceDto proposalDeadline(int proposalDeadline) {
        this.proposalDeadline = proposalDeadline;
        return this;
    }

    public ConferenceDto abstractDeadline(int abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
        return this;
    }

    public ConferenceDto biddingDeadline(int biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
        return this;
    }

    public ConferenceDto evaluationDeadline(int evaluationDeadline) {
        this.evaluationDeadline = evaluationDeadline;
        return this;
    }


    public ConferenceDto allowFullPaper(boolean allowFullPaper) {
        this.allowFullPaper = allowFullPaper;
        return this;
    }

    public ConferenceDto taxFee(int taxFee) {
        this.taxFee = taxFee;
        return this;
    }

    public ConferenceDto id(int id) {
        this.id = id;
        return this;
    }


}
