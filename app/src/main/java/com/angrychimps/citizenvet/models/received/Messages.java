package com.angrychimps.citizenvet.models.received;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Auth API, inside the response after login
    Used in Messages API, as a response from POST
 */
@AutoParcelGson
public abstract class Messages implements Parcelable {
    public abstract int count();
    @SerializedName("count_unread") public abstract int countUnread();
    @SerializedName("results") public abstract List<MessageDetail> messages();

    Messages() {
    }

    public static Messages create(int count, int countUnread, List<MessageDetail> messages){
        return new AutoParcelGson_Messages(count, countUnread, messages);
    }
}
