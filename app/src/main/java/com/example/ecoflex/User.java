package com.example.ecoflex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);
        mybottomNavView.setSelectedItemId(R.id.user);
        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()  == R.id.home ) {
                    startActivity(new Intent(getApplicationContext(),Main.class));
                    overridePendingTransition(0,0);
                    return true;
                }else if (item.getItemId()  == R.id.libro ) {
                    startActivity(new Intent(getApplicationContext(),Info.class));
                    overridePendingTransition(0,0);
                    return true;

                } else if (item.getItemId()  == R.id.map ) {
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if (item.getItemId()  == R.id.user ) {

                    return true;
                }



              /*  switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Main.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.libro:
                        startActivity(new Intent(getApplicationContext(),Info.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.map:
                        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.user:

                        return true;
                }*/
                return false;
            }
        });
    }
}