package com.example.firebase_log_in;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private static final String TAG = "loginInfo";
    private EditText username;
    private EditText password;
    private TextView showPass;
    private TextView toSignUp;
    private Button btn_login;
    private Boolean showPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.txt_login_username);
        password = (EditText) findViewById(R.id.txt_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        showPass = (TextView) findViewById(R.id.show_pass);
        showPass.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        toSignUp = findViewById(R.id.txt_login_toSignUp);
        toSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_login:
                signIn(username.getText().toString(), password.getText().toString());
                break;
            case R.id.show_pass:
                showPassword = !showPassword;
                if (showPassword){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            case R.id.txt_login_toSignUp:
                Intent i_signUp = new Intent(this, SignUpActivity.class);
                startActivity(i_signUp);
                break;
        }
    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to Log in", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
