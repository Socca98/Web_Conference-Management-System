package com.cms.dto;

public class UserInformationDto {
    private String username;
    private String affiliation;
    private String role;
    private Boolean isChair;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getChair() {
        return isChair;
    }

    public void setChair(Boolean chair) {
        isChair = chair;
    }

    public UserInformationDto username(String username) {
        this.username = username;
        return this;
    }
    public UserInformationDto affiliation(String affiliation) {
        this.affiliation = affiliation;
        return this;
    }
    public UserInformationDto role(String role) {
        this.role = role;
        return this;
    }
    public UserInformationDto isChair(Boolean isChair) {
        this.isChair = isChair;
        return this;
    }
}
