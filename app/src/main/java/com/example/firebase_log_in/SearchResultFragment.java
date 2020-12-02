package com.example.firebase_log_in;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchResultFragment extends Fragment implements View.OnClickListener {
    private DatabaseReference databaseReference;
    SharedPreferences sharedPrefs;
    LinearLayout layout;
    Button signUp;
    //    Button btn_search;
    private String name, type = "Game", date, time;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sharedPrefs = getActivity().getSharedPreferences("Search", Context.MODE_PRIVATE);
        name = sharedPrefs.getString("Title", "Among Us");
        layout = getActivity().findViewById(R.id.fragment_search_layout);
//        btn_search = view.findViewById(R.id.btn_search);
//        btn_search.setOnClickListener(this);

        signUp = getActivity().findViewById(R.id.eventnew);
        signUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), AddingEvent.class));
                getActivity().finish();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Event");
        Query eventQuery;
        if (!name.isEmpty()){
            eventQuery = databaseReference.child(type).child(name);
            eventQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int count = 0;
                    for (DataSnapshot events: dataSnapshot.getChildren()) {
                        EventHelperClass event = events.getValue(EventHelperClass.class);
                        View view = getActivity().getLayoutInflater().inflate(R.layout.search_event_grid, layout);
                        view = view.findViewById(R.id.search_grid);
                        view.setId(count);
                        count++;

                        TextView eventName = view.findViewById(R.id.eventName);
                        TextView eventDate = view.findViewById(R.id.eventDate);
                        TextView eventTime = view.findViewById(R.id.eventTime);
                        TextView eventType = view.findViewById(R.id.eventType);
                        eventName.setText(event.getTitle());
                        eventDate.setText(event.getDate());
                        eventTime.setText(event.getTime());
                        eventType.setText(event.getType());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            eventQuery = databaseReference.child(type);
            eventQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int count = 0;
                    for (DataSnapshot allEvents : dataSnapshot.getChildren()) {
                        for (DataSnapshot events : allEvents.getChildren()) {

                            EventHelperClass event = events.getValue(EventHelperClass.class);
                            View view = getActivity().getLayoutInflater().inflate(R.layout.search_event_grid, layout);
                            view = view.findViewById(R.id.search_grid);
                            view.setId(count);
                            count++;

                            TextView eventName = view.findViewById(R.id.eventName);
                            TextView eventDate = view.findViewById(R.id.eventDate);
                            TextView eventTime = view.findViewById(R.id.eventTime);
                            TextView eventType = view.findViewById(R.id.eventType);
                            eventName.setText(event.getTitle());
                            eventDate.setText(event.getDate());
                            eventTime.setText(event.getTime());
                            eventType.setText(event.getType());
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
//
    @Override
    public void onClick(View v) {
//        databaseReference = FirebaseDatabase.getInstance().getReference("Event");
//        Query eventQuery = databaseReference.child(type).child(name);
//
//        eventQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int count = 0;
//                for (DataSnapshot events: dataSnapshot.getChildren()) {
//                    EventHelperClass event = events.getValue(EventHelperClass.class);
//                    View view = getActivity().getLayoutInflater().inflate(R.layout.search_event_grid, layout);
//                    view = view.findViewById(R.id.search_grid);
//                    view.setId(count);
//                    count++;
//
//                    TextView eventName = view.findViewById(R.id.eventName);
//                    TextView eventDate = view.findViewById(R.id.eventDate);
//                    TextView eventTime = view.findViewById(R.id.eventTime);
//                    TextView eventType = view.findViewById(R.id.eventType);
//                    eventName.setText(event.getTitle());
//                    eventDate.setText(event.getDate());
//                    eventTime.setText(event.getTime());
//                    eventType.setText(event.getType());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }
}
