package me.saipathuri.cabtracker.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import me.saipathuri.cabtracker.Constants;
import me.saipathuri.cabtracker.dtos.CabResponseDTO;
import me.saipathuri.cabtracker.managers.CabManagerSingleton;

/**
 * Created by saipathuri on 10/13/17.
 */

public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    public static void makeApiCall(final Context context){
        Log.d(TAG, "Making API Call");
        AndroidNetworking.get(Constants.TRACKER_API_ENDPOINT)
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Got response: " + response.toString());
                        CabResponseDTO cabResponse = CabResponseDTO.parseJSON(response.toString());
                        CabManagerSingleton cabManager = CabManagerSingleton.getInstance();
                        cabManager.updateCabs(cabResponse.getCabs());
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "Couldn't get info");
                        if(error.getErrorCode() != 502) {
                            Toast.makeText(context, "Sorry, we were unable to update Cab information", Toast.LENGTH_SHORT).show();
                        }
                        Log.d(TAG, "error code: " + error.getErrorCode());
                        error.printStackTrace();
                    }
                });
    }
}
