package com.angrychimps.citizenvet.utils;

import android.os.Build;

/*
    Provides a description of the user's device, in the format:
    "Android 5.1 Lollipop, API 22, Device: Nexus 6"
 */
public class Device {
    private String description;

    public Device() {
        description = "Android " + Build.VERSION.RELEASE + ", "+ Build.VERSION.CODENAME + " API " + Build.VERSION.SDK_INT + ", Device: " + getDeviceName();
    }

    public String getDescription() {
        return description;
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
