package com.example.firebase_log_in;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class SearchFragment extends Fragment {
    ListView listView;
    ArrayList<String> searchList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.search_list);
        searchList.add("Among Us");
        searchList.add("League of Legends");
        searchList.add("Minecraft");

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, searchList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPrefs = getActivity().getSharedPreferences("Search", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("Title", adapter.getItem(position));
                editor.apply();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchResultFragment()).commit();
            }
        });
    }

}
