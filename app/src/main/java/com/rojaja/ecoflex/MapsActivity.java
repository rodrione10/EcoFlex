package com.rojaja.ecoflex;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {
    private GoogleMap mMap;
    private final int FINE_PERMISSION_CODE = 1;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private ArrayList<LatLng> locationArrayList;


    LatLng Mine;
    // creating array list for adding all our locations.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);
        mybottomNavView.setSelectedItemId(R.id.map);
        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()  == R.id.home ) {
                    startActivity(new Intent(getApplicationContext(),Main.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(MapsActivity.this, "Página principal", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (item.getItemId()  == R.id.libro ) {
                    startActivity(new Intent(getApplicationContext(),Info.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(MapsActivity.this, "Artículos sobre reciclaje", Toast.LENGTH_SHORT).show();
                    return true;

                } else if (item.getItemId()  == R.id.map ) {

                    return true;
                } else if (item.getItemId()  == R.id.user ) {
                    startActivity(new Intent(getApplicationContext(),User.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(MapsActivity.this, "Tu perfil", Toast.LENGTH_SHORT).show();
                    return true;
                }

               /* switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Main.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.libro:
                        startActivity(new Intent(getApplicationContext(), Info.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.map:

                        return true;

                    case R.id.user:
                        startActivity(new Intent(getApplicationContext(), User.class));
                        overridePendingTransition(0,0);
                        return true;
                }*/
                return false;
            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        // in below line we are initializing our array list.
        locationArrayList = new ArrayList<>();
        locationArrayList.add(new LatLng(40.392986, -3.698626));
        locationArrayList.add(new LatLng(40.397053, -3.714228));
        locationArrayList.add(new LatLng(40.394233, -3.683357));
        locationArrayList.add(new LatLng(40.401058, -3.704488));
        locationArrayList.add(new LatLng(40.402380, -3.679590));
        locationArrayList.add(new LatLng(40.405424, -3.693377));
        locationArrayList.add(new LatLng(40.407122, -3.704004));
        locationArrayList.add(new LatLng(40.410635, -3.719507));
        locationArrayList.add(new LatLng(40.409535, -3.696160));
        locationArrayList.add(new LatLng(40.411463, -3.710095));
        locationArrayList.add(new LatLng(40.403870, -3.672536));
        locationArrayList.add(new LatLng(40.415351, -3.700304));
        locationArrayList.add(new LatLng(40.416540, -3.676394));
        locationArrayList.add(new LatLng(40.423199, -3.668783));
        locationArrayList.add(new LatLng(40.440923, -3.622996));
        locationArrayList.add(new LatLng(40.458799, -3.666623));
        locationArrayList.add(new LatLng(40.451623, -3.740412));




    }

    private void getLastLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!= null){
                    currentLocation=location;
                    Mine = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                    initMap();
                }
            }
        });

    }
    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // inside on map ready method
                // we will be displaying all our markers.
                // for adding markers we are running for loop and
                // inside that we are drawing marker on our map.
                for (int i = 0; i < locationArrayList.size(); i++) {
                    // below line is use to add marker to each location of our array list.
                    mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Punto Limpio"));

                    // below line is use to zoom our camera on map.
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(900));

                    // below line is use to move our camera to the specific location.
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Mine));
                }

                // Now, add the marker for the current location (Mine)
                if (Mine != null) {
                    mMap.addMarker(new MarkerOptions().position(Mine).title("My Location").icon(BitmapFromVector(
                            getApplicationContext(),
                            R.drawable.mylocation)));

                    // below line is use to move our camera to the specific location.
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Mine));

                    // below line is use to zoom our camera on map.
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15));


                } else {
                    // Handle the case where Mine is null
                    Toast.makeText(MapsActivity.this, "Current location not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == FINE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                Toast.makeText(this,"No has concedido la localizacion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private BitmapDescriptor
    BitmapFromVector(Context context, int vectorResId)
    {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(
                context, vectorResId);

        // below line is use to set bounds to our vector
        // drawable.
        vectorDrawable.setBounds(
                0, 0, vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(
                vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our
        // bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}