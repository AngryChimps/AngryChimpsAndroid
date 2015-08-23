package com.angrychimps.citizenvet.models.send;


import java.util.Date;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    This is NOT ready on the server side. Expect changes.
 */
@AutoParcelGson
public abstract class Inquiry {
    @SerializedName("location_id") public abstract String locationId();
    public abstract String first();
    public abstract String last();
    public abstract String phone();
    @SerializedName("best_time") public abstract String bestTime();
    public abstract String email();
    public abstract String password();
    @SerializedName("fb_id") public abstract String facebookId();
    @SerializedName("fb_auth_token") public abstract String facebookAuthToken();
    @SerializedName("pet_name") public abstract String petName();
    @SerializedName("preferred_provider_staff_id") public abstract String preferredProviderStaffId();
    public abstract String description();
    @SerializedName("first_time") public abstract Date firstTime();
    @SerializedName("second_time") public abstract Date secondTime();
    @SerializedName("third_time") public abstract Date thirdTime();
    @SerializedName("new_patient") public abstract boolean newPatient();

    Inquiry() {
    }


}
