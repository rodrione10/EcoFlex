package com.rojaja.ecoflex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

    private Handler searchHandler = new Handler(Looper.getMainLooper());
    private Runnable searchRunnable;
    private SearchView barra;
    private TextView nombreprod, huellaprod, impactoprod, reciclarprod;
    private ImageView imagenprod;
    private CardView nombre, huella, impacto, reciclar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Vinculación
        barra = findViewById(R.id.barra);
        nombreprod = findViewById(R.id.nombreprod);
        huellaprod = findViewById(R.id.huellaprod);
        impactoprod = findViewById(R.id.impactoprod);
        reciclarprod = findViewById(R.id.reciclarprod);
        imagenprod = findViewById(R.id.imagenprod);

        // CardViews
        nombre = findViewById(R.id.nombre);
        huella = findViewById(R.id.huella);
        impacto = findViewById(R.id.impacto);
        reciclar = findViewById(R.id.reciclar);

        barra.setIconified(false);

        barra.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String cleanedQuery = newText.trim();

                // Cancelar cualquier búsqueda pendiente
                if (searchRunnable != null) {
                    searchHandler.removeCallbacks(searchRunnable);
                }

                searchRunnable = () -> {
                    if (cleanedQuery.isEmpty()) {
                        clearProductInfo();
                    } else {
                        searchProducts(cleanedQuery);
                    }
                };

                // Ejecutar después de 400ms si no se sigue escribiendo
                searchHandler.postDelayed(searchRunnable, 400);

                return true;
            }
        });

        clearProductInfo(); // Oculta al iniciar
    }

    private void searchProducts(String query) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String queryLowerCase = query.trim().toLowerCase();

        db.collection("productos")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        boolean found = false;
                        clearProductInfo();

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            String nombreItem = document.getString("nombre");
                            if (nombreItem != null && nombreItem.toLowerCase().startsWith(queryLowerCase)) {
                                nombreprod.setText("Nombre: " + nombreItem);
                                huellaprod.setText("C02 por cada 100g: " + document.getString("huella"));
                                impactoprod.setText("Impacto mediambiental: " + document.getString("impacto"));
                                reciclarprod.setText("Donde reciclar: " + document.getString("reciclar"));

                                String imagenUrl = document.getString("imagen");
                                if (imagenUrl != null && !imagenUrl.isEmpty()) {
                                    Picasso.get().load(imagenUrl).into(imagenprod);
                                    imagenprod.setVisibility(ImageView.VISIBLE);
                                }

                                // Mostrar los cardviews
                                nombre.setVisibility(CardView.VISIBLE);
                                huella.setVisibility(CardView.VISIBLE);
                                impacto.setVisibility(CardView.VISIBLE);
                                reciclar.setVisibility(CardView.VISIBLE);

                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            clearProductInfo();
                            Toast.makeText(Products.this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(Products.this, "Error al buscar", Toast.LENGTH_SHORT).show());
    }

    private void clearProductInfo() {
        nombreprod.setText("");
        huellaprod.setText("");
        impactoprod.setText("");
        reciclarprod.setText("");
        imagenprod.setImageDrawable(null);
        imagenprod.setVisibility(ImageView.GONE);

        nombre.setVisibility(CardView.GONE);
        huella.setVisibility(CardView.GONE);
        impacto.setVisibility(CardView.GONE);
        reciclar.setVisibility(CardView.GONE);
    }
}