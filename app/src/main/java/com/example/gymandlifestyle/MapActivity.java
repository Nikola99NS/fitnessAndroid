package com.example.gymandlifestyle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gymandlifestyle.databinding.ActivityMapBinding;

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String gym="";

//    private ActivityMapBinding binding;
//
//    List<Address> listGeoCoder;
    private static final int LOCATION_PERMISSION_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        if(isLocationPermissionGranted()) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

//        }
            Intent intent = this.getIntent();


            if (intent != null) {
                gym = intent.getStringExtra("gym");

            }

        }else{
            requestLocationPermission();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(gym.equals("Prime")){
            LatLng prime = new LatLng(45.257603, 19.843519);
            mMap.addMarker(new MarkerOptions().position(prime).title("Prime"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.257603, 19.843519), 14.0f));
        }else if(gym.equals("Synergy")){
            LatLng Synergy = new LatLng(45.260245, 19.809585);
            mMap.addMarker(new MarkerOptions().position(Synergy).title("Synergy"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.260245, 19.809585), 14.0f));
        }else if(gym.equals("Non stop")){
            LatLng nonStopFitness = new LatLng(45.253439, 19.842166);
            mMap.addMarker(new MarkerOptions().position(nonStopFitness).title("Non stop"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.253439, 19.842166), 14.0f));
        }else if(gym.equals("X-gym")){
            LatLng xGym = new LatLng(45.25457, 19.806129);
            mMap.addMarker(new MarkerOptions().position(xGym).title("X-gym"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.25457, 19.806129), 14.0f));
        }else if(gym.equals("Edu fit")){
            LatLng eduFit = new LatLng(45.227143, 19.851478);
            mMap.addMarker(new MarkerOptions().position(eduFit).title("Edu fit"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.227143, 19.851478), 14.0f));
        }else if(gym.equals("Classic gym")){
            LatLng classic = new LatLng(45.254102, 19.836213);
            mMap.addMarker(new MarkerOptions().position(classic).title("Classic gym"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.254102, 19.836213), 14.0f));
        }else if(gym.equals("Ozzy")){
            LatLng ozzy = new LatLng(45.24037, 19.816989);
            mMap.addMarker(new MarkerOptions().position(ozzy).title("Ozzy gym"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.24037, 19.816989), 14.0f));
        }else if(gym.equals("Koloseum")){
            LatLng koloseum = new LatLng(45.248227, 19.803711);
            mMap.addMarker(new MarkerOptions().position(koloseum).title("Koloseum"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.248227, 19.803711), 14.0f));
        }else if(gym.equals("Best fit")){
            LatLng bestFit = new LatLng(45.243688, 19.842252);
            mMap.addMarker(new MarkerOptions().position(bestFit).title("Best fit"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(45.243688, 19.842252), 14.0f));
        }



        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
             mMap.setMyLocationEnabled(true);
        }
    }
//
    private boolean isLocationPermissionGranted(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED ){
            return true;
        }else{
            return false;
        }
    }
//
    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_CODE);
    }

}