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
    private String fullName;
    private String email;
    private String affiliation;
    private String webpage;

    public User() {
    }

    public User(String username, String password, String fullName, String email, String affiliation, String webpage) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.affiliation = affiliation;
        this.webpage = webpage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

}
