package com.rojaja.ecoflex;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.firebase.auth.FirebaseAuth;

public class User extends AppCompatActivity {

    private Button modoClaroOscuroButton;
    private Button cerrarSesionButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mAuth = FirebaseAuth.getInstance();

        modoClaroOscuroButton = findViewById(R.id.button3);
        cerrarSesionButton = findViewById(R.id.button4);

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

    private void cerrarSesion() {
        mAuth.signOut();
        // Redirigir a la pantalla de inicio de sesión o a la actividad que desees después del cierre de sesión
        Intent intent = new Intent(User.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
