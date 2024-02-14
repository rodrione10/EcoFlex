package com.rojaja.ecoflex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class Products extends AppCompatActivity {

    private SearchView barra;
    private TextView nombreprod,huellaprod,impactoprod,reciclarprod;
    private ImageView imagenprod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        barra=findViewById(R.id.barra);
        nombreprod=findViewById(R.id.nombreprod);
        huellaprod=findViewById(R.id.huellaprod);
        impactoprod=findViewById(R.id.impactoprod);
        reciclarprod=findViewById(R.id.reciclarprod);
        imagenprod = findViewById(R.id.imagenprod);

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
                            String nombre = document.getString("nombre");
                            String huella = document.getString("huella");
                            String impacto = document.getString("impacto");
                            String reciclar = document.getString("reciclar");
                            String imagen = document.getString("imagen");
                            // Puedes mostrar el nombre del producto en tu interfaz de usuario
                            nombreprod.setText("Nombre: "+nombre);
                            huellaprod.setText(huella);
                            impactoprod.setText(impacto);
                            reciclarprod.setText(reciclar);
                            Picasso.get().load(imagen).into(imagenprod);
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
