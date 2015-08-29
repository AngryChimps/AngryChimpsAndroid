package com.angrychimps.citizenvet.models.send;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class SendSessionAPI {
    abstract SessionPayload payload();

    SendSessionAPI(){}

    public static SendSessionAPI create(String pushToken, String description){
        return new AutoParcelGson_SendSessionAPI(new AutoParcelGson_SendSessionAPI_SessionPayload(3, pushToken, description));
    }

    @AutoParcelGson
    abstract static class SessionPayload{
        @SerializedName("device_type") abstract int deviceType();
        @SerializedName("push_token") abstract String pushToken();
        abstract String description();

        SessionPayload(){}
    }
}
