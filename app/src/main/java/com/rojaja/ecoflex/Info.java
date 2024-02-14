package com.rojaja.ecoflex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);
        mybottomNavView.setSelectedItemId(R.id.libro);
        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()  == R.id.home ) {
                    startActivity(new Intent(getApplicationContext(),Main.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(Info.this, "Página principal", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (item.getItemId()  == R.id.libro ) {

                    return true;

                } else if (item.getItemId()  == R.id.map ) {
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(Info.this, "Mapa con puntos limpios", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId()  == R.id.user ) {
                    startActivity(new Intent(getApplicationContext(),User.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(Info.this, "Tu perfil", Toast.LENGTH_SHORT).show();
                    return true;
                }


                /*switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Main.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.libro:

                        return true;

                    case R.id.map:
                        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.user:
                        startActivity(new Intent(getApplicationContext(),User.class));
                        overridePendingTransition(0,0);
                        return true;
                }*/
                return false;
            }
        });
    }
}