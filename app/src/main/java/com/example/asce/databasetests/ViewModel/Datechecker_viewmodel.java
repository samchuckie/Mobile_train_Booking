package com.example.asce.databasetests.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.asce.databasetests.Entry;
import com.example.asce.databasetests.Fared;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Datechecker_viewmodel extends ViewModel {
    DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference();
    private int nm, nv, vm;
    private int maximumseats = 40;
    private int seat_availabe;

    public int getMaximumseats() {
        return maximumseats;
    }

    public void setSeat_availabe(int seat_availabe) {
        this.seat_availabe = seat_availabe;
    }

    public int getSeat_availabe() {
        return seat_availabe;
    }

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


;

    public void queryeconomical(String economical_class) {
        dbreference.child("Fares").child(economical_class).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Fared fares = dataSnapshot.getValue(Fared.class);
                    setNm(fares.getNairobi_Mombasa());
                    setNv(fares.getNairobi_Voi());
                    setVm(fares.getVoi_Mombasa());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void queryprice(){

    }
}
