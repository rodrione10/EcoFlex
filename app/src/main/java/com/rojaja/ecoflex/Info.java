package com.rojaja.ecoflex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Info extends AppCompatActivity {
    private static final int REQUEST_EDUCACION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);
        mybottomNavView.setSelectedItemId(R.id.libro);
        Button educacionbutton = findViewById(R.id.educacion);
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
        educacionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, educacion.class);
                startActivityForResult(intent, REQUEST_EDUCACION);
            }
        });
    }
    public void openNoticia(View view) {
        String url = "https://elpais.com/noticias/medio-ambiente/#";

        // Crear un Intent para abrir una URL en el navegador
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }
}