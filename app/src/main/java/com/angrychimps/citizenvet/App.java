package com.angrychimps.citizenvet;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.angrychimps.citizenvet.callbacks.OnVolleyResponseListener;
import com.angrychimps.citizenvet.events.SessionIdReceivedEvent;
import com.angrychimps.citizenvet.models.SessionAPI;
import com.angrychimps.citizenvet.server.VolleyRequest;
import com.angrychimps.citizenvet.utils.Otto;

import org.json.JSONObject;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation. It also stores the sessionId,
    which remains constant once acquired
 */

public class App extends Application implements OnVolleyResponseListener{

    public static final String URL = "http://devvy3.angrychimps.com/api/v1/";
    public static final String MEDIA_URL = "http://devvy3.angrychimps.com/media/";
    public static final String PAYLOAD = "payload";
    private String sessionId; //SessionOutput ID required for all server calls
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
        new VolleyRequest(this).requestSessionId();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getNewSessionId();
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        sessionId = new SessionAPI().getSessionId(object);
        Log.i("sessionId = ", "" + sessionId);
        Otto.BUS.getBus().post(new SessionIdReceivedEvent());
    }
}
