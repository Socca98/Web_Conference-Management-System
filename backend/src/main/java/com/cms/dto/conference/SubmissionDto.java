package com.cms.dto.conference;

import com.cms.dto.user.UserDto;

import java.util.List;

public class SubmissionDto {

    private String id;
    private String title;
    private String abstractPaper;
    private String fullPaper;
    private String keywords;
    private String topics;
    private String finalVerdict;
    private SectionDto section;
    private List<UserRoleDto> authors;
    private List<UserDto> likes;
    private List<ReviewDto> reviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<UserRoleDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<UserRoleDto> authors) {
        this.authors = authors;
    }

    public List<UserDto> getLikes() {
        return likes;
    }

    public void setLikes(List<UserDto> likes) {
        this.likes = likes;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    public SubmissionDto id(String id) {
        this.id = id;
        return this;
    }

    public SubmissionDto title(String title) {
        this.title = title;
        return this;
    }
    public SubmissionDto section(SectionDto sectionDto) {
        this.section = sectionDto;
        return this;
    }


    public SubmissionDto abstractPaper(String abstractPaper) {
        this.abstractPaper = abstractPaper;
        return this;
    }


    public SubmissionDto fullPaper(String fullPaper) {
        this.fullPaper = fullPaper;
        return this;
    }


    public SubmissionDto keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }


    public SubmissionDto topics(String topics) {
        this.topics = topics;
        return this;
    }


    public SubmissionDto finalVerdict(String finalVerdict) {
        this.finalVerdict = finalVerdict;
        return this;
    }


    public SubmissionDto authors(List<UserRoleDto> userDtos) {
        this.authors = userDtos;
        return this;
    }


    public SubmissionDto likes(List<UserDto> userDtos) {
        this.likes = userDtos;
        return this;
    }

    public SubmissionDto reviews(List<ReviewDto> reviewDtos) {
        this.reviews = reviewDtos;
        return this;
    }

    public SectionDto getSection() {
        return section;
    }

    public void setSection(SectionDto section) {
        this.section = section;
    }
}
