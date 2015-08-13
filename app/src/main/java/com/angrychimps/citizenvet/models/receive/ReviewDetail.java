package com.angrychimps.citizenvet.models.receive;

import java.util.Date;
import java.util.List;

/*
    Used in Location API inside CompanyLocationDetail
    Used in Review API as response from GET
 */
public class ReviewDetail {
    private String id;
    private String reviewerName;
    private String reviewerPhoto;
    private float rating;
    private String body;
    private List<String> staffIds;
    private Date createdAt;

    public ReviewDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerPhoto() {
        return reviewerPhoto;
    }

    public void setReviewerPhoto(String reviewerPhoto) {
        this.reviewerPhoto = reviewerPhoto;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<String> staffIds) {
        this.staffIds = staffIds;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
