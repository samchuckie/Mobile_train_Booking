package com.example.asce.databasetests.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asce.databasetests.Adapter.StationsAdapter;
import com.example.asce.databasetests.R;

public class Improve_Home_Frag extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.improved_home_frag,container,false);
        RecyclerView stations_rv = view.findViewById(R.id.stations_rv);
        stations_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        StationsAdapter stationsAdapter = new StationsAdapter();
        stations_rv.setAdapter(stationsAdapter);
        return view;
    }


}
