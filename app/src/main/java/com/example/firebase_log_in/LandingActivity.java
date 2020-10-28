package com.example.firebase_log_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogIn;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        btnLogIn = (Button) findViewById(R.id.btn_to_login);
        btnSignUp = (Button) findViewById(R.id.btn_to_signUp);
        btnLogIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_to_login:
                Intent i_logIn = new Intent(this, LoginActivity.class);
                startActivity(i_logIn);
                break;
            case R.id.btn_to_signUp:
                Intent i_signUp = new Intent(this, SignUpActivity.class);
                startActivity(i_signUp);
                break;
        }
    }
}
