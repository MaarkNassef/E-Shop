package com.example.e_shop;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.e_shop.databinding.ActivityAddressBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Database.EShopDatabaseHelper;
import Listeners.MyLocationListener;
import Session.Session;

public class AddressActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityAddressBinding binding;
    private Button sendLocation;
    private Button getLocation;
    LocationListener locationListener;
    LocationManager locationManager;
    String address = "";

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        sendLocation = findViewById(R.id.gps_send_location_btn);
        getLocation = findViewById(R.id.gps_get_my_location_btn);

        locationListener = new MyLocationListener(getApplicationContext());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,0,locationListener);
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "You are not allowed to access the current location.", Toast.LENGTH_SHORT).show();
        }

        sendLocation = findViewById(R.id.gps_send_location_btn);
        sendLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
                EShopDatabaseHelper db = new EShopDatabaseHelper(getApplicationContext());
                if (!address.equals("")) {
                    db.setDateAndAddressInOrders(db.getOrderId(Session.getInstance().getCustomer().getId()), currentDate, address);
                    startActivity(new Intent(AddressActivity.this, RateActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Choose a valid location.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.gps_map);
        mapFragment.getMapAsync(this);


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
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.04441960,
                31.235711600), 8));
        getLocation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                mMap.clear();
                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> addressList;
                Location loc = null;
                try {
                    loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } catch (SecurityException e) {
                    Toast.makeText(getApplicationContext(), "You did not allowed to access the current location.", Toast.LENGTH_SHORT).show();
                }
                if (loc != null) {
                    LatLng myPosition = new LatLng(loc.getLatitude(),loc.getLongitude());
                    try {
                        addressList = geocoder.getFromLocation(myPosition.latitude, myPosition.longitude, 1);
                        if (!addressList.isEmpty()) {
                            String address1 = "";
                            for (int i = 0; i < addressList.get(0).getMaxAddressLineIndex()+1; i++) {
                                address1 += addressList.get(0).getAddressLine(i)+", ";
                            }
                            address = address1;
                            mMap.addMarker(new MarkerOptions().position(myPosition).title("My Location").snippet(address)).setDraggable(true);
                            Toast.makeText(getApplicationContext(), address, Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        mMap.addMarker(new MarkerOptions().position(myPosition).title("My Location"));
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not Determined yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> addressList;
                try {
                    addressList = geocoder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
                    if (!addressList.isEmpty()) {
                        String address1 = "";
                        for (int i = 0; i < addressList.get(0).getMaxAddressLineIndex()+1; i++) {
                            address1 += addressList.get(0).getAddressLine(i)+", ";
                        }
                        address = address1;
                        Toast.makeText(getApplicationContext(), address, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No Address for this location.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Can't get address.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}