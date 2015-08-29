package com.angrychimps.citizenvet.models.send;

import com.angrychimps.citizenvet.models.base.Message;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class SendMessageAPI {
    abstract Message payload();

    SendMessageAPI(){
    }

    public static SendMessageAPI create(Message message){
        return new AutoParcelGson_SendMessageAPI(message);
    }
}
