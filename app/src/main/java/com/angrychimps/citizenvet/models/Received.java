package com.angrychimps.citizenvet.models;

import android.support.annotation.Nullable;

import com.angrychimps.citizenvet.models.received.Animal;
import com.angrychimps.citizenvet.models.received.CompanyLocationDetail;
import com.angrychimps.citizenvet.models.received.MessageDetail;
import com.angrychimps.citizenvet.models.received.ReviewDetail;
import com.angrychimps.citizenvet.models.received.SearchResults;
import com.angrychimps.citizenvet.models.shared.Company;
import com.angrychimps.citizenvet.models.shared.CompanyLocation;
import com.angrychimps.citizenvet.models.shared.Member;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class Received {

    public abstract Payload payload();

    Received(){
    }

    @AutoParcelGson
    public abstract static class Payload{

        @Nullable public abstract List<Animal> animals(); //This doesn't currently match the way the server data is returned
        @Nullable public abstract Member member();
        @Nullable public abstract List<Company> companies();
        @Nullable public abstract Company company();
        @Nullable public abstract CompanyLocation location();
        @Nullable public abstract CompanyLocationDetail.Staff staff();
        @Nullable public abstract CompanyLocationDetail.Reviews reviews();
        @Nullable public abstract MessageDetail message();
        //@Nullable public abstract List<MessageDetail> results();
        @Nullable public abstract String count();
        @Nullable @SerializedName("count_unread") public abstract String countUnread();
        @Nullable public abstract ReviewDetail review();
        @Nullable public abstract SearchResults results();
    }
}
