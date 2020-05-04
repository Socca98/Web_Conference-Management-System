package com.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Conference {

    @Id
    @GeneratedValue
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



    @OneToMany(mappedBy = "conference")
    private List<Section> items;

    public Conference() {
    }

    public Conference(String name, int startDate, int endDate, int abstractDeadline, int proposalDeadline) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.abstractDeadline = abstractDeadline;
        this.proposalDeadline = proposalDeadline;
    }

    public int getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(int taxFee) {
        this.taxFee = taxFee;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public int getProposalDeadline() {
        return proposalDeadline;
    }

    public int getAbstractDeadline() {
        return abstractDeadline;
    }

    public int getBiddingDeadline() {
        return biddingDeadline;
    }

    public int getEvaluationDeadline() {
        return evaluationDeadline;
    }

    public boolean isAllowFullPaper() {
        return allowFullPaper;
    }

    public List<Section> getItems() {
        return items;
    }
}

