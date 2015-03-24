package com.angrychimps.appname;


import android.app.Application;
import android.content.Context;

public class MainApplication extends Application{

    private static MainApplication sInstance;

    public static MainApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
