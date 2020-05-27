package com.cms.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoleKey implements Serializable {
    @Column(name = "conference_id")
    private String conferenceId;
    @Column(name = "user_id")
    private String userId;

    public String getConference() {
        return conferenceId;
    }

    public void setConference(String conference) {
        this.conferenceId = conference;
    }

    public String getUser() {
        return userId;
    }

    public void setUser(String user) {
        this.userId = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(conferenceId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof RoleKey))
            return false;
        RoleKey roleKey = (RoleKey) obj;
        return Objects.equals(conferenceId, roleKey.conferenceId) &&
                Objects.equals(userId, roleKey.userId);
    }
}
