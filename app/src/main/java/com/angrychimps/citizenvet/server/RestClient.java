package com.angrychimps.citizenvet.server;

import com.angrychimps.citizenvet.App;
import com.angrychimps.citizenvet.network.SessionAPIs;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestClient {

    private static final String BASE_URL = "http://devvy3.angrychimps.com/api/v1";
    private SessionAPIs sessionAPIs;

    public RestClient(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                //if(we have a session Id)
                request.addHeader("angrychimps-api-session-token", App.getInstance().getSessionId());
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(requestInterceptor)
                .build();

        sessionAPIs = restAdapter.create(SessionAPIs.class);
    }

    public SessionAPIs getSessionAPIs(){
        return sessionAPIs;
    }

}
