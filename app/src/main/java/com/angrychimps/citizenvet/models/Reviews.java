package com.angrychimps.citizenvet.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Reviews {
    private int count;
    @SerializedName("results") private List<ReviewDetail> reviews;

    public Reviews() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ReviewDetail> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDetail> reviews) {
        this.reviews = reviews;
    }

    public static class ReviewDetail{
        //TODO: does this need to be in a payload??
        private String id;
        private String reviewerName;
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
}
