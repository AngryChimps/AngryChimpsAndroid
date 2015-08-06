package com.angrychimps.citizenvet.server;

import android.support.annotation.IntDef;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.angrychimps.citizenvet.App;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.angrychimps.citizenvet.App.URL;
import static com.angrychimps.citizenvet.App.VOLLEY_ERROR;


/*
    This class initiates all Volley requests, returning the respective JsonObject, unpacked from its payload, when finished
 */
public class VolleyRequest extends Request<JSONObject> {

    private static final String SESSION_TOKEN = "angrychimps-api-session-token";
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private Listener<JSONObject> listener;
    private Map<String, String> params;
    private String requestBody;

    public VolleyRequest(@RequestMethod int method, String url, JSONObject requestObject, Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, URL + url, errorListener);
        requestBody = requestObject.toString();
        this.listener = responseListener;
    }

    public VolleyRequest(@RequestMethod int method, String url, Map<String, String> params, JSONObject requestObject, Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, URL + url, errorListener);
        requestBody = requestObject.toString();
        this.listener = responseListener;
        this.params = params;
    }

    public VolleyRequest(@RequestMethod int method, String url, Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, URL + url, errorListener);
        this.listener = responseListener;
    }

    public VolleyRequest(@RequestMethod int method, String url, Map<String, String> params, Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, URL + url, errorListener);
        this.listener = responseListener;
        this.params = params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put(SESSION_TOKEN, App.getInstance().getSessionId());
        return headers;
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() {
        try {
            return requestBody == null ? null : requestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override public void deliverError(VolleyError error) {
        Log.d(VOLLEY_ERROR, "error: " + error.toString());
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
        if(error.networkResponse.statusCode == 403) App.getInstance().getNewSessionId();
        super.deliverError(error);
    }
//    private final OnVolleyResponseListener listener;
//
//    public VolleyRequest(Context context) {
//        listener = (OnVolleyResponseListener) context;
//    }
//
//    public VolleyRequest(Fragment fragment) {
//        listener = (OnVolleyResponseListener) fragment;
//    }
//
//    public void makeRequest(@RequestMethod int method, String urlString){
//        makeRequest(method, urlString, null);
//    }
//
//    public void makeRequest(@RequestMethod int method, String urlString, JSONObject requestObject){
//        JsonObjectRequest request = new JsonObjectRequest(method, URL + urlString, requestObject, this, this) {
//            // Attach headers
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put(SESSION_TOKEN, App.getInstance().getSessionId());
//                return params;
//            }
//        };
//        VolleySingleton.VOLLEY.addToRequestQueue(request);
//    }
//
//    public void makeRequest(@RequestMethod int method, Map<String, String> parameters, String urlString){
//        makeRequest(method, parameters, urlString, null);
//    }
//
//    public void makeRequest(@RequestMethod int method, final Map<String, String> parameters, String urlString, JSONObject requestObject){
//        JsonObjectRequest request = new JsonObjectRequest(method, URL + urlString, requestObject, this, this) {
//            // Attach headers
//            @Override public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put(SESSION_TOKEN, App.getInstance().getSessionId());
//                return params;
//            }
//            @Override protected Map<String, String> getParams() throws AuthFailureError {
//                return parameters;
//            }
//        };
//        VolleySingleton.VOLLEY.addToRequestQueue(request);
//    }
//
//    public void requestSessionId(){
//        VolleySingleton.VOLLEY.addToRequestQueue(new JsonObjectRequest(POST, URL + "session", new SessionAPI().getRequestObject(), this, this));
//    }

//    @Override
//    public void onResponse(JSONObject object) {
//        try {
//            listener.onVolleyResponse(object.getJSONObject(PAYLOAD));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onErrorResponse(VolleyError error) {
//        Log.d(VOLLEY_ERROR, "error: "+error.toString());
//        JSONObject jsonError = null;
//        try {
//            String message = new String(error.networkResponse.data);
//            Log.d(VOLLEY_ERROR, "message = "+message);
//            jsonError = new JSONObject(message);
//            Log.d(VOLLEY_ERROR, "error message: "+jsonError.getString("error"));
//            Log.d(VOLLEY_ERROR, "debug message: "+jsonError.getString("debug"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if(error.networkResponse.statusCode == 403) App.getInstance().getNewSessionId();
//    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Method.GET, Method.PUT, POST, Method.PATCH, Method.DELETE})
    public @interface RequestMethod {
    }
}
