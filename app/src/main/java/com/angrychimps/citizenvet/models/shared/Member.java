package com.angrychimps.citizenvet.models.shared;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;

/*
    Used in Member API, for both send and receive
 */
@AutoParcelGson
public abstract class Member implements Parcelable {
    @Nullable public abstract String id(); //receive only
    @Nullable public abstract String first();
    @Nullable public abstract String last();
    @Nullable public abstract String title();
    @Nullable public abstract String email();
    @Nullable public abstract String password(); //send only
    @Nullable public abstract String photo();

    Member() {
    }
}
