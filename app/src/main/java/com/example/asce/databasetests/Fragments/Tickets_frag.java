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

import com.example.asce.databasetests.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Tickets_frag extends Fragment {

    RecyclerView alltickets_rv;
    LinearLayoutManager linearLayoutManager;
    Ticketsadapter ticketsadapter;
    private FirebaseUser user;
    private String userid;
    private DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ticketchecker ,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        alltickets_rv =getActivity().findViewById(R.id.allticketickets_rv);
        linearLayoutManager = new LinearLayoutManager(getContext());
        alltickets_rv.setLayoutManager(linearLayoutManager);
        alltickets_rv.setAdapter(ticketsadapter);
        user = FirebaseAuth.getInstance().getCurrentUser();
         userid =user.getUid();


    }
}
