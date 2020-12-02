package com.example.firebase_log_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Onboard1Activity extends AppCompatActivity implements View.OnClickListener {
    Button btn_next;
    TextView tv_skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding1);
        btn_next = findViewById(R.id.btn_onboard_1);
        btn_next.setOnClickListener(this);
        tv_skip = findViewById(R.id.tv_onboard_1skip);
        tv_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btn_onboard_1):
                startActivity(new Intent(Onboard1Activity.this, Onboard2Activity.class));
                overridePendingTransition(0, 0);

                break;
            case (R.id.tv_onboard_1skip):
                startActivity(new Intent(Onboard1Activity.this, HomeScreen.class));
                overridePendingTransition(0, 0);

                break;

        }
    }
}



