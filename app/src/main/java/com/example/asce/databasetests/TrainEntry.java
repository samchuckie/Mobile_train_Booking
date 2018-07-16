package com.example.asce.databasetests;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TrainEntry extends Fragment {
    private  booking_viewmodel book;
    DatabaseReference databaseReference;
    EditText usersname, userid,usertelephone;
    Button mpesa_req;
    Daraja daraja;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_booking ,container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        book = ViewModelProviders.of(getActivity()).get(booking_viewmodel.class);
        Log.e("sam", "" + book.getmMonth());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        usersname = getActivity().findViewById(R.id.names);
        userid = getActivity().findViewById(R.id.id);
        usertelephone = getActivity().findViewById(R.id.telephone);
        mpesa_req = getActivity().findViewById(R.id.requester);
         mpesa_req.setOnClickListener(mpesa_request);
        daraja = Daraja.with("IUgqx9M5VSyo21ZX6oMUbIjJcZANdLxu", "F2KdwWh7HUh8K37c", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Toast.makeText(getContext(), "TOKEN : " + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
                Log.e("sam" , "access token is " + accessToken.getAccess_token());
            }

            @Override
            public void onError(String error) {
                Log.e("sam" , "error is " + error);
            }
        });

    }
    View.OnClickListener mpesa_request = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final String n = usersname.getText().toString();
            final String i = userid.getText().toString();
            final String t = usertelephone.getText().toString();
            databaseReference.child(String.valueOf(book.getmYear())).child(String.valueOf(book.getmMonth()))
                    .child(String.valueOf(book.getmDay()))
                    .child(book.getDeparture_station()).child(book.getDestination_station()).child(book.getEconomical_status())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


            LNMExpress lnmExpress = new LNMExpress(
                    "174379",
                    "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",
                    "5",
                    "254703318241",
                    "174379",
                    "254703318241",
                    "http://mycallbackurl.com/checkout.php",
                    "Sam",
                    "Testing"
            );

            daraja.requestMPESAExpress(lnmExpress, new DarajaListener<LNMResult>() {
                        @Override
                        public void onResult(@NonNull LNMResult lnmResult) {
                            Log.e("sam", lnmResult.ResponseDescription);
                            setter(n, i, book.getPrice(), t);
                        }

                        @Override
                        public void onError(String error) {
                            Log.e("sam", error);
                        }
                    }
            );
        }
    };

    public void setter(String name ,String id,int price , String phone)
    {
        entry ee= new entry(name,id ,price ,phone);
        Map<String, Object> postValues = ee.mapper();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("hj"  , postValues);
        databaseReference.updateChildren(childUpdates);


    }


}

