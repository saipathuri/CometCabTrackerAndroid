package me.saipathuri.cabtracker.tasks;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

/**
 * Created by saipathuri on 10/14/17.
 */

public class UpdateInformationTimerTask implements Runnable {
    private static final String TAG = "UpdateInfoTimerTask";
    private Handler handler = new Handler();
    private Context context;
    private UpdateCabInformationTask updateCabInformationTask;

    public UpdateInformationTimerTask(Context context) {
        this.context = context;
        updateCabInformationTask = new UpdateCabInformationTask(context);
    }

    @Override
    public void run() {
        Log.d(TAG, "Timer task run");
        handler.post(updateCabInformationTask);
        handler.postDelayed(this, 5000);
    }

    public void stop(){
        handler.removeCallbacks(this);
    }
}
