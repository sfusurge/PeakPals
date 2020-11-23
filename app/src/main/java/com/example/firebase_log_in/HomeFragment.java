package com.example.firebase_log_in;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {
    private Button Talks;
    private Button Games;
    private Button Study;
    private Button Outdoor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        Talks = (Button) findViewById(R.id.talks_button);
//        Games = (Button) findViewById(R.id.games_button);
//        Study = (Button) findViewById(R.id.study_button);
//        Outdoor = (Button) findViewById(R.id.outdoor_button);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
