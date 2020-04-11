package com.cms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Conference {
    @Id
    private Long id;
    private String name;
    private String website;
    private Date startDate;
    private Date endDate;
    private Date proposalDeadline;
    private Date abstractDeadline;
    private Date biddingDeadline;
    private Date evaluationDeadline;
    private boolean isProposalPeriod;
    private boolean isAbstractPeriod;
    private boolean isBiddingPeriod;
    private boolean isEvaluationPeriod;
    private boolean isConferencePeriod;
    private boolean allowFullPaper;

    @OneToMany(mappedBy = "conference")
    private List<Section> items;

    public Conference() {
    }

    public Conference(Date startDate, Date endDate, Date abstractDeadline, Date proposalDeadline) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.abstractDeadline = abstractDeadline;
        this.proposalDeadline = proposalDeadline;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getProposalDeadline() {
        return proposalDeadline;
    }

    public Date getAbstractDeadline() {
        return abstractDeadline;
    }

    public Date getBiddingDeadline() {
        return biddingDeadline;
    }

    public Date getEvaluationDeadline() {
        return evaluationDeadline;
    }

    public boolean isProposalPeriod() {
        return isProposalPeriod;
    }

    public boolean isAbstractPeriod() {
        return isAbstractPeriod;
    }

    public boolean isBiddingPeriod() {
        return isBiddingPeriod;
    }

    public boolean isEvaluationPeriod() {
        return isEvaluationPeriod;
    }

    public boolean isConferencePeriod() {
        return isConferencePeriod;
    }

    public boolean isAllowFullPaper() {
        return allowFullPaper;
    }

    public List<Section> getItems() {
        return items;
    }
}

