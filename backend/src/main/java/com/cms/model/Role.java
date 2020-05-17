package com.cms.model;

import javax.persistence.*;

@Entity
public class Role {

    @EmbeddedId
    protected RoleKey roleKey = new RoleKey();

    @ManyToOne
    @JoinColumn(name ="conference_id", insertable = false, updatable = false)
    Conference conference;
    @ManyToOne
    @JoinColumn(name ="user_id", insertable = false, updatable = false)
    User user;

    Roles role;

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
        this.roleKey.setConference(conference.getConferenceId());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.roleKey.setUser(user.getUserId());
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
