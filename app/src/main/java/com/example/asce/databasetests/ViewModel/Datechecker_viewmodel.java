package com.example.asce.databasetests.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.asce.databasetests.entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Datechecker_viewmodel extends ViewModel {
    DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference();
    private int nm,nv,vm;
    entry prices;


    public void setNm(int nm) {
        this.nm = nm;
    }

    public void setNv(int nv) {
        this.nv = nv;
    }

    public void setVm(int vm) {
        this.vm = vm;
    }

    public int getNm() {
        return nm;
    }

    public int getNv() {
        return nv;
    }

    public int getVm() {
        return vm;
    }

    public void queryeconomical(String economical_class) {
        dbreference.child("Fares").child(economical_class).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                prices = dataSnapshot.child("Mombasa").getValue(entry.class);
                setNm(prices.getPrice());
                prices = dataSnapshot.child("Voi").getValue(entry.class);
                setNv(prices.getPrice());
                prices = dataSnapshot.child("Voi-Msa").getValue(entry.class);
                setVm(prices.getPrice());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
