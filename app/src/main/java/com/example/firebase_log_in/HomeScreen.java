package com.example.firebase_log_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {
    private BottomNavigationView navBar;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        navBar = findViewById(R.id.bottom_navigation);
        navBar.setOnNavigationItemSelectedListener(navListener);

        //landing fragment on home page after loading
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
        signUp = findViewById(R.id.eventnew);
        signUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddingEvent.class));
                finish();
            }
        });
    }


    public void logout (View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LandingActivity.class));
        finish();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    //set icons back to default

                    Menu menu = navBar.getMenu();
                    menu.findItem(R.id.nav_profile).setIcon(R.drawable.ic_nav_profile_false);
                    menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_nav_home_false);


                    switch (item.getItemId()){
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            item.setIcon(R.drawable.ic_nav_profile_true);
                            break;
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            item.setIcon(R.drawable.ic_nav_home_true);
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace
                            (R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}