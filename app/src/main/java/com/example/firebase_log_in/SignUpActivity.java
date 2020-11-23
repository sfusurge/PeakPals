package com.example.firebase_log_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText user_name, pass_word, email, conf_password;
    FirebaseAuth fAuth;
    Button sign_up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user_name = findViewById(R.id.user_name_et);
        pass_word = findViewById(R.id.password_et);
        email = findViewById(R.id.email_et);
        conf_password=findViewById(R.id.conf_pass_et);
        sign_up = findViewById(R.id.sign_up_btn);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
            finish();
        }
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user_name.getText().toString().trim();
                String email_str = email.getText().toString().trim();
                String pass = pass_word.getText().toString().trim();
                String confirm_pass = conf_password.getText().toString().trim();

                if(TextUtils.isEmpty(username))
                {
                 user_name.setError("Username Required");
                 return;
                }

                if(TextUtils.isEmpty(email_str)){
                    email.setError("Email Required");
                    return;
                }

                if(pass.length() < 6){
                    pass_word.setError("Too short. Must be >= 6 char");
                    return;
                }

                if(TextUtils.isEmpty(confirm_pass)){
                    email.setError("Email Required");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email_str, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        }else{
                            Toast.makeText(SignUpActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                {

                }

            }
        });
    }


}