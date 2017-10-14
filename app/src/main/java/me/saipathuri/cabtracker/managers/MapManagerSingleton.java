package me.saipathuri.cabtracker.managers;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by saipathuri on 10/14/17.
 */

public class MapManagerSingleton {
    private static final MapManagerSingleton ourInstance = new MapManagerSingleton();
    private GoogleMap map;

    public static MapManagerSingleton getInstance() {
        return ourInstance;
    }

    private MapManagerSingleton() {
    }

    public GoogleMap getMap() {
        return map;
    }

    public void setMap(GoogleMap map) {
        this.map = map;
    }
}
