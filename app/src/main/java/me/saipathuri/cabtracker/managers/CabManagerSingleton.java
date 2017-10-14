package me.saipathuri.cabtracker.managers;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.List;

import me.saipathuri.cabtracker.dtos.CabDTO;

/**
 * Created by saipathuri on 10/13/17.
 */

public class CabManagerSingleton {
    private static final CabManagerSingleton ourInstance = new CabManagerSingleton();
    private static final String TAG = "CabManager";
    private HashMap<String, CabDTOwithMarker> cabsMap = new HashMap<String, CabDTOwithMarker>();
    private OnCabUpdateListener cabUpdateListener;


    public static CabManagerSingleton getInstance() {
        return ourInstance;
    }

    public void updateCabs(List<CabDTO> cabs){
        if(this.cabsMap.keySet().size() == 0){
            Log.d(TAG, "No cabs in map, adding all");
            for(CabDTO cab: cabs){
                CabDTOwithMarker currentCab = new CabDTOwithMarker(cab);
                this.cabsMap.put(currentCab.getName(), currentCab);
            }
        }else{
            Log.d(TAG, "Cabs in map, updating all");
            for(CabDTO cab: cabs){
                CabDTOwithMarker currentCab = this.cabsMap.get(cab.getName());
                currentCab.updateCab(cab);
            }
        }
        cabUpdateListener.onCabUpdated();
    }

    public HashMap<String, CabDTOwithMarker> getCabsMap(){
        return cabsMap;
    }

    private CabManagerSingleton() {
    }

    public void setUpdateListener(OnCabUpdateListener onCabUpdateListener){
        this.cabUpdateListener = onCabUpdateListener;
    }

    public interface OnCabUpdateListener {
        void onCabUpdated();
    }

    public class CabDTOwithMarker extends CabDTO{
        private LatLng latLng;
        private Marker marker;

        public CabDTOwithMarker(String name, String id, int heading, boolean ignition, String latitude, String longitude, String moved, String updated, int velocity) {
            super(name, id, heading, ignition, latitude, longitude, moved, updated, velocity);
            latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        }

        public CabDTOwithMarker(CabDTO cab) {
            super(cab.getName(), cab.getId(), cab.getHeading(), cab.isIgnition(), cab.getLatitude(), cab.getLongitude(), cab.getMoved(), cab.getUpdated(), cab.getVelocity());
            setLatLng();
        }

        public void setLatLng(){
            latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        }

        public void setMarker(Marker marker){
            this.marker = marker;
        }

        public Marker getMarker() {
            return marker;
        }

        public LatLng getLatLng() {
            return latLng;
        }

        public void updateCab(CabDTO cab){
            setHeading(cab.getHeading());
            setIgnition(cab.isIgnition());
            setLatitude(cab.getLatitude());
            setLongitude(cab.getLongitude());
            setMoved(cab.getMoved());
            setUpdated(cab.getUpdated());
            setVelocity(cab.getVelocity());
        }
    }
}
