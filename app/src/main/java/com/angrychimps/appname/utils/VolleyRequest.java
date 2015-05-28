package com.angrychimps.appname.utils;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;

import org.json.JSONObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;


/*
    This class initiates all Volley requests, returning the respective JsonObject when finished
 */
public class VolleyRequest {

    OnVolleyResponseListener listener;

    public VolleyRequest(Activity activity) {
        listener = (OnVolleyResponseListener) activity;
    }

    public VolleyRequest(Fragment fragment){
        listener = (OnVolleyResponseListener) fragment;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Request.Method.GET, Request.Method.PUT, Request.Method.POST, Request.Method.DELETE})
    public @interface RequestMethod {}

    public void makeRequest(@RequestMethod int method, String urlString, JSONObject requestObject){
        JsonObjectRequest request = new JsonObjectRequest(method, MainActivity.url + urlString, requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                listener.onVolleyResponse(object);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY ERROR", "error => " + error.toString());
                    }
                }) {
            // Attach headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("angrychimps-api-session-token", MainActivity.sessionId);
                return params;
            }
        };
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    public void makeRequest(@RequestMethod int method, String urlString){
        makeRequest(method, urlString, null);
    }

    public void getSessionId(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, MainActivity.url + "session", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                listener.onVolleyResponse(object);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY ERROR", "error => " + error.toString());
                    }
                }
        );
        VolleySingleton.getInstance().addToRequestQueue(request);
    }
}
