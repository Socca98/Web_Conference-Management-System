package com.cms.dto.user;

public class UserDto {

    private String id;
    private String username;
    private String fullName;
    private String affiliation;
    private String email;
    private String webpage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public UserDto id(String id) {
        this.id = id;
        return this;
    }

    public UserDto username(String username) {
        this.username = username;
        return this;
    }

    public UserDto fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public UserDto affiliation(String affiliation) {
        this.affiliation = affiliation;
        return this;
    }
    public UserDto email(String email) {
        this.email = email;
        return this;
    }
    public UserDto webpage(String webpage) {
        this.webpage = webpage;
        return this;
    }

}
