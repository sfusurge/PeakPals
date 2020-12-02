package com.example.firebase_log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddingEvent extends AppCompatActivity {


    EditText eventName, eventDate, eventTime;
    Button addEvent, btn_back;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_event);
        eventName = findViewById(R.id.event_name);
        eventDate = findViewById(R.id.eventDate);
        eventTime = findViewById(R.id.eventTime);
        addEvent = findViewById(R.id.addButton);
        btn_back = findViewById(R.id.create_event_back);
         addEvent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String title = eventName.getText().toString();
                 String date = eventDate.getText().toString();
                 String time = eventTime.getText().toString();
                 String type = "Game";
                 rootNode = FirebaseDatabase.getInstance();
                 reference = rootNode.getReference("Event").child(type);


                 EventHelperClass Eventclass = new EventHelperClass (title,date,time,type);
//                 reference.child(title).setValue(Eventclass);
                 DatabaseReference newEvent = reference.child(title).push();
                 newEvent.setValue(Eventclass);

             }
         });

         btn_back.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View v) {
                 startActivity(new Intent(AddingEvent.this, HomeScreen.class));
                 overridePendingTransition(0, 0);
             }
         });
    }
}