package me.saipathuri.cabtracker;

import android.app.Application;
import android.content.res.Configuration;

import com.androidnetworking.AndroidNetworking;

/**
 * Created by saipathuri on 10/13/17.
 */

public class CabTracker extends Application {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
