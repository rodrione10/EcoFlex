package com.example.ecoflex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
     private Button botonregistro;
     private EditText nombre,email,contrasena;
     private FirebaseFirestore mFirestore;
     private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

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
                        registerUser(nameUser,emailUser,passUser);
                 }
             }
         });
    }

    private void registerUser(String nameUser, String emailUser, String passUser) {
        mAuth.createUserWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id",id);
                map.put("name",nameUser);
                map.put("email",emailUser);
                map.put("password",passUser);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(Register.this, Main.class));
                        Toast.makeText(Register.this, "Usuario registrado",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Error al guardar",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "Error al registrar",Toast.LENGTH_SHORT).show();
            }
        });
    }


}