package com.angrychimps.appname;


import android.app.Application;
import android.content.Context;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation
 */

public class MainApplication extends Application{

    private static MainApplication instance;

    public static MainApplication getInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
