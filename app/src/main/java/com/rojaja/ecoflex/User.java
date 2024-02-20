package com.rojaja.ecoflex;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class User extends AppCompatActivity {

    private Button modoClaroOscuroButton;
    private Button cerrarSesionButton;
    private FirebaseAuth mAuth;
    private TextView nombreuser;
    private String correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mAuth = FirebaseAuth.getInstance();
        nombreuser = findViewById(R.id.usernameText);
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
                                nombreuser.setText(nombre);
                                correo = documentSnapshot.getString("email");
                            } else {
                                Toast.makeText(User.this, "No se encontró el usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(User.this, "Error al obtener el usuario", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(User.this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }

        modoClaroOscuroButton = findViewById(R.id.button3);
        cerrarSesionButton = findViewById(R.id.button4);
        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);
        mybottomNavView.setSelectedItemId(R.id.user);
        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), Main.class));
                    overridePendingTransition(0, 0);
                    //Toast.makeText(User.this, "Página principal", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.libro) {
                    startActivity(new Intent(getApplicationContext(), Info.class));
                    overridePendingTransition(0, 0);
                    //Toast.makeText(User.this, "Artículos sobre reciclaje", Toast.LENGTH_SHORT).show();
                    return true;

                } else if (item.getItemId() == R.id.map) {
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                    overridePendingTransition(0, 0);
                    //Toast.makeText(User.this, "Mapa con puntos limpios", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.user) {

                    return true;
                }
                return false;
            }
            });

                modoClaroOscuroButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Verifica el modo actual
                        int modoActual = AppCompatDelegate.getDefaultNightMode();
                        // Cambia al modo opuesto
                        if (modoActual == AppCompatDelegate.MODE_NIGHT_YES) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        }
                        // Recrea la actividad para aplicar el nuevo modo
                        recreate();
                    }
                });

                cerrarSesionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cerrarSesion();
                    }
                });
            }

    public void resetPassword(View view) {
        String email =correo; // Obtener el correo electrónico del campo de texto

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Por favor ingresa tu correo electrónico registrado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Enviar un correo electrónico de restablecimiento de contraseña a la dirección de correo electrónico proporcionada
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(User.this, "Se ha enviado un correo electrónico para restablecer tu contraseña", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(User.this, "Falló el envío del correo electrónico de restablecimiento de contraseña", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void cerrarSesion() {
        mAuth.signOut();
        // Redirigir a la pantalla de inicio de sesión o a la actividad que desees después del cierre de sesión
        Intent intent = new Intent(User.this, MainLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
