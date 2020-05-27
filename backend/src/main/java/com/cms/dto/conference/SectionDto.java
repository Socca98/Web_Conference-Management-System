package com.cms.dto.conference;

import com.cms.dto.user.UserDto;

import java.util.List;

public class SectionDto {
    private String sectionId;
    private String title;
    private UserDto sectionChair;
    private ConferenceDto conference;
    private List<SubmissionDto> submissions;

    private Integer startTime;
    private Integer endTime;

    private List<UserDto> speakers;
    private List<UserDto> listeners;
    private int seats;


    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDto getSectionChair() {
        return sectionChair;
    }

    public void setSectionChair(UserDto sectionChair) {
        this.sectionChair = sectionChair;
    }

    public ConferenceDto getConference() {
        return conference;
    }

    public void setConference(ConferenceDto conference) {
        this.conference = conference;
    }

    public List<SubmissionDto> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionDto> submissions) {
        this.submissions = submissions;
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

    public List<UserDto> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<UserDto> speakers) {
        this.speakers = speakers;
    }

    public List<UserDto> getListeners() {
        return listeners;
    }

    public void setListeners(List<UserDto> listeners) {
        this.listeners = listeners;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
