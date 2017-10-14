package me.saipathuri.cabtracker.services;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import me.saipathuri.cabtracker.dtos.CabDTO;
import me.saipathuri.cabtracker.dtos.CabResponseDTO;
import me.saipathuri.cabtracker.tasks.UpdateCabInformationTask;
import me.saipathuri.cabtracker.tasks.UpdateInformationTimerTask;
import me.saipathuri.cabtracker.utils.NetworkUtils;

public class UpdateCabInformationService extends Service {
    String TAG = "UpdateCabInfoService";
    private Thread updateInfoThread;
    private UpdateInformationTimerTask updateInformationTimerTask;

    public UpdateCabInformationService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        updateInformationTimerTask = new UpdateInformationTimerTask(this);
        updateInfoThread = new Thread(updateInformationTimerTask);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service started");
        updateInfoThread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        updateInformationTimerTask.stop();
        updateInfoThread.interrupt();
        super.onDestroy();
    }
}
