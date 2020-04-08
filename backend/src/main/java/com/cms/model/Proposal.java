package com.cms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Proposal {
    @Id
    private Long id;
    private String abstractPaper;
    private String name;
    private String keywords;
    private String topics;
    private String fullPaper;
    private String finalVerdict;
    private String isEvaluated;
}
