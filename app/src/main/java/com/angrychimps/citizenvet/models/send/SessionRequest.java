package com.angrychimps.citizenvet.models.send;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class SessionRequest {
    @SerializedName("device_type") public abstract int deviceType();
    @SerializedName("push_token") public abstract String pushToken();
    public abstract String description();

    SessionRequest() {
    }

    public static SessionRequest create(String pushToken, String description){
        return new AutoParcelGson_SessionRequest(3, pushToken, description);
    }
}
