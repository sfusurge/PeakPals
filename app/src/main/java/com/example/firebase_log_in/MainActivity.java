package com.example.firebase_log_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends Activity implements View.OnClickListener{
    private Button btnLogIn;
    private Button btnSignUp;

    private static final String TAG = "authInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogIn = (Button) findViewById(R.id.btn_to_login);
        btnSignUp = (Button) findViewById(R.id.btn_to_signUp);
        btnLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_to_login:
                Intent i_logIn = new Intent(this, LogInActivity.class);
                startActivity(i_logIn);
                break;
            case R.id.btn_to_signUp:
                break;
        }
    }



}