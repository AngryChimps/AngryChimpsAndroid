package com.angrychimps.appname;


import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.models.SessionGetResponsePayload;
import com.angrychimps.appname.utils.DeviceLocation;
import com.angrychimps.appname.utils.VolleyRequest;
import com.bluelinelabs.logansquare.LoganSquare;
import com.squareup.otto.Bus;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation, as well as
    storing static variables
 */

public class App extends Application implements OnVolleyResponseListener{

    public static final String url = "http://devvy3.angrychimps.com/api/v1/";
    public static final String mediaUrl = "http://devvy3.angrychimps.com/media/";
    public static List<SearchPostResponseResults> searchResults = new ArrayList<>();
    public static JSONObject currentRequest = new JSONObject();

    private static Location location;
    private static String sessionId; //Session ID required for all server calls
    private static App instance;
    private static final Bus bus = new Bus();

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

        //Get current location then grab the sessionId
        DeviceLocation.LocationResult locationResult = new DeviceLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                App.location = location;
                Log.i(null, "location latitude == " + App.location.getLatitude() + "and longitude == " + App.location.getLongitude());
                new VolleyRequest(instance).getSessionId();
            }
        };
        new DeviceLocation().getLocation(this, locationResult);
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        App.sessionId = "";
        try {
            SessionGetResponsePayload session = LoganSquare.parse(object.getString("payload"), SessionGetResponsePayload.class);
            App.sessionId = session.getSession_id();
            Log.i("sessionId = ", "" + App.sessionId);
            //setMainFragment();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
