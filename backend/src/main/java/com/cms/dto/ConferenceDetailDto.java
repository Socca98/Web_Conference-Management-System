package com.cms.dto;

public class ConferenceDetailDto {

    private int id;
    private String name;
    private int startDate;
    private int endDate;
    private String website;
    private int abstractDeadline;
    private int proposalDeadline;
    private int taxFee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getAbstractDeadline() {
        return abstractDeadline;
    }

    public void setAbstractDeadline(int abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
    }

    public int getProposalDeadline() {
        return proposalDeadline;
    }

    public void setProposalDeadline(int proposalDeadline) {
        this.proposalDeadline = proposalDeadline;
    }

    public int getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(int taxFee) {
        this.taxFee = taxFee;
    }


    public ConferenceDetailDto id(int id) {
        this.id = id;
        return this;
    }

    public ConferenceDetailDto name(String name) {
        this.name = name;
        return this;
    }

    public ConferenceDetailDto startDate(int StartDate) {
        this.startDate = startDate;
        return this;
    }

    public ConferenceDetailDto endDate(int endDate) {
        this.endDate = endDate;
        return this;
    }

    public ConferenceDetailDto website(String website) {
        this.website = website;
        return this;
    }

    public ConferenceDetailDto abstractDeadline(int abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
        return this;
    }

    public ConferenceDetailDto proposalDeadline(int proposalDeadline) {
        this.proposalDeadline = proposalDeadline;
        return this;
    }

    public ConferenceDetailDto taxFee(int taxFee) {
        this.taxFee = taxFee;
        return this;
    }
}
