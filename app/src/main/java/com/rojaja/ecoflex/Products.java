package com.rojaja.ecoflex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Products extends AppCompatActivity {

    private SearchView barra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        barra=findViewById(R.id.barra);

        barra.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Aquí puedes realizar la búsqueda en Firebase cada vez que el texto cambie
                // Llamar a tu función de búsqueda con newText
                searchProducts(newText);
                return true;
            }
        });
    }
    private void searchProducts(String query) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("productos")
                .whereEqualTo("nombre", query)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            // Aquí puedes manejar los resultados de la búsqueda
                            String productName = document.getString("nombre");
                            // Puedes mostrar el nombre del producto en tu interfaz de usuario
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Products.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}