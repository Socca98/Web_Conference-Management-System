package com.cms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(UserProposalKey.class)
public class UserProposals {

    boolean likeThis;
    boolean isAuthor;
    boolean isReviewer;
    String verdictReviewer;
    String recommendationReviewer;
    @ManyToOne
    @Id
    User user;
    @ManyToOne
    @Id
    Proposal proposal;

}
