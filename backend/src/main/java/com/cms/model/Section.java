package com.cms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
public class Section {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length = 50)
    private String sectionId;
    private String title;
    @ManyToOne
    @JoinColumn(name ="session_chair_id")
    private User sectionChair;
    @ManyToOne
    @JoinColumn(name ="conference_id")
    private Conference conference;
    @OneToMany
    private List<Submission> submissions;

    private Integer startTime;
    private Integer endTime;

    @ManyToMany
    private List<User> speakers;
    @ManyToMany
    private List<User> listeners;
    private int seats;

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String id) {
        this.sectionId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getSectionChair() {
        return sectionChair;
    }

    public void setSectionChair(User sectionChair) {
        this.sectionChair = sectionChair;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public List<User> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<User> speakers) {
        this.speakers = speakers;
    }

    public List<User> getListeners() {
        return listeners;
    }

    public void setListeners(List<User> listeners) {
        this.listeners = listeners;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}
