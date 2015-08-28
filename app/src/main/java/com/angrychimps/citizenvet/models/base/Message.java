package com.angrychimps.citizenvet.models.base;

import android.os.Parcelable;

import java.util.Date;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class Message implements Parcelable {
    @SerializedName("message_id") public abstract String messageId();
    @SerializedName("location_id") public abstract String locationId();
    @SerializedName("consumer_message_id") public abstract String consumerMessageId();
    @SerializedName("replied_message_id") public abstract String repliedMessageId();
    @SerializedName("sender_name") public abstract String senderName();
    public abstract String body();
    public abstract String status();
    @SerializedName("created_at") public abstract Date createdAt();

    Message() {
    }

    public static Message create(String messageId, String locationId, String consumerMessageId, String repliedMessageId, String senderName, String body, String status, Date createdAt){
        return new AutoParcelGson_Message(messageId, locationId, consumerMessageId, repliedMessageId, senderName, body, status, createdAt);
    }
}
