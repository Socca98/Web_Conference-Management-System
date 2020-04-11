package com.cms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(RoleKey.class)
public class Role {

    String role;

    @ManyToOne
    @Id
    Conference conference;
    @ManyToOne
    @Id
    User user;

}
