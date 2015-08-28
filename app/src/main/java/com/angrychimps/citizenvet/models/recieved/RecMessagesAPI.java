package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Message;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class RecMessagesAPI {
    abstract PayloadMessages payload();

    public int count(){
        return payload().count();
    }

    public int countUnread(){
        return payload().countUnread();
    }

    public List<Message> messages(){
        return payload().messages();
    }

    @AutoParcelGson
    static abstract class PayloadMessages{
        abstract int count();
        @SerializedName("count_unread") abstract int countUnread();
        abstract List<Message> messages();
    }
}
