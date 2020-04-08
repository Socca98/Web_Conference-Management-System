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
}
