package com.cms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Section {
    @Id
    private Long id;
    private String title;
    private String sessionChair;
    private String speaker;

    @ManyToOne
    private Conference conference;
}
