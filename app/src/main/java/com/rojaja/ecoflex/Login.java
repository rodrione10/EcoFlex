package com.rojaja.ecoflex;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText usuario1; // Campo de texto para el usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referencia al campo de texto usuario1
        usuario1 = findViewById(R.id.usuario1);

        ImageView imagen;
        imagen = findViewById(R.id.imagen);

        TextView olvideContraseñaTextView = findViewById(R.id.olvide_contraseña);

        // Crear una cadena con el texto del enlace
        String texto = "He olvidado mi contraseña";

        // Crear un SpannableString para aplicar el subrayado al texto
        SpannableString spannableString = new SpannableString(texto);

        // Aplicar el subrayado al texto
        spannableString.setSpan(new UnderlineSpan(), 0, texto.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Establecer el texto subrayado en el TextView
        olvideContraseñaTextView.setText(spannableString);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            goToMain();
        }
    }

    public void openMain(View v){
        Intent intent = new Intent(com.rojaja.ecoflex.Login.this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void openRegister(View v){
        Intent intent = new Intent(Login.this, Register.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void resetPassword(View view) {
        String email = usuario1.getText().toString().trim(); // Obtener el correo electrónico del campo de texto

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Por favor ingresa tu correo electrónico registrado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Enviar un correo electrónico de restablecimiento de contraseña a la dirección de correo electrónico proporcionada
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Se ha enviado un correo electrónico para restablecer tu contraseña", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Falló el envío del correo electrónico de restablecimiento de contraseña", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToMain(){
        Intent toMain = new Intent(Login.this, Main.class);
        toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        toMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(toMain);
    }
}
