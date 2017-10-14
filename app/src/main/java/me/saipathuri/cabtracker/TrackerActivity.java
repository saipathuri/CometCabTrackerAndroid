package me.saipathuri.cabtracker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import me.saipathuri.cabtracker.managers.CabManagerSingleton;
import me.saipathuri.cabtracker.managers.MapManagerSingleton;
import me.saipathuri.cabtracker.services.UpdateCabInformationService;
import me.saipathuri.cabtracker.utils.ImageUtils;

public class TrackerActivity extends FragmentActivity implements OnMapReadyCallback, CabManagerSingleton.OnCabUpdateListener {

    private static final String TAG = "TrackerActivity";
    private GoogleMap mMap;
    CabManagerSingleton cabManager = CabManagerSingleton.getInstance();
    private Intent startUpdateCabInformationService;
    private LatLngBounds UTDBounds = new LatLngBounds(
            new LatLng(Constants.UTD_BOTTOM_LAT, Constants.UTD_BOTTOM_LONG),
            new LatLng(Constants.UTD_TOP_LAT, Constants.UTD_TOP_LONG));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cabManager.setUpdateListener(this);

        startUpdateCabInformationService = new Intent(this, UpdateCabInformationService.class);
    }

    @Override
    protected void onPause() {
        stopService(startUpdateCabInformationService);
        Log.d(TAG, "onPause called");
        super.onPause();
    }

    @Override
    protected void onResume() {
        startService(startUpdateCabInformationService);
        Log.d(TAG, "onResume called");
        super.onResume();
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MapManagerSingleton mapManager = MapManagerSingleton.getInstance();
        if(mapManager.getMap() == null){
            mapManager.setMap(googleMap);
        } else{
            mMap = mapManager.getMap();
        }
        // Move the marker to UTD with zoom level 16
        LatLng UTD = new LatLng(Constants.UTD_CENTER_LAT, Constants.UTD_CENTER_LONG);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UTD, 16));
        // enable buildings
        mMap.setBuildingsEnabled(true);
        // set the camera bounds to UTD
        mMap.setLatLngBoundsForCameraTarget(UTDBounds);
        mMap.setMaxZoomPreference(20);
        mMap.setMinZoomPreference(16);
    }



    @Override
    public void onCabUpdated() {
        HashMap<String, CabManagerSingleton.CabDTOwithMarker> cabs = cabManager.getCabsMap();
        for(String key: cabs.keySet()){
            CabManagerSingleton.CabDTOwithMarker cab = cabs.get(key);
            cab.setLatLng();

            Marker marker = cab.getMarker();
            if(marker == null){
                marker = mMap.addMarker(new MarkerOptions()
                        .position(cab.getLatLng())
                        .rotation(cab.getHeading()-90)
                        .anchor(.5f, .5f)
                        .title(cab.getName())
                        .icon(ImageUtils.bitmapDescriptorFromVector(this, R.drawable.ic_golf_cart)));
                cab.setMarker(marker);
            } else {
                marker.setPosition(cab.getLatLng());
            }
        }
    }
}
