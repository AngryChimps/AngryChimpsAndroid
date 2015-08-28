package com.angrychimps.citizenvet.models.recieved;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecSessionAPI {
    abstract PayloadSession payload();

    public String id(){
        return payload().session().id();
    }

    @AutoParcelGson
    static abstract class PayloadSession{
        abstract Session session();

        @AutoParcelGson
        static abstract class Session {
            abstract String id();
        }
    }
}
