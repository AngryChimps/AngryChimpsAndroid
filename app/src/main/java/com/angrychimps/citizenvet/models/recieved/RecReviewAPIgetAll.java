package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Review;

import java.util.List;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecReviewAPIgetAll {
    abstract PayloadReview payload();

    public int count(){
        return payload().count();
    }

    public List<Review> reviews(){
        return payload().reviews();
    }

    @AutoParcelGson
    static abstract class PayloadReview{
        abstract int count();
        abstract List<Review> reviews();
    }
}
