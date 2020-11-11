package com.example.firebase_log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddingEvent extends AppCompatActivity {


    EditText eventName, eventDate, eventTime;
    Button addEvent;

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

         addEvent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 rootNode = FirebaseDatabase.getInstance();
                 reference = rootNode.getReference("Event");

                 String title = eventName.getText().toString();
                 String date = eventDate.getText().toString();
                 String time = eventTime.getText().toString();

                 EventHelperClass Eventclass = new EventHelperClass (title,date,time);
                 reference.setValue(Eventclass);
             }
         });
         
    }
}