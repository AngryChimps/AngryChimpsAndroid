package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Review;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecReviewAPIget {
    abstract PayloadReview payload();

    public Review review(){
        return payload().review();
    }

    @AutoParcelGson
    static abstract class PayloadReview{
        abstract Review review();
    }
}
