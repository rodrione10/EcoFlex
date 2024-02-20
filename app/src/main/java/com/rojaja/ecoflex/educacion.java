package com.rojaja.ecoflex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class educacion extends AppCompatActivity {
    private ListView tipsListView;
    // Aquí puedes definir las URL de los artículos correspondientes a cada consejo
    private static final String[] tipsUrls = {
            "https://www.iberdrola.com/sostenibilidad/como-reducir-consumo-plastico",
            "https://www.recytrans.com/blog/como-se-recicla-el-papel-y-carton/",
            "https://www.leanpio.com/es/blog/separacion-residuos-organicos-inorganicos",
            "https://mejorconsalud.as.com/reutilizar-los-envases-de-plastico-se-acumulan-hogar/",
            "https://www.reciclajeparatodo.com/",
            "https://ecolec.es/greenblog/actualidad/bolsas-reutilizables-beneficios/",
            "https://www.sostenibilidad.com/vida-sostenible/desperdicio-de-comida-que-es-y-como-evitarlo/",
            // Agrega más URL según desees, asegúrate de que coincidan con los consejos
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educacion);

            // Array de consejos para reciclar
            final String[] tipsArray = {
                    "Reduce el uso de plástico",
                    "Recicla papel y cartón",
                    "Separa los residuos orgánicos de los inorgánicos",
                    "Reutiliza envases y botellas",
                    "Compra productos reciclados",
                    "Usa bolsas reutilizables",
                    "Evita el desperdicio de alimentos"
                    // Agrega más consejos según desees
            };

            // Vincula el ListView del layout
            tipsListView = findViewById(R.id.tipsListView);

            // Crea un ArrayAdapter para la lista de consejos
            ArrayAdapter<String> tipsAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    tipsArray
            );

            // Asigna el adaptador al ListView
            tipsListView.setAdapter(tipsAdapter);

            // Agrega un listener de clics a la lista
            tipsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Obtiene la URL del artículo correspondiente al consejo seleccionado
                    String tipUrl = tipsUrls[position];
                    // Abre el enlace en un navegador web
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tipUrl));
                    startActivity(browserIntent);
                }
            });
        }
    }