package com.angrychimps.citizenvet.models;

import android.os.Build;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static com.angrychimps.citizenvet.App.PAYLOAD;

public class SessionAPI {

    public String getSessionId(JSONObject object) {
        try {
            return object.getJSONObject("session").getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getRequestObject() {
        JSONObject object = new JSONObject();
        JSONObject payload = new JSONObject();
        try {
            payload.put("device_type", 3);
            payload.put("push_token", ""); //TODO: Add push notification token
            payload.put("description", "Android " + Build.VERSION.RELEASE + " API " + Build.VERSION.SDK_INT + " Device: " + getDeviceName());
            object.put(PAYLOAD, payload);
            Log.i(null, "object = "+object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    private String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) return capitalize(model);
        else return capitalize(manufacturer) + " " + model;
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) return "";
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) return s;
        else return Character.toUpperCase(first) + s.substring(1);
    }
}