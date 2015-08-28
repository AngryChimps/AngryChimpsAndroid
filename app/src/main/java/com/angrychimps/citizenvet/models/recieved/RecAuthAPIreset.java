package com.angrychimps.citizenvet.models.recieved;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class RecAuthAPIreset {
    abstract PayloadReset payload();

    public String resetCode(){
        return payload().resetCode();
    }

    @AutoParcelGson
    static abstract class PayloadReset{
        @SerializedName("reset_code") abstract String resetCode();
    }
}
