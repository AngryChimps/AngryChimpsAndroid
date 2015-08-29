package com.angrychimps.citizenvet.models.send;

import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class SendAuthAPIreset {
    abstract AuthPayload payload();

    @AutoParcelGson
    abstract static class AuthPayload{
        @Nullable public abstract String email();
        @Nullable public abstract String password();
        @Nullable @SerializedName("reset_code") public abstract Integer resetCode(); //May be String- conflicting api data

        AuthPayload(){
        }
    }

    SendAuthAPIreset(){
    }

    public static SendAuthAPIreset start(String email){
        return new AutoParcelGson_SendAuthAPIreset(new AutoParcelGson_SendAuthAPIreset_AuthPayload(email, null, null));
    }

    public static SendAuthAPIreset finish(String email, String password, Integer resetCode){
        return new AutoParcelGson_SendAuthAPIreset(new AutoParcelGson_SendAuthAPIreset_AuthPayload(email, password, resetCode));
    }
}
