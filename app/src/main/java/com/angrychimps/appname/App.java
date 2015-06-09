package com.angrychimps.appname;


import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.angrychimps.appname.events.SessionIdReceivedEvent;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.models.SessionGetResponsePayload;
import com.angrychimps.appname.utils.DeviceLocation;
import com.angrychimps.appname.server.VolleyRequest;
import com.bluelinelabs.logansquare.LoganSquare;
import com.squareup.otto.Bus;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation, as well as
    storing static variables TODO: consider splitting these into different singleton methods- research best practices
 */

public class App extends Application implements OnVolleyResponseListener{

    public static final String url = "http://devvy3.angrychimps.com/api/v1/";
    public static final String mediaUrl = "http://devvy3.angrychimps.com/media/";
    public static List<SearchPostResponseResults> searchResults = new ArrayList<>();
    public static JSONObject currentRequest = new JSONObject();

    private static final Bus bus = new Bus();
    private static Location location;
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

    public static double getLatitude() {
        return location.getLatitude();
    }

    public static double getLongitude() {
        return location.getLongitude();
    }

    public static Bus getBus() {
        return bus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void initiate(){
        Log.i(null, "initiate called");
        if(sessionId != null && location != null) {
            Log.i(null, "SendingSessionIdEvent");
            bus.post(new SessionIdReceivedEvent());
        }
        else {
            Log.i(null, "initiate else called");
            //Get current location then grab the sessionId
            new DeviceLocation().getLocation(this, new DeviceLocation.LocationResult() {
                @Override
                public void gotLocation(Location location) {
                    App.location = location;
                    Log.i(null, "latitude == " + getLatitude() + "and longitude == " + getLongitude());
                    new VolleyRequest(instance).getSessionId();
                }
            });
        }
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        Log.i(null, "Volley Response");
        sessionId = "";
        try {
            sessionId = LoganSquare.parse(object.getString("payload"), SessionGetResponsePayload.class).getSession_id();
            Log.i("sessionId = ", "" + sessionId);
            bus.post(new SessionIdReceivedEvent());
            Log.i(null, "Volley Response Event sent");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
