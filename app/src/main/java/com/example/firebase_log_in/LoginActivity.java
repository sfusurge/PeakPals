package com.example.firebase_log_in;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends Fragment implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private static final String TAG = "loginInfo";
    private EditText username;
    private EditText password;
    private TextView showPass;
    private TextView toSignUp;
    private Button btn_login;
    private Boolean showPassword = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        username = (EditText) view.findViewById(R.id.txt_login_username);
        password = (EditText) view.findViewById(R.id.txt_login_password);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        showPass = (TextView) view.findViewById(R.id.show_pass);
        showPass.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        toSignUp = view.findViewById(R.id.txt_login_toSignUp);
        toSignUp.setOnClickListener(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        username = (EditText) findViewById(R.id.txt_login_username);
//        password = (EditText) findViewById(R.id.txt_login_password);
//        btn_login = (Button) findViewById(R.id.btn_login);
//        btn_login.setOnClickListener(this);
//        showPass = (TextView) findViewById(R.id.show_pass);
//        showPass.setOnClickListener(this);
//        mAuth = FirebaseAuth.getInstance();
//        toSignUp = findViewById(R.id.txt_login_toSignUp);
//        toSignUp.setOnClickListener(this);
//    }

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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SignUpActivity()).commit();
                break;
        }
    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new HomeFragment()).commit();
                        } else {

                        }
                    }
                });
    }
}
