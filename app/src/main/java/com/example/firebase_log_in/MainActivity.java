package com.example.firebase_log_in;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class MainActivity extends Activity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView authStatus;
    private EditText authEmail;
    private EditText authPassword;
    private Button authLogin;
    private Button authSignup;

    private static final String TAG = "authInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        authStatus = (TextView) findViewById(R.id.auth_status);
        authEmail = (EditText) findViewById(R.id.auth_email);
        authPassword = (EditText) findViewById(R.id.auth_password);
        authLogin = (Button) findViewById(R.id.auth_login);
        authLogin.setOnClickListener(this);
        authSignup = (Button) findViewById(R.id.auth_signup);
        authSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.auth_signup:
                createAccount(authEmail.getText().toString(), authPassword.getText().toString());
                break;
            case R.id.auth_login:
                signIn(authEmail.getText().toString(), authPassword.getText().toString());
                break;
        }
    }

    private void createAccount(String email, String password) {
        //if not sfu return
        Log.d(TAG, "createAccount:" + email);
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        authStatus.setText("Signed up");
                        Log.d(TAG, "createAccount success");

                    } else {
                        authStatus.setText("Failed to Sign up");
                        Log.d(TAG, "createAccount failed");

                    }
                }
            });
    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        authStatus.setText("Logged in");
                    } else {
                        authStatus.setText("Failed to log in");
                    }
                }
            });
    }
}