package com.angrychimps.appname;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.angrychimps.appname.events.SessionIdReceivedEvent;
import com.angrychimps.appname.callbacks.OnVolleyResponseListener;
import com.angrychimps.appname.models.SessionGetResponsePayload;
import com.angrychimps.appname.server.VolleyRequest;
import com.angrychimps.appname.utils.Otto;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation. It also stores the sessionId,
    which remains constant once acquired
 */

public class App extends Application implements OnVolleyResponseListener{

    public static final String url = "http://devvy3.angrychimps.com/api/v1/";
    public static final String mediaUrl = "http://devvy3.angrychimps.com/media/";
    private String sessionId; //Session ID required for all server calls
    private static App instance;

    public static App getInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void getNewSessionId(){
        new VolleyRequest(this).getSessionId();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getNewSessionId();
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
