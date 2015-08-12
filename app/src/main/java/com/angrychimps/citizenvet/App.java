package com.angrychimps.citizenvet;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.citizenvet.events.SessionIdReceivedEvent;
import com.angrychimps.citizenvet.models_volley.SessionAPI;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Request.Method.POST;
import static com.angrychimps.citizenvet.VolleySingleton.VOLLEY;
import static com.angrychimps.citizenvet.utils.Otto.BUS;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation. It also stores the sessionId,
    which remains constant once acquired
 */

public class App extends Application implements Response.Listener<JSONObject>, Response.ErrorListener{

    public static final String URL = "http://devvy3.angrychimps.com/api/v1/";
    public static final String MEDIA_URL = "http://devvy3.angrychimps.com/media/";
    public static final String PAYLOAD = "payload";
    public static final String VOLLEY_ERROR = "Volley Error";
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
        VOLLEY.addToRequestQueue(new JsonObjectRequest(POST, URL + "session", new SessionAPI().getRequestObject(), this, this));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getNewSessionId();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.i(null, "response for sessionId: "+response.toString());
        sessionId = new SessionAPI().getSessionId(response);
        Log.i("sessionId = ", "" + sessionId);
        BUS.getBus().post(new SessionIdReceivedEvent());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(null, "error receiving sessionId: " + error.toString());
        JSONObject jsonError;
        try {
            String message = new String(error.networkResponse.data);
            Log.d(VOLLEY_ERROR, "message = "+message);
            jsonError = new JSONObject(message);
            Log.d(VOLLEY_ERROR, "error message: "+jsonError.getString("error"));
            Log.d(VOLLEY_ERROR, "debug message: "+jsonError.getString("debug"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
