package com.angrychimps.appname.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class DeviceLocation {
    Timer timer;
    LocationManager lm;
    LocationResult locationResult;
    int timeBetweenLocationChecks = 60000; //Check device location once per minute
    boolean isGpsEnabled = false;
    boolean isNetworkEnabled = false;

    public boolean getLocation(Context context, LocationResult result) {
        //Use LocationResult callback class to pass location value from DeviceLocation to user code.
        locationResult=result;
        if(lm==null) lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        //don't start listeners if no provider is enabled
        if(!isGpsEnabled && !isNetworkEnabled) return false;

        if(isGpsEnabled) lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
        if(isNetworkEnabled) lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
        timer =new Timer();
        timer.schedule(new GetLastLocation(), timeBetweenLocationChecks);
        return true;
    }

    LocationListener locationListenerGps = new LocationListener() {
        public void onLocationChanged(Location location) {
            timer.cancel();
            locationResult.gotLocation(location);
            lm.removeUpdates(this);
            lm.removeUpdates(locationListenerNetwork);
        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            timer.cancel();
            locationResult.gotLocation(location);
            lm.removeUpdates(this);
            lm.removeUpdates(locationListenerGps);
        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    class GetLastLocation extends TimerTask {
        @Override
        public void run() {
            lm.removeUpdates(locationListenerGps);
            lm.removeUpdates(locationListenerNetwork);

            Location networkLocation = null, gpsLocation = null;
            if(isGpsEnabled) gpsLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(isNetworkEnabled) networkLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            //If both values present, use the latest one
            if(gpsLocation!=null && networkLocation != null){
                if(gpsLocation.getTime() > networkLocation.getTime()) locationResult.gotLocation(gpsLocation);
                else locationResult.gotLocation(networkLocation);
                return;
            }

            if(gpsLocation != null){
                locationResult.gotLocation(gpsLocation);
                return;
            }
            if(networkLocation != null){
                locationResult.gotLocation(networkLocation);
                return;
            }
            locationResult.gotLocation(null);
        }
    }

    public static abstract class LocationResult{
        public abstract void gotLocation(Location location);
    }
}