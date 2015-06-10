package com.angrychimps.appname;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.angrychimps.appname.events.SessionIdReceivedEvent;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.models.SessionGetResponsePayload;
import com.angrychimps.appname.server.VolleyRequest;
import com.angrychimps.appname.utils.Otto;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation
 */

public class App extends Application implements OnVolleyResponseListener{

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
        new VolleyRequest(this).getSessionId();
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        try {
            sessionId = LoganSquare.parse(object.getString("payload"), SessionGetResponsePayload.class).getSession_id();
            Log.i("sessionId = ", "" + sessionId);
            Otto.BUS.getBus().post(new SessionIdReceivedEvent());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
