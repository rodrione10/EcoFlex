package com.rojaja.ecoflex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class Main extends AppCompatActivity {


    private MenuItem prevMenuItem;
    private TextView nombreuser;
    private Button busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busqueda=findViewById(R.id.busqueda);

        nombreuser = findViewById(R.id.nomusuario);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("user")
                    .document(userId)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String nombre = documentSnapshot.getString("name");
                                nombreuser.setText("Bienvenido: " + nombre);
                            } else {
                                Toast.makeText(Main.this, "No se encontró el usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Main.this, "Error al obtener el usuario", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(Main.this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }

        TextView contadorp = findViewById(R.id.contadorp);
        Button masp = findViewById(R.id.masp);
        Button menosp = findViewById(R.id.menosp);

        TextView contadoro = findViewById(R.id.contadoro);
        Button maso = findViewById(R.id.maso);
        Button menoso = findViewById(R.id.menoso);

        TextView contadorv = findViewById(R.id.contadorv);
        Button masv = findViewById(R.id.masv);
        Button menosv = findViewById(R.id.menosv);

        TextView contadorc = findViewById(R.id.contadorc);
        Button masc = findViewById(R.id.masc);
        Button menosc = findViewById(R.id.menosc);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int contadorValorP = prefs.getInt("contadorp_valor", 0); // 0 es el valor predeterminado si no se encuentra ningún valor
        int contadorValorO = prefs.getInt("contadoro_valor", 0);
        int contadorValorV = prefs.getInt("contadorv_valor", 0);
        int contadorValorC = prefs.getInt("contadorc_valor", 0);
        // Mostrar el valor recuperado en el EditText
        contadorp.setText(String.valueOf(contadorValorP));
        contadoro.setText(String.valueOf(contadorValorO));
        contadorv.setText(String.valueOf(contadorValorV));
        contadorc.setText(String.valueOf(contadorValorC));

        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);
        mybottomNavView.setSelectedItemId(R.id.home);
        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()  == R.id.home ) {
                    return true;
                }else if (item.getItemId()  == R.id.libro ) {
                    startActivity(new Intent(getApplicationContext(),Info.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(Main.this, "Artículos sobre reciclaje", Toast.LENGTH_SHORT).show();
                    return true;

                } else if (item.getItemId()  == R.id.map ) {
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(Main.this, "Mapa con puntos limpios", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId()  == R.id.user ) {
                    startActivity(new Intent(getApplicationContext(),User.class));
                    overridePendingTransition(0,0);
                    //Toast.makeText(Main.this, "Tu perfil", Toast.LENGTH_SHORT).show();
                    return true;
                }



               /*switch (item.getItemId()){
                    case R.id.home:
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
                        startActivity(new Intent(getApplicationContext(), User.class));
                        overridePendingTransition(0,0);
                        return true;
                }*/
                return false;
            }
        });

        masp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor actual del EditText y convertirlo a entero
                String valorActualString = contadorp.getText().toString();
                int valorActualP = Integer.parseInt(valorActualString);

                // Incrementar el valor actual en 1
                valorActualP++;

                // Guardar el nuevo valor actualizado en SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("contadorp_valor", valorActualP);
                editor.apply();

                // Actualizar el valor del EditText con el nuevo valor
                contadorp.setText(String.valueOf(valorActualP));
            }
        });

        menosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor actual del EditText y convertirlo a entero
                String valorActualString = contadorp.getText().toString();
                int valorActualP = Integer.parseInt(valorActualString);

                if (valorActualP > 0) {
                    // Decrementar el valor actual en 1
                    valorActualP--;
                }

                // Guardar el nuevo valor actualizado en SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("contadorp_valor", valorActualP);
                editor.apply();

                // Actualizar el valor del EditText con el nuevo valor
                contadorp.setText(String.valueOf(valorActualP));
            }
        });

        maso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor actual del EditText y convertirlo a entero
                String valorActualString = contadoro.getText().toString();
                int valorActualO = Integer.parseInt(valorActualString);

                // Incrementar el valor actual en 1
                valorActualO++;

                // Guardar el nuevo valor actualizado en SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("contadoro_valor", valorActualO);
                editor.apply();

                // Actualizar el valor del EditText con el nuevo valor
                contadoro.setText(String.valueOf(valorActualO));
            }
        });

        menoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor actual del EditText y convertirlo a entero
                String valorActualString = contadoro.getText().toString();
                int valorActualO = Integer.parseInt(valorActualString);

                if (valorActualO > 0) {
                    // Decrementar el valor actual en 1
                    valorActualO--;
                }

                // Guardar el nuevo valor actualizado en SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("contadoro_valor", valorActualO);
                editor.apply();

                // Actualizar el valor del EditText con el nuevo valor
                contadoro.setText(String.valueOf(valorActualO));
            }
        });
        masv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor actual del EditText y convertirlo a entero
                String valorActualString = contadorv.getText().toString();
                int valorActualV = Integer.parseInt(valorActualString);

                // Incrementar el valor actual en 1
                valorActualV++;

                // Guardar el nuevo valor actualizado en SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("contadorv_valor", valorActualV);
                editor.apply();

                // Actualizar el valor del EditText con el nuevo valor
                contadorv.setText(String.valueOf(valorActualV));
            }
        });

        menosv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor actual del EditText y convertirlo a entero
                String valorActualString = contadorv.getText().toString();
                int valorActualV = Integer.parseInt(valorActualString);

                // Verificar si el valor actual es mayor que 0 antes de restar
                if (valorActualV     > 0) {
                    // Decrementar el valor actual en 1
                    valorActualV--;
                }

                // Guardar el nuevo valor actualizado en SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("contadorv_valor", valorActualV);
                editor.apply();

                // Actualizar el valor del EditText con el nuevo valor
                contadorv.setText(String.valueOf(valorActualV));
            }
        });

        masc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor actual del EditText y convertirlo a entero
                String valorActualString = contadorc.getText().toString();
                int valorActualC = Integer.parseInt(valorActualString);

                // Incrementar el valor actual en 1
                valorActualC++;

                // Guardar el nuevo valor actualizado en SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("contadorc_valor", valorActualC);
                editor.apply();

                // Actualizar el valor del EditText con el nuevo valor
                contadorc.setText(String.valueOf(valorActualC));
            }
        });

        menosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor actual del EditText y convertirlo a entero
                String valorActualString = contadorc.getText().toString();
                int valorActualC = Integer.parseInt(valorActualString);

                // Verificar si el valor actual es mayor que 0 antes de restar
                if (valorActualC > 0) {
                    // Decrementar el valor actual en 1
                    valorActualC--;
                }

                // Guardar el nuevo valor actualizado en SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("contadorc_valor", valorActualC);
                editor.apply();

                // Actualizar el valor del EditText con el nuevo valor
                contadorc.setText(String.valueOf(valorActualC));
            }
        });

    }
    public void openBusqueda(View v){
        Intent intent = new Intent(Main.this,Products.class);
        startActivity(intent);
    }
}