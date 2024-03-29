package com.rojaja.ecoflex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        openApp();

        //ImageView logo;
        //logo = (ImageView)findViewById(R.id.logo);
        ImageView cargar;
        //cargar = (ImageView) findViewById(R.id.cargar);
        Animation rotate = AnimationUtils.loadAnimation(this,R.anim.girar);
        //cargar.startAnimation(rotate);

        /*Glide.with(this)
                .load(R.drawable.logo)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(logo);

         */
    }
    private void openApp() { //Para que se abra el splash con efecto
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 6000);
    }
}