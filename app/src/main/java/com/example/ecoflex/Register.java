package com.example.ecoflex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
     private Button botonregistro;
     private EditText nombre,email,contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         nombre=findViewById(R.id.usuario1);
         email=findViewById(R.id.email1);
         contrasena=findViewById(R.id.password1);
         botonregistro=findViewById(R.id.iniciar_sesion);

         botonregistro.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String nameUser = nombre.getText().toString().trim();
                 String emailUser = email.getText().toString().trim();
                 String passUser = contrasena.getText().toString().trim();

                 if(nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()){
                     Toast.makeText(Register.this, "Completa todos los campos",Toast.LENGTH_SHORT).show();
                 }else{

                 }
             }
         });
    }
    public void openMain(View v){
        Intent intent = new Intent(Register.this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}