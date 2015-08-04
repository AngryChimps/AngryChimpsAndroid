package com.angrychimps.citizenvet.models;

import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import static com.angrychimps.citizenvet.App.PAYLOAD;

public class SessionAPI {

    public static String getSessionId(JSONObject object) {
        try {
            return object.getJSONObject("session").getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getRequestObject() {
        JSONObject object = new JSONObject();
        JSONObject payload = new JSONObject();
        try {
            payload.put("device_type", 3);
            payload.put("push_token", ""); //TODO: Add push notification token
            payload.put("description", "Android " + Build.VERSION.RELEASE + " API " + Build.VERSION.SDK_INT);
            object.put(PAYLOAD, payload);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}