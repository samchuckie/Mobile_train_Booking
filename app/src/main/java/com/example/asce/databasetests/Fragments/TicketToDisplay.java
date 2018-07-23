package com.example.asce.databasetests.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asce.databasetests.R;
import com.example.asce.databasetests.ViewModel.CurrentTicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TicketToDisplay extends Fragment {

    TextView t_n,t_phone,t_i,t_pr;
    private CurrentTicker currentTicker;
    Button deleter;
    private String userid;
    DatabaseReference databaseReference;
    private FirebaseUser user;
    private View.OnClickListener deleteentry = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            databaseReference.child(userid).child(currentTicker.getTrainentry_id()).setValue(null);
            getFragmentManager().popBackStackImmediate();
            Log.e("sam" , "the string key is " + currentTicker.getTrainentry_id());


        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.currentticket ,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentTicker  = ViewModelProviders.of(getActivity()).get(CurrentTicker.class);

        t_n=getActivity().findViewById(R.id.ctk_name);
        t_phone=getActivity().findViewById(R.id.ctk_phoneno);
        t_i=getActivity().findViewById(R.id.ctk_id);
        t_pr=getActivity().findViewById(R.id.ctk_price);
        deleter=getActivity().findViewById(R.id.deleteentry);
        t_n.setText(currentTicker.getName());
        t_phone.setText(String.valueOf(currentTicker.getPhonenumber()));
        t_i.setText(String.valueOf(currentTicker.getId()));
        t_pr.setText(String.valueOf(currentTicker.getPrice()));
        deleter.setOnClickListener(deleteentry);
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("UserTickets");
        userid =user.getUid();
    }
}
