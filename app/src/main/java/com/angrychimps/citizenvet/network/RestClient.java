package com.angrychimps.citizenvet.network;

import android.util.Log;

import com.angrychimps.citizenvet.models.Member;
import com.angrychimps.citizenvet.models.Session;
import com.angrychimps.citizenvet.network.api.MemberAPI;
import com.angrychimps.citizenvet.network.api.SessionAPI;
import com.angrychimps.citizenvet.network.utils.PayloadSerializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public enum RestClient {
    API;

    private final RestAdapter restAdapter;
    private String sessionId;

    RestClient(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Session.class, new PayloadSerializer<Session>())
                .registerTypeAdapter(Member.class, new PayloadSerializer<Member>())
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                Log.i(null, "sessionId = " + sessionId);
                if(sessionId != null) request.addHeader("angrychimps-api-session-token", sessionId);
            }
        };

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://devvy3.angrychimps.com/api/v1")
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(requestInterceptor)
                .build();
    }

    public SessionAPI session(){
        return restAdapter.create(SessionAPI.class);
    }

    public MemberAPI member(){
        return restAdapter.create(MemberAPI.class);
    }

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }
}
