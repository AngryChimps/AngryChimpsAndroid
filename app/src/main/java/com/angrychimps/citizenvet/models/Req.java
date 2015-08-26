package com.angrychimps.citizenvet.models;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.angrychimps.citizenvet.models.shared.Address;
import com.angrychimps.citizenvet.models.shared.Hours;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Does not currently include Inquiry API
 */


@AutoParcelGson
public abstract class Req implements Parcelable {

    public abstract Payload payload();

    Req() {
    }

    private static Req create(Payload payload){
        return new AutoParcelGson_Req(payload);
    }

    public static Req login(String email, String password){
        return create(Payload.builder().email(email).password(password).build());
    }

    public static Req loginFB(String facebookId, String facebookAuthToken){
        return create(Payload.builder().facebookId(facebookId).facebookAuthToken(facebookAuthToken).build());
    }

    public static Req resetPasswordStart(String email){
        return create(Payload.builder().email(email).build());
    }

    public static Req resetPasswordFinish(String email, String password, String resetCode){
        return create(Payload.builder().email(email).password(password).resetCode(resetCode).build());
    }

    public static Req postCompany(String name, String email, String website, Address billingAddress){
        return create(Payload.builder().name(name).email(email).website(website).billingAddress(billingAddress).build());
    }

    public static Req patchCompany(String name, String email, String website, Address billingAddress, int plan){
        return create(Payload.builder().name(name).email(email).website(website).billingAddress(billingAddress).plan(plan).build());
    }

    public static Req postLocation(String companyId, String name, String description, Address address, boolean isMobile,
                                   float mobileRadiusMiles,  int[] services, int[] animals, Hours hours, List<String> photos){
        return create(Payload.builder().companyId(companyId).name(name).description(description).address(address).isMobile(isMobile)
                .mobileRadiusMiles(mobileRadiusMiles).services(services).animals(animals).hours(hours).photos(photos).build());
    }

    public static Req patchLocation(String name, String description, Address address, boolean isMobile, float mobileRadiusMiles,
                                    int[] services, int[] animals, List<String> staffIds, Hours hours, List<String> photos){
        return create(Payload.builder().name(name).description(description).address(address).isMobile(isMobile).mobileRadiusMiles(mobileRadiusMiles)
                .services(services).animals(animals).staffIds(staffIds).hours(hours).photos(photos).build());
    }

    public static Req postMember(String first, String last, String title, String email, String password, String photo){
        return create(Payload.builder().first(first).last(last).title(title).email(email).password(password).photo(photo).build());
    }

    public static Req patchMember(String first, String last, String title, String email, String password, String photo){
        return create(Payload.builder().first(first).last(last).title(title).email(email).password(password).photo(photo).build());
    }

    public static Req postMessage(String locationId, String sendingMemberId, String repliedMessageId, String body){
        return create(Payload.builder().locationId(locationId).sendingMemberId(sendingMemberId).repliedMessageId(repliedMessageId).body(body).build());
    }

    public static Req patchMessage(String messageId, String status){
        return create(Payload.builder().messageId(messageId).status(status).build());
    }

    public static Req postMessagesMember(String memberId, boolean includeArchived, int limit, int offset){
        return create(Payload.builder().memberId(memberId).includeArchived(includeArchived).limit(limit).offset(offset).build());
    }

    public static Req postMessagesCompany(String companyId, boolean includeArchived, int limit, int offset){
        return create(Payload.builder().companyId(companyId).includeArchived(includeArchived).limit(limit).offset(offset).build());
    }

    public static Req postMessagesLocation(String locationId, boolean includeArchived, int limit, int offset){
        return create(Payload.builder().locationId(locationId).includeArchived(includeArchived).limit(limit).offset(offset).build());
    }

    public static Req postMessagesLocationMember(String locationId, String memberId, int limit, int offset){
        return create(Payload.builder().locationId(locationId).memberId(memberId).limit(limit).offset(offset).build());
    }

    public static Req postReview(String locationId, Float rating, String body, List<String> staffIds){
        return create(Payload.builder().locationId(locationId).rating(rating).body(body).staffIds(staffIds).build());
    }

    public static Req search(Float lat, Float lon, Integer animal, Boolean mobileLocation, Boolean emergency, Boolean walkIn, Integer limit, Integer offset){
        return create(Payload.builder().lat(lat).lon(lon).animal(animal).mobileLocation(mobileLocation).emergency(emergency).walkIn(walkIn).limit(limit).offset(offset).build());
    }

    public static Req session(String pushToken, String description){
        return create(Payload.builder().deviceType(3).pushToken(pushToken).description(description).build());
    }

    public static Req postStaff(String companyId, String first, String last, String title, String position, String education, String email,
                                String description, List<String> locationIds, String photo, Integer role){
        return create(Payload.builder().companyId(companyId).first(first).last(last).title(title).position(position).education(education).email(email)
                .description(description).locationIds(locationIds).photo(photo).role(role).build());
    }

    public static Req patchStaff(String first, String last, String title, String position, String education, String email,
                                 String description, List<String> locationIds, String photo, Integer role){
        return create(Payload.builder().first(first).last(last).title(title).position(position).education(education).email(email)
                .description(description).locationIds(locationIds).build());
    }

    @AutoParcelGson
    public abstract static class Payload{
        @Nullable public abstract Address address();
        @Nullable public abstract Integer animal();
        @Nullable public abstract int[] animals();
        @Nullable @SerializedName("billing_address") public abstract Address billingAddress();
        @Nullable public abstract String body();
        @Nullable @SerializedName("company_id") public abstract String companyId();
        @Nullable public abstract String description();
        @Nullable @SerializedName("device_type") public abstract Integer deviceType();
        @Nullable public abstract String education();
        @Nullable public abstract String email();
        @Nullable public abstract Boolean emergencies();
        @Nullable public abstract Boolean emergency();
        @Nullable @SerializedName("fb_auth_token") public abstract String facebookAuthToken();
        @Nullable @SerializedName("fb_id") public abstract String facebookId();
        @Nullable public abstract String first();
        @Nullable public abstract Hours hours();
        @Nullable @SerializedName("include_archived") public abstract Boolean includeArchived();
        @Nullable @SerializedName("is_mobile") public abstract Boolean isMobile();
        @Nullable public abstract String last();
        @Nullable public abstract Float lat();
        @Nullable public abstract Integer limit();
        @Nullable @SerializedName("location_id") public abstract String locationId();
        @Nullable @SerializedName("location_ids") public abstract List<String> locationIds();
        @Nullable public abstract Float lon();
        @Nullable @SerializedName("member_id") public abstract String memberId();
        @Nullable @SerializedName("message_id") public abstract String messageId();
        @Nullable @SerializedName("mobile_location") public abstract Boolean mobileLocation();
        @Nullable @SerializedName("mobile_radius_miles") public abstract Float mobileRadiusMiles();
        @Nullable public abstract String name();
        @Nullable public abstract Integer offset();
        @Nullable public abstract String password();
        @Nullable public abstract String photo();
        @Nullable public abstract List<String> photos();
        @Nullable public abstract Integer plan();
        @Nullable public abstract String position();
        @Nullable @SerializedName("push_token") public abstract String pushToken();
        @Nullable public abstract Float rating();
        @Nullable @SerializedName("replied_message_id") public abstract String repliedMessageId();
        @Nullable @SerializedName("reset_code") public abstract String resetCode();
        @Nullable public abstract Integer role();
        @Nullable @SerializedName("sending_member_id") public abstract String sendingMemberId();
        @Nullable public abstract int[] services();
        @Nullable @SerializedName("staff_ids") public abstract List<String> staffIds();
        @Nullable public abstract String status();
        @Nullable public abstract String title();
        @Nullable @SerializedName("walk_in") public abstract Boolean walkIn();
        @Nullable @SerializedName("walk_ins") public abstract Boolean walkIns();
        @Nullable public abstract String website();

        Payload(){
        }

        @AutoParcelGson.Builder
        abstract static class Builder {
            public abstract Builder address(Address address);
            public abstract Builder animal(Integer animal);
            public abstract Builder animals(int[] animals);
            public abstract Builder billingAddress(Address billingAddress);
            public abstract Builder body(String body);
            public abstract Builder companyId(String companyId);
            public abstract Builder description(String description);
            public abstract Builder deviceType(Integer deviceType);
            public abstract Builder education(String education);
            public abstract Builder email(String email);
            public abstract Builder emergencies(Boolean emergencies);
            public abstract Builder emergency(Boolean emergency);
            public abstract Builder facebookAuthToken(String facebookAuthToken);
            public abstract Builder facebookId(String facebookId);
            public abstract Builder first(String first);
            public abstract Builder hours(Hours hours);
            public abstract Builder includeArchived(Boolean includeArchived);
            public abstract Builder isMobile(Boolean isMobile);
            public abstract Builder last(String last);
            public abstract Builder lat(Float lat);
            public abstract Builder limit(Integer limit);
            public abstract Builder locationId(String locationId);
            public abstract Builder locationIds(List<String> locationIds);
            public abstract Builder lon(Float lon);
            public abstract Builder memberId(String memberId);
            public abstract Builder messageId(String messageId);
            public abstract Builder mobileLocation(Boolean mobileLocation);
            public abstract Builder mobileRadiusMiles(Float mobileRadiusMiles);
            public abstract Builder name(String name);
            public abstract Builder offset(Integer offset);
            public abstract Builder password(String password);
            public abstract Builder photo(String photo);
            public abstract Builder photos(List<String> photos);
            public abstract Builder plan(Integer plan);
            public abstract Builder position(String position);
            public abstract Builder pushToken(String pushToken);
            public abstract Builder rating(Float rating);
            public abstract Builder repliedMessageId(String repliedMessageId);
            public abstract Builder resetCode(String resetCode);
            public abstract Builder role(Integer role);
            public abstract Builder sendingMemberId(String sendingMemberId);
            public abstract Builder services(int[] services);
            public abstract Builder staffIds(List<String> staffIds);
            public abstract Builder status(String status);
            public abstract Builder title(String title);
            public abstract Builder walkIn(Boolean walkIn);
            public abstract Builder walkIns(Boolean walkIns);
            public abstract Builder website(String website);
            public abstract Payload build();
        }

        public static Builder builder() {
            return new AutoParcelGson_Req_Payload.Builder();
        }
    }
}

