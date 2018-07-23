package com.example.asce.databasetests.Fragments;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asce.databasetests.R;
import com.example.asce.databasetests.ViewModel.CurrentTicker;
import com.example.asce.databasetests.ViewModel.TicketBooked;
import com.example.asce.databasetests.ViewModel.booking_viewmodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tickets_frag extends Fragment implements Ticketsadapter.ItemClickListener {

    RecyclerView alltickets_rv;
    LinearLayoutManager linearLayoutManager;
    Ticketsadapter ticketsadapter;
    private FirebaseUser user;
    private String userid;
    private DatabaseReference databaseReference;
    List<TicketBooked> tbb = new ArrayList<>();
    private CurrentTicker currentTicker;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ticketchecker ,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        alltickets_rv =getActivity().findViewById(R.id.allticketickets_rv);
        currentTicker = ViewModelProviders.of(getActivity()).get(CurrentTicker.class);
        linearLayoutManager = new LinearLayoutManager(getContext());
        ticketsadapter = new Ticketsadapter(this);
        alltickets_rv.setLayoutManager(linearLayoutManager);
        alltickets_rv.setAdapter(ticketsadapter);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userid =user.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("UserTickets");
        databaseReference.child(userid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TicketBooked booked = dataSnapshot.getValue(TicketBooked.class);
                String trainentry_id = dataSnapshot.getKey();
                booked.setTrainentry_id(trainentry_id);
                new Tickets_async().execute(booked);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClickListener(String itemId) {
        databaseReference.child(userid).child(itemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("sam" , "" + dataSnapshot.getValue());
                TicketBooked todisplay =  dataSnapshot.getValue(TicketBooked.class);
                currentTicker.setId(todisplay.getId());
                currentTicker.setName(todisplay.getName());
                currentTicker.setPhonenumber(todisplay.getPhonenumber());
                currentTicker.setTrainentry_id(dataSnapshot.getKey());
                currentTicker.setPrice(todisplay.getPrice());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frags_container, new TicketToDisplay(), "Booking").addToBackStack(null).commit();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private class Tickets_async extends AsyncTask<TicketBooked, Void, TicketBooked> {
        @Override
        protected TicketBooked doInBackground(TicketBooked... ticketBookeds) {


            TicketBooked ticketBooked =ticketBookeds[0];
            return ticketBooked;
        }

        @Override
        protected void onPostExecute(TicketBooked ticketBookeds) {
            super.onPostExecute(ticketBookeds);
            ticketsadapter.addticket(ticketBookeds);
            Log.e("sam" , "Completed adding all of them to the adapter");
            Log.e("sam" , "" + tbb.size());
        }

    }

}
