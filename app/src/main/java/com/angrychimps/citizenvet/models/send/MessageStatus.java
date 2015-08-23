package com.angrychimps.citizenvet.models.send;


import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Message API, sent from PATCH to change message status
 */
@AutoParcelGson
public abstract class MessageStatus {
    @SerializedName("message_id") public abstract String messageId();
    public abstract String status();

    MessageStatus() {
    }

    public static MessageStatus create(String messageId, String status){
        return new AutoParcelGson_MessageStatus(messageId, status);
    }
}
