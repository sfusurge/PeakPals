package com.example.firebase_log_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Onboard2Activity extends AppCompatActivity implements View.OnClickListener {
    Button btn_next;
    TextView tv_skip;
    TextView tv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding2);
        btn_next = findViewById(R.id.btn_onboard_2);
        btn_next.setOnClickListener(this);
        tv_skip = findViewById(R.id.tv_onboard_2skip);
        tv_skip.setOnClickListener(this);
        tv_back = findViewById(R.id.tv_onboard2_back);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.btn_onboard_2):
            case (R.id.tv_onboard_2skip):
                startActivity(new Intent(Onboard2Activity.this, HomeScreen.class));
                overridePendingTransition(0, 0);

                break;

            case (R.id.tv_onboard2_back):
                startActivity(new Intent(Onboard2Activity.this, Onboard1Activity.class));
                overridePendingTransition(0, 0);

                break;

        }
    }
}



