package com.example.firebase_log_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {
    private SharedPreferences sharedPrefs;
    private boolean isDark = false;
    private BottomNavigationView navBar;
    private int page;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefs = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        isDark = sharedPrefs.getBoolean("Theme", true);


        if (isDark){
            setTheme(R.style.LightTheme);
        }else{
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        navBar = findViewById(R.id.bottom_navigation);
        navBar.setOnNavigationItemSelectedListener(navListener);

        page = sharedPrefs.getInt("Fragment", 0);
        if (page == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        } else if (page == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
        } else if (page == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SearchFragment()).commit();
        }
        setIcons();
        
//        signUp = findViewById(R.id.eventnew);
//        signUp.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), AddingEvent.class));
//                finish();
//            }
//        });
    }


    public void logout (View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LandingActivity.class));
        finish();
    }

    private void setIcons(){
        Menu menu = navBar.getMenu();
        menu.findItem(R.id.nav_profile).setIcon(R.drawable.ic_nav_profile_false);
        menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_nav_home_false);
        menu.findItem(R.id.nav_search).setIcon(R.drawable.ic_nav_search_false);
        switch(page){
            case 0:
                menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_nav_home_true);
                break;
            case 1:
                menu.findItem(R.id.nav_profile).setIcon(R.drawable.ic_nav_profile_true);
                break;
            case 2:
                menu.findItem(R.id.nav_search).setIcon(R.drawable.ic_nav_search_true);
                break;

        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    SharedPreferences sharedPrefs = getSharedPreferences("Setting", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    //set icons back to default

                    Menu menu = navBar.getMenu();
//                    menu.findItem(R.id.nav_profile).setIcon(R.drawable.ic_nav_profile_false);
//                    menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_nav_home_false);
//                    menu.findItem(R.id.nav_search).setIcon(R.drawable.ic_nav_search_false);

                    switch (item.getItemId()){
                        case R.id.nav_profile:
                            selectedFragment = new SignUpActivity();
//                            item.setIcon(R.drawable.ic_nav_profile_true);
                            page = 1;
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
//                            item.setIcon(R.drawable.ic_nav_search_true);
                            page = 2;
                            break;
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
//                            item.setIcon(R.drawable.ic_nav_home_true);
                            page = 0;
                            break;
                    }
                    setIcons();
                    editor.putInt("Fragment", page);
                    editor.apply();
                    getSupportFragmentManager().beginTransaction().replace
                            (R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    public void switchTheme(View view){
        if (isDark){
            Toast.makeText(this, "button working!!!", Toast.LENGTH_SHORT).show();
            setTheme(R.style.LightTheme);
            isDark = false;
        } else{
            setTheme(R.style.DarkTheme);
            isDark = true;
        }
        SharedPreferences sharedPrefs = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("Theme", isDark);
        editor.apply();
        finish();
        startActivity(getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        overridePendingTransition( 0, 0);
    }


}