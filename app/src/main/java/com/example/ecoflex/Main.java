package com.example.ecoflex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.example.ecoflex.SectionsPagerAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class Main extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager1 = findViewById(R.id.view_pager);
        viewPager1.setAdapter(sectionsPagerAdapter);
        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);
        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.likes) {

                    item.setChecked(true);
                    Toast.makeText(Main.this, "Likes clicked.", Toast.LENGTH_SHORT).show();
                    //removeBadge(mybottomNavView,item.getItemId());
                    viewPager1.setCurrentItem(0);
                } else if (item.getItemId() == R.id.add) {
                    item.setChecked(true);
                    Toast.makeText(Main.this, "Add clicked.", Toast.LENGTH_SHORT).show();
                    //removeBadge(mybottomNavView,item.getItemId());
                    viewPager1.setCurrentItem(1);
                } else if (item.getItemId() == R.id.browse) {
                    item.setChecked(true);
                    Toast.makeText(Main.this, "Browse clicked.", Toast.LENGTH_SHORT).show();
                    //removeBadge(mybottomNavView,item.getItemId());
                    viewPager1.setCurrentItem(2);
                } else if (item.getItemId() == R.id.personal) {
                    item.setChecked(true);
                    Toast.makeText(Main.this, "Personal clicked.", Toast.LENGTH_SHORT).show();
                    //removeBadge(mybottomNavView,item.getItemId());
                    viewPager1.setCurrentItem(3);
                }


                return false;
            }
        });
    }
}