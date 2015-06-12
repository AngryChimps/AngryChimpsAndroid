package com.angrychimps.appname.server;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.appname.App;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.callbacks.OnVolleyResponseListener;

import org.json.JSONObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;


/*
    This class initiates all Volley requests, returning the respective JsonObject when finished
 */
public class VolleyRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    OnVolleyResponseListener listener;

    public VolleyRequest(Context context) {
        listener = (OnVolleyResponseListener) context;
    }

    public VolleyRequest(Fragment fragment) {
        listener = (OnVolleyResponseListener) fragment;
    }

    public void makeRequest(@RequestMethod int method, String urlString){
        makeRequest(method, urlString, null);
    }

    public void makeRequest(@RequestMethod int method, String urlString, JSONObject requestObject){
        JsonObjectRequest request = new JsonObjectRequest(method, App.url + urlString, requestObject, this, this) {
            // Attach headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("angrychimps-api-session-token", App.getInstance().getSessionId());
                return params;
            }
        };
        VolleySingleton.INSTANCE.addToRequestQueue(request);
    }

    public void getSessionId(){
        VolleySingleton.INSTANCE.addToRequestQueue(new JsonObjectRequest(Request.Method.GET, App.url + "session", this, this));
    }

    @Override
    public void onResponse(JSONObject object) {
        listener.onVolleyResponse(object);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("VOLLEY ERROR", "error => " + error.toString());
        if(error.networkResponse.statusCode == 403) App.getInstance().getNewSessionId();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Request.Method.GET, Request.Method.PUT, Request.Method.POST, Request.Method.DELETE})
    public @interface RequestMethod {}
}
