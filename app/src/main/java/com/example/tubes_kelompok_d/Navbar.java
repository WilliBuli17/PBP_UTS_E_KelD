package com.example.tubes_kelompok_d;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navbar extends AppCompatActivity {

    public final static String NAME = "Name";
    public final static String EMAIL = "Email";
    public final static String PHONE = "Phone";

    String nama, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new FavoritesFragment();
                            break;
                        case R.id.nav_search:
                            getdata();

                            Bundle bundle = new Bundle();
                            bundle.putString(NAME, nama);
                            bundle.putString(EMAIL, email);
                            bundle.putString(PHONE, phone);

                            selectedFragment = new SearchFragment();
                            selectedFragment.setArguments(bundle);
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    public void getdata(){
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
    }
}