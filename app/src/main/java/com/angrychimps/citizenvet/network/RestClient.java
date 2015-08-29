package com.angrychimps.citizenvet.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.angrychimps.citizenvet.models.recieved.RecSessionAPI;
import com.angrychimps.citizenvet.models.send.SendSessionAPI;
import com.angrychimps.citizenvet.network.api.AnimalAPI;
import com.angrychimps.citizenvet.network.api.AuthAPI;
import com.angrychimps.citizenvet.network.api.CompanyAPI;
import com.angrychimps.citizenvet.network.api.InquiryAPI;
import com.angrychimps.citizenvet.network.api.LocationAPI;
import com.angrychimps.citizenvet.network.api.MemberAPI;
import com.angrychimps.citizenvet.network.api.MessageAPI;
import com.angrychimps.citizenvet.network.api.MessagesAPI;
import com.angrychimps.citizenvet.network.api.ReviewAPI;
import com.angrychimps.citizenvet.network.api.SearchAPI;
import com.angrychimps.citizenvet.network.api.ServiceAPI;
import com.angrychimps.citizenvet.network.api.SessionAPI;
import com.angrychimps.citizenvet.network.api.StaffAPI;
import com.angrychimps.citizenvet.utils.Device;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import rx.Subscriber;

public enum RestClient {
    API;

    private final RestAdapter restAdapter;
    private String sessionId;
    //private String userId;

    RestClient() {
        Log.i(null, "RestClient created");
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                Log.i(null, "sessionId = " + sessionId);
                request.addHeader("angrychimps-api-session-token", sessionId);
                //if(userId != null) request.addQueryParam("userId", userId);
            }
        };

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://devvy3.angrychimps.com/api/v1")
                .setConverter(new GsonConverter(getGson()))
                .setRequestInterceptor(requestInterceptor)
                .build();

        checkSession();
    }

    private void checkSession(){
        if(sessionId == null) session()
                .postSession(SendSessionAPI.create("", new Device().getDescription()))
                .timeout(10, TimeUnit.SECONDS)
                .subscribe(new Subscriber<RecSessionAPI>() {
                    @Override public void onCompleted() {
                        Log.i(null, "onCompleted session");
                    }
                    @Override public void onError(Throwable e) {
                        Log.e(null, "error on session id: " + e.toString());
                    }
                    @Override public void onNext(RecSessionAPI session) {
                        sessionId = session.id();
                        Log.i(null, "sessionId = " + sessionId);
                    }
                });
    }

    @NonNull private Gson getGson() {
        return new GsonBuilder()
                    .registerTypeAdapterFactory(new AutoParcelGsonTypeAdapterFactory())
                    .registerTypeAdapter(Date.class, new DateTypeAdapter())
                    .create();
    }

    public AnimalAPI animal() {
        checkSession();
        return restAdapter.create(AnimalAPI.class);
    }

    public AuthAPI auth() {
        checkSession();
        return restAdapter.create(AuthAPI.class);
    }

    public CompanyAPI company() {
        checkSession();
        return restAdapter.create(CompanyAPI.class);
    }

    public InquiryAPI inquiry() {
        checkSession();
        return restAdapter.create(InquiryAPI.class);
    }

    public LocationAPI location() {
        checkSession();
        return restAdapter.create(LocationAPI.class);
    }

    public MemberAPI member() {
        checkSession();
        return restAdapter.create(MemberAPI.class);
    }

    public MessageAPI message() {
        checkSession();
        return restAdapter.create(MessageAPI.class);
    }

    public MessagesAPI messages() {
        checkSession();
        return restAdapter.create(MessagesAPI.class);
    }

    public ReviewAPI review() {
        checkSession();
        return restAdapter.create(ReviewAPI.class);
    }

    public SearchAPI search() {
        checkSession();
        return restAdapter.create(SearchAPI.class);
    }

    public ServiceAPI service() {
        checkSession();
        return restAdapter.create(ServiceAPI.class);
    }

    private SessionAPI session() {
        return restAdapter.create(SessionAPI.class);
    }

    public StaffAPI staff() {
        checkSession();
        return restAdapter.create(StaffAPI.class);
    }
}
