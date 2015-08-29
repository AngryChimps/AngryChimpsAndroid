package com.angrychimps.citizenvet.models.send;

import android.support.annotation.Nullable;

import java.util.Date;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class SendInquiryAPI {
    abstract InquiryPayload payload();

    public static SendInquiryAPI create(InquiryPayload payload){
        return new AutoParcelGson_SendInquiryAPI(payload);
    }

    public static InquiryPayload.Builder builder() {
        return new AutoParcelGson_SendInquiryAPI_InquiryPayload.Builder();
    }

    @AutoParcelGson
    abstract static class InquiryPayload{
        @Nullable @SerializedName("location_id") abstract String locationId();
        @Nullable abstract String first();
        @Nullable abstract String last();
        @Nullable abstract String phone();
        @Nullable @SerializedName("best_time") abstract String bestTime();
        @Nullable abstract String email();
        @Nullable abstract String password();
        @Nullable @SerializedName("pet_name") abstract String petName();
        @Nullable @SerializedName("preferred_provider_staff_id") abstract String preferredProvider();
        @Nullable abstract String description();
        @Nullable @SerializedName("first_time") abstract Date firstTime();
        @Nullable @SerializedName("second_time") abstract Date secondTime();
        @Nullable @SerializedName("third_time") abstract Date thirdTime();
        @Nullable @SerializedName("new_patient") abstract Boolean newPatient();

        InquiryPayload(){
        }

        @AutoParcelGson.Builder
        public abstract static class Builder {
            public abstract Builder locationId(String locationId);
            public abstract Builder first(String first);
            public abstract Builder last(String last);
            public abstract Builder phone(String phone);
            public abstract Builder bestTime(String bestTime);
            public abstract Builder email(String email);
            public abstract Builder password(String password);
            public abstract Builder petName(String petName);
            public abstract Builder preferredProvider(String staffId);
            public abstract Builder description(String description);
            public abstract Builder firstTime(Date time);
            public abstract Builder secondTime(Date time);
            public abstract Builder thirdTime(Date time);
            public abstract Builder newPatient(Boolean newPatient);
            public abstract InquiryPayload build();
        }
    }

    SendInquiryAPI(){
    }
}
