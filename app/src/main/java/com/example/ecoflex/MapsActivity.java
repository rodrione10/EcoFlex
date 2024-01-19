package com.example.ecoflex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {
    private GoogleMap mMap;

    LatLng sydney = new LatLng(-34, 151);
    LatLng TamWorth = new LatLng(-31.083332, 150.916672);
    LatLng NewCastle = new LatLng(-32.916668, 151.750000);
    LatLng Brisbane = new LatLng(-27.470125, 153.021072);
    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

// Comprueba y solicita permisos
//checkLocationPermission();

//provisional
        // in below line we are initializing our array list.
        locationArrayList = new ArrayList<>();

        // on below line we are adding our
        // locations in our array list.
        locationArrayList.add(sydney);
        locationArrayList.add(TamWorth);
        locationArrayList.add(NewCastle);
        locationArrayList.add(Brisbane);

        
        initMap();
    }

    private void initMap() {
// Inicializa el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override public void onMapReady(GoogleMap googleMap)
            {
                mMap = googleMap;


                // inside on map ready method
                // we will be displaying all our markers.
                // for adding markers we are running for loop and
                // inside that we are drawing marker on our map.
                for (int i = 0; i < locationArrayList.size(); i++) {

                    // below line is use to add marker to each location of our array list.
                    mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));

                    // below line is use to zoom our camera on map.
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

                    // below line is use to move our camera to the specific location.
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                }
            }

        });
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