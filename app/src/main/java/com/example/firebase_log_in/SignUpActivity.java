package com.example.firebase_log_in;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends Fragment {
    EditText user_name, pass_word, email, conf_password;
    FirebaseAuth fAuth;
    Button sign_up;
    TextView toLogIn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        user_name = view.findViewById(R.id.txt_signup_fullName);
        pass_word = view.findViewById(R.id.txt_signup_password);
        email = view.findViewById(R.id.txt_signup_email);
        conf_password = view.findViewById(R.id.txt_signup_passConfirm);
        sign_up = view.findViewById(R.id.sign_up_btn);
        fAuth = FirebaseAuth.getInstance();
        toLogIn = view.findViewById(R.id.txt_signup_toLogin);

//        if(fAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getIntent()), HomeScreen.class));
//            finish();
//        }
        toLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LoginActivity()).commit();
            }
        });
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
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new HomeFragment()).commit();
                        }else{
                        }
                    }
                });

                {

                }

            }
        });
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
////        user_name = findViewById(R.id.user_name_et);
////        pass_word = findViewById(R.id.password_et);
////        email = findViewById(R.id.email_et);
////        conf_password=findViewById(R.id.conf_pass_et);
//        sign_up = findViewById(R.id.sign_up_btn);
//        fAuth = FirebaseAuth.getInstance();
//
//        if(fAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
//            finish();
//        }
//        sign_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = user_name.getText().toString().trim();
//                String email_str = email.getText().toString().trim();
//                String pass = pass_word.getText().toString().trim();
//                String confirm_pass = conf_password.getText().toString().trim();
//
//                if(TextUtils.isEmpty(username))
//                {
//                 user_name.setError("Username Required");
//                 return;
//                }
//
//                if(TextUtils.isEmpty(email_str)){
//                    email.setError("Email Required");
//                    return;
//                }
//
//                if(pass.length() < 6){
//                    pass_word.setError("Too short. Must be >= 6 char");
//                    return;
//                }
//
//                if(TextUtils.isEmpty(confirm_pass)){
//                    email.setError("Email Required");
//                    return;
//                }
//
//                fAuth.createUserWithEmailAndPassword(email_str, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
//                        }else{
//                            Toast.makeText(SignUpActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                {
//
//                }
//
//            }
//        });
//    }


}