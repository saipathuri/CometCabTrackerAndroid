package me.saipathuri.cabtracker.tasks;

import android.content.Context;
import android.util.Log;

import me.saipathuri.cabtracker.utils.NetworkUtils;

/**
 * Created by saipathuri on 10/14/17.
 */

public class UpdateCabInformationTask implements Runnable {
    private static final String TAG = "UpdateCabInfoTask";
    private final Context context;

    public UpdateCabInformationTask(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(TAG, "Running update cab info task");
        NetworkUtils.makeApiCall(context);
    }
}
