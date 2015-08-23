package com.angrychimps.citizenvet.models.send;


import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Messages API to request a list of messages
 */
@AutoParcelGson
public abstract class MessagesRequest {
    @Nullable @SerializedName("location_id") public abstract String locationId();
    @Nullable @SerializedName("member_id") public abstract String memberId();
    @Nullable @SerializedName("company_id") public abstract String companyId();
    @SerializedName("include_archived") public abstract boolean includeArchived();
    public abstract int limit();
    public abstract int offset();

    MessagesRequest() {
    }

    public static MessagesRequest member(String memberId, boolean includeArchived, int limit, int offset){
        return new AutoParcelGson_MessagesRequest(null, memberId, null, includeArchived, limit, offset);
    }

    public static MessagesRequest company(String companyId, boolean includeArchived, int limit, int offset){
        return new AutoParcelGson_MessagesRequest(null, null, companyId, includeArchived, limit, offset);
    }

    public static MessagesRequest location(String locationId, boolean includeArchived, int limit, int offset){
        return new AutoParcelGson_MessagesRequest(locationId, null, null, includeArchived, limit, offset);
    }

    public static MessagesRequest locationMember(String locationId, String memberId, int limit, int offset){
        return new AutoParcelGson_MessagesRequest(locationId, memberId, null, true, limit, offset);
    }
}
