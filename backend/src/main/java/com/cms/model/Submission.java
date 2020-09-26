package com.cms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
public class Submission {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String submissionId;

    private String title;
    private String abstractPaper;
    private String fullPaper;
    private String keywords;
    private String topics;
    private String finalVerdict;

    @ManyToMany
    private List<User> authors;

    @ManyToMany
    private List<User> likes;

    @OneToMany(orphanRemoval = true)
    private List<Review> reviews;

    @ManyToOne
    private Section section;

    @ManyToOne
    @JoinColumn(name ="conference_id")
    private Conference conference;

    public String getSubmissionId() {
        return submissionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractPaper() {
        return abstractPaper;
    }

    public void setAbstractPaper(String abstractPaper) {
        this.abstractPaper = abstractPaper;
    }

    public String getFullPaper() {
        return fullPaper;
    }

    public void setFullPaper(String fullPaper) {
        this.fullPaper = fullPaper;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getFinalVerdict() {
        return finalVerdict;
    }

    public void setFinalVerdict(String finalVerdict) {
        this.finalVerdict = finalVerdict;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setSubmissionId(String id) {
        this.submissionId = id;
    }

    public List<User> getAuthors() {
        return authors;
    }

    public void setAuthors(List<User> authors) {
        this.authors = authors;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
