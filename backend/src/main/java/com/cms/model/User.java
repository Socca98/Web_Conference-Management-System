package com.cms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
public class User {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String userId;
    @Column(length = 50,  unique = true)
    private String username;
    private String password;
    private String fullName;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    private String affiliation;
    private String webpage;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Role> roles;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Review> reviews;
    @ManyToMany
    private List<Submission> submissions;
    @ManyToMany
    private List<Submission> likes;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setWebpage(String personalWebpage) {
        this.webpage = personalWebpage;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
