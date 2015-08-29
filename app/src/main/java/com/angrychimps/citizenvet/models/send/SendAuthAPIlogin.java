package com.angrychimps.citizenvet.models.send;

import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class SendAuthAPIlogin {
    abstract AuthPayload payload();

    @AutoParcelGson
    abstract static class AuthPayload{
        @Nullable public abstract String email();
        @Nullable public abstract String password();
        @Nullable @SerializedName("fb_id") public abstract String facebookId();
        @Nullable @SerializedName("fb_auth_token") public abstract String facebookAuthToken();

        AuthPayload(){
        }
    }

    SendAuthAPIlogin(){
    }

    public static SendAuthAPIlogin login(String email, String password){
        return new AutoParcelGson_SendAuthAPIlogin(new AutoParcelGson_SendAuthAPIlogin_AuthPayload(email, password, null, null));
    }

    public static SendAuthAPIlogin loginFB(String facebookId, String facebookAuthToken){
        return new AutoParcelGson_SendAuthAPIlogin(new AutoParcelGson_SendAuthAPIlogin_AuthPayload(null, null, facebookId, facebookAuthToken));
    }
}
