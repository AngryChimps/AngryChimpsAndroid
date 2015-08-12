package com.angrychimps.citizenvet.models_volley;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static com.angrychimps.citizenvet.App.PAYLOAD;

public class Member {
    public static final String ID = "id";
    public static final String FIRST = "first";
    public static final String LAST = "last";
    public static final String TITLE = "title";
    public static final String EMAIL = "email";
    public static final String PHOTO = "photo";
    public static final String PASSWORD = "password";
    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String photoUrl;
    private String password;

    public Member() {
    }

    public Member(JSONObject object){
        Log.i(null, "member object = " + object.toString());
        try {
            id = object.getString(ID);
            firstName = object.optString(FIRST);
            lastName = object.optString(LAST);
            title = object.optString(TITLE);
            email = object.optString(EMAIL);
            photoUrl = object.optString(PHOTO);
            password = object.optString(PASSWORD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJSONObject(){
        JSONObject object = new JSONObject();
        JSONObject payload = new JSONObject();
        try {
            if(id != null) payload.put(ID, id);
            if(firstName != null) payload.put(FIRST, firstName);
            if(lastName != null) payload.put(LAST, lastName);
            if(title != null) payload.put(TITLE, title);
            if(email != null) payload.put(EMAIL, email);
            if(photoUrl != null) payload.put(PHOTO, photoUrl);
            if(password != null) payload.put(PASSWORD, password);
            object.put(PAYLOAD, payload);
            Log.i(null, "object = " + object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(null, "member getJSONObject = "+object.toString());
        return object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
