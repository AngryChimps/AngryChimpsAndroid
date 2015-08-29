package com.angrychimps.citizenvet.models.send;

import android.support.annotation.Nullable;

import com.angrychimps.citizenvet.models.base.Address;

import java.util.Date;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class SendSearchAPI {
    abstract SearchPayload payload();

    public static SendSearchAPI create(SearchPayload payload){
        return new AutoParcelGson_SendSearchAPI(payload);
    }

    public static SearchPayload.Builder builder() {
        return SearchPayload.Builder.builder();
    }

    @AutoParcelGson
    abstract static class SearchPayload{
        @Nullable abstract Address address();
        @Nullable abstract Float lat();
        @Nullable abstract Float lon();
        @Nullable abstract Integer animal();
        @Nullable @SerializedName("mobile_location") abstract Boolean mobileLocation();
        @Nullable abstract Boolean emergency();
        @Nullable @SerializedName("walk_in") abstract Boolean walkIn();
        abstract int limit();
        abstract int offset();

        SearchPayload(){
        }

        @AutoParcelGson.Builder
        public abstract static class Builder {
            public abstract Builder address(Address address);
            public abstract Builder lat(Float lat);
            public abstract Builder lon(Float lon);
            public abstract Builder animal(Integer animal);
            public abstract Builder mobileLocation(Boolean mobileLocation);
            public abstract Builder emergency(Boolean emergency);
            public abstract Builder walkIn(Boolean walkIn);
            public abstract Builder limit(int limit);
            public abstract Builder offset(int offset);
            public abstract SearchPayload build();

            public static Builder builder(){
                return new AutoParcelGson_SendSearchAPI_SearchPayload.Builder().limit(10).offset(0);
            }
        }
    }
    SendSearchAPI(){
    }
}
