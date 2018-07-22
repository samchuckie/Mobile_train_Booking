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
import android.widget.LinearLayout;

import com.example.asce.databasetests.Adapters.MapsAdapter;
import com.example.asce.databasetests.R;

import java.util.ArrayList;
import java.util.List;

public class Home_fragment extends Fragment {

    List<String> places;
    RecyclerView maps_rv;
    LinearLayoutManager linearLayoutManager;
    MapsAdapter mapsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homefrag ,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        maps_rv = getActivity().findViewById(R.id.maps_rv);
        linearLayoutManager = new LinearLayoutManager(getContext());
        maps_rv.setLayoutManager(linearLayoutManager);
        places = new ArrayList();
        places.add("Nairobi");
        places.add("Mombasa");
        places.add("Voi");
        mapsAdapter= new MapsAdapter(places);
        maps_rv.setAdapter(mapsAdapter);


    }
}
