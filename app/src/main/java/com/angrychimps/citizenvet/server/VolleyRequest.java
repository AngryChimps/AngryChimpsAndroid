package com.angrychimps.citizenvet.server;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.citizenvet.App;
import com.angrychimps.citizenvet.VolleySingleton;
import com.angrychimps.citizenvet.callbacks.OnVolleyResponseListener;
import com.angrychimps.citizenvet.models.SessionAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.angrychimps.citizenvet.App.PAYLOAD;
import static com.angrychimps.citizenvet.App.URL;


/*
    This class initiates all Volley requests, returning the respective JsonObject, unpacked from its payload, when finished
 */
public class VolleyRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private final OnVolleyResponseListener listener;

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
        JsonObjectRequest request = new JsonObjectRequest(method, URL + urlString, requestObject, this, this) {
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

    public void requestSessionId(){
        VolleySingleton.INSTANCE.addToRequestQueue(new JsonObjectRequest(POST, URL + "session", new SessionAPI().getRequestObject(), this, this));
    }

    @Override
    public void onResponse(JSONObject object) {
        try {
            listener.onVolleyResponse(object.getJSONObject(PAYLOAD));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Volley Error", "error: "+error.toString());
        JSONObject jsonError = null;
        try {
            String message = new String(error.networkResponse.data);
            Log.d("Volley Error", "message = "+message);
            jsonError = new JSONObject(message);
            Log.d("Volley Error", "error message: "+jsonError.getString("error"));
            Log.d("Volley Error", "debug message: "+jsonError.getString("debug"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(error.networkResponse.statusCode == 403) App.getInstance().getNewSessionId();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Request.Method.GET, Request.Method.PUT, POST, Request.Method.PATCH, Request.Method.DELETE})
    public @interface RequestMethod {}
}
