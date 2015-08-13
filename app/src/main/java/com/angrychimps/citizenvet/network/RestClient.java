package com.angrychimps.citizenvet.network;

import android.util.Log;

import com.angrychimps.citizenvet.events.SessionIdReceivedEvent;
import com.angrychimps.citizenvet.models.receive.SessionId;
import com.angrychimps.citizenvet.models.send.SessionRequest;
import com.angrychimps.citizenvet.models.shared.Member;
import com.angrychimps.citizenvet.network.api.MemberAPI;
import com.angrychimps.citizenvet.network.api.SessionAPI;
import com.angrychimps.citizenvet.network.utils.PayloadSerializer;
import com.angrychimps.citizenvet.utils.Device;
import com.angrychimps.citizenvet.utils.Otto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public enum RestClient {
    API;

    private final RestAdapter restAdapter;
    private String sessionId;
    //private String userId;

    RestClient(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(SessionRequest.class, new PayloadSerializer<SessionRequest>())
                .registerTypeAdapter(Member.class, new PayloadSerializer<Member>())
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                Log.i(null, "sessionId = " + sessionId);
                if(sessionId != null) request.addHeader("angrychimps-api-session-token", sessionId);
                //if(userId != null) request.addQueryParam("userId", userId);
            }
        };

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://devvy3.angrychimps.com/api/v1")
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(requestInterceptor)
                .build();

        restAdapter.create(SessionAPI.class).post(new SessionRequest("", new Device().getDescription()), new Callback<SessionId>() {
            @Override public void success(SessionId sessionId, Response response) {
                RestClient.this.sessionId = sessionId.get();
                Otto.BUS.getBus().post(new SessionIdReceivedEvent());
            }

            @Override public void failure(RetrofitError error) {
                Log.i(null, "FAILURE TO GET SESSION ID");
            }
        });
    }

    public MemberAPI member(){
        return restAdapter.create(MemberAPI.class);
    }

}
