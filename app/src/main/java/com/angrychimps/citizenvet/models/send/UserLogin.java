package com.angrychimps.citizenvet.models.send;


import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Auth API for logging in
 */
@AutoParcelGson
public abstract class UserLogin {
    @Nullable public abstract String email();
    @Nullable public abstract String password();
    @Nullable @SerializedName("fb_id") public abstract String facebookId();
    @Nullable @SerializedName("fb_auth_token") public abstract String facebookAuthToken();

    UserLogin() {
    }

    public static UserLogin login(String email, String password){
        return new AutoParcelGson_UserLogin(email, password, null, null);
    }

    public static UserLogin loginFB(String facebookId, String facebookAuthToken){
        return new AutoParcelGson_UserLogin(null, null, facebookId, facebookAuthToken);
    }
}
