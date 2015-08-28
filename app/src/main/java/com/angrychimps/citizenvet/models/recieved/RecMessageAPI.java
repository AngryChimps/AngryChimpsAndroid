package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Message;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecMessageAPI {
    abstract PayloadMessage payload();

    public Message message(){
        return payload().message();
    }

    @AutoParcelGson
    static abstract class PayloadMessage{
        abstract Message message();
    }
}
