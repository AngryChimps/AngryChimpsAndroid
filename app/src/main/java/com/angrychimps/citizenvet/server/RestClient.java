package com.angrychimps.citizenvet.server;

import android.util.Log;

import com.angrychimps.citizenvet.models.Members;
import com.angrychimps.citizenvet.models.Sessions;
import com.angrychimps.citizenvet.network.MemberAPIs;
import com.angrychimps.citizenvet.network.SessionAPIs;
import com.angrychimps.citizenvet.utils.PayloadSerializer;
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
                .registerTypeAdapter(Sessions.class, new PayloadSerializer<Sessions>())
                .registerTypeAdapter(Members.class, new PayloadSerializer<Members>())
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

    public SessionAPIs session(){
        return restAdapter.create(SessionAPIs.class);
    }

    public MemberAPIs member(){
        return restAdapter.create(MemberAPIs.class);
    }

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }
}
