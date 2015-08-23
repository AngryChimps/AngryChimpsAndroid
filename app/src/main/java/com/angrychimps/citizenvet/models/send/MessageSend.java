package com.angrychimps.citizenvet.models.send;


import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Message API, sent from POST
 */
@AutoParcelGson
public abstract class MessageSend {
    @SerializedName("location_id") public abstract String locationId();
    @SerializedName("sending_member_id") public abstract String sendingMemberId();
    @SerializedName("replied_member_id") public abstract String repliedMemberId();
    public abstract String body();

    MessageSend() {
    }

    public static MessageSend create(String locationId, String sendingMemberId, String repliedMemberId, String body){
        return new AutoParcelGson_MessageSend(locationId, sendingMemberId, repliedMemberId, body);
    }
}
