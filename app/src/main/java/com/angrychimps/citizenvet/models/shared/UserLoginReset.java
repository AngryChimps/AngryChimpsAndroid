package com.angrychimps.citizenvet.models.shared;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Auth API
    send: start: provide only email  finish: provide all
    receive resetCode after start
 */
@AutoParcelGson
public abstract class UserLoginReset implements Parcelable{
    public abstract String email();
    @Nullable public abstract String password();
    @Nullable @SerializedName("reset_code") public abstract String resetCode();

    UserLoginReset() {
    }

    public static UserLoginReset create(String email){
        return new AutoParcelGson_UserLoginReset(email, null, null);
    }

    public static UserLoginReset create(String email, String password, String resetCode){
        return new AutoParcelGson_UserLoginReset(email, password, resetCode);
    }
}
