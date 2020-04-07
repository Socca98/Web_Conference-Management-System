package com.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @Column(length = 30)
    private String username;

    private String password;

    private String realName;

    private String email;

    private String affiliation;

    private String personalWebpage;

    public User(String username, String password, String realName, String email, String affiliation, String personalWebpage) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.email = email;
        this.affiliation = affiliation;
        this.personalWebpage = personalWebpage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public String getEmail() {
        return email;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getPersonalWebpage() {
        return personalWebpage;
    }
}
