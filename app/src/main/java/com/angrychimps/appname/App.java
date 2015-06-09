package com.angrychimps.appname;


import android.app.Application;
import android.content.Context;

import com.angrychimps.appname.models.SearchPostResponseResults;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation
 */

public class App extends Application {

    public static final String url = "http://devvy3.angrychimps.com/api/v1/";
    public static final String mediaUrl = "http://devvy3.angrychimps.com/media/";
    public static List<SearchPostResponseResults> searchResults = new ArrayList<>();
    public static JSONObject currentRequest = new JSONObject();

    private static String sessionId; //Session ID required for all server calls
    private static App instance;

    public static App getInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }

    public static String getSessionId() {
        return sessionId;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
