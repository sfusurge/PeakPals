package com.example.firebase_log_in;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment implements View.OnClickListener {
//    private Button Talks;
    private Button Games;
//    private Button Study;
//    private Button Outdoor;
    private Button AmongUs;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        AmongUs = (Button) view.findViewById(R.id.among_us);
        Games = view.findViewById(R.id.games_button);
        AmongUs.setOnClickListener(this);
        Games.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.among_us:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AmongUsActivity()).commit();
                break;
            case R.id.games_button:
                SharedPreferences sharedPrefs = getActivity().getSharedPreferences("Search", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("Title", "");
                editor.apply();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchResultFragment()).commit();
                break;
        }
    }
}
