package com.angrychimps.citizenvet.models.send;

import com.angrychimps.citizenvet.models.base.Review;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class SendReviewAPI {
    abstract Review payload();

    SendReviewAPI(){
    }

    public static SendReviewAPI create(Review review){
        return new AutoParcelGson_SendReviewAPI(review);
    }
}
