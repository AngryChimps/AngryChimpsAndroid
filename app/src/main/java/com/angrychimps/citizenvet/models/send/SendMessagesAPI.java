package com.angrychimps.citizenvet.models.send;

import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class SendMessagesAPI {
    abstract MessagesPayload payload();

    public static SendMessagesAPI member(String memberId){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(null, memberId, null, false, 10, 0));
    }

    public static SendMessagesAPI member(String memberId, boolean includeArchived){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(null, memberId, null, includeArchived, 10, 0));
    }

    public static SendMessagesAPI member(String memberId, boolean includeArchived, int limit, int offset){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(null, memberId, null, includeArchived, limit, offset));
    }



    public static SendMessagesAPI company(String companyId){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(null, null, companyId, false, 10, 0));
    }

    public static SendMessagesAPI company(String companyId, boolean includeArchived){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(null, null, companyId, includeArchived, 10, 0));
    }

    public static SendMessagesAPI company(String companyId, boolean includeArchived, int limit, int offset){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(null, null, companyId, includeArchived, limit, offset));
    }



    public static SendMessagesAPI location(String locationId){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(locationId, null, null, false, 10, 0));
    }

    public static SendMessagesAPI location(String locationId, boolean includeArchived){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(locationId, null, null, includeArchived, 10, 0));
    }

    public static SendMessagesAPI location(String locationId, boolean includeArchived, int limit, int offset){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(locationId, null, null, includeArchived, limit, offset));
    }



    public static SendMessagesAPI locationMember(String locationId, String memberId){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(locationId, memberId, null, false, 10, 0));
    }

    public static SendMessagesAPI locationMember(String locationId, String memberId, boolean includeArchived){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(locationId, memberId, null, includeArchived, 10, 0));
    }

    public static SendMessagesAPI locationMember(String locationId, String memberId, boolean includeArchived, int limit, int offset){
        return new AutoParcelGson_SendMessagesAPI(new AutoParcelGson_SendMessagesAPI_MessagesPayload(locationId, memberId, null, includeArchived, limit, offset));
    }

    @AutoParcelGson
    abstract static class MessagesPayload{
        @Nullable @SerializedName("location_id") abstract String locationId();
        @Nullable @SerializedName("member_id") abstract String memberId();
        @Nullable @SerializedName("company_id") abstract String companyId();
        @SerializedName("include_archived") abstract boolean includeArchived();
        abstract int limit();
        abstract int offset();
    }
}
