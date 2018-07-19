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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.example.asce.databasetests.R;
import com.example.asce.databasetests.ViewModel.Datechecker_viewmodel;
import com.example.asce.databasetests.ViewModel.booking_viewmodel;
import com.example.asce.databasetests.ViewModel.darajaViewModel;
import com.example.asce.databasetests.Entry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TrainEntry_frag extends Fragment {
    private booking_viewmodel book;
    private darajaViewModel darajavm;
    private DatabaseReference databaseReference;
    EditText usersname, userid,usertelephone;
    private Datechecker_viewmodel datechecker_viewmodel;
    TextView seatno;
    Button mpesa_req;
    private Daraja daraja;
    String bday,bmonth,byear;
    int seatnumber;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_booking ,container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        book = ViewModelProviders.of(getActivity()).get(booking_viewmodel.class);
        darajavm = ViewModelProviders.of(getActivity()).get(darajaViewModel.class);
        datechecker_viewmodel = ViewModelProviders.of(getActivity()).get(Datechecker_viewmodel.class);
        Log.e("sam", "Month to be booked" + book.getmMonth());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        usersname = getActivity().findViewById(R.id.names);
        userid = getActivity().findViewById(R.id.id);
        usertelephone = getActivity().findViewById(R.id.telephone);
        seatno = getActivity().findViewById(R.id.seat);
        mpesa_req = getActivity().findViewById(R.id.requester);
        mpesa_req.setOnClickListener(mpesa_request);
        bday =String.valueOf(book.getmDay());
        bmonth = String.valueOf(book.getmMonth());
        byear =String.valueOf(book.getmYear());
        daraja = Daraja.with(darajavm.getConsumerkey(), darajavm.getConsumesecret(), new DarajaListener<AccessToken>() {
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
        databaseReference.child(byear)
                .child(bmonth)
                .child(bday)
                .child(book.getDeparture_station()).child(book.getDestination_station())
                .child(book.getEconomical_status())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //use to check if seast available change during booking
                        int number = (int) dataSnapshot.getChildrenCount();
                        if (number<=datechecker_viewmodel.getMaximumseats())
                        {
                            number++;
                            seatnumber = number;
                            seatno.setText(String.valueOf(seatnumber));

                        }
                        else
                        {
                            //go back to datepicker
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }

                });

    }
    View.OnClickListener mpesa_request = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final String user_name = usersname.getText().toString();
            final int user_id =Integer.valueOf(userid.getText().toString());
            final int user_telephone = Integer.valueOf(usertelephone.getText().toString());


            LNMExpress lnmExpress = new LNMExpress(
                    darajavm.getBusinnesscode(),
                    darajavm.getPasskey(),
                    darajavm.getAmount(book.getPrice()),
                    darajavm.getPartyA(),
                    darajavm.getPartyB(),
                    // TODO: USE PHONE NUMBER GOTTEN
                    darajavm.getPhoneno(),
                    darajavm.getCallbackurl(),
                    darajavm.getAccountreference(),
                    darajavm.getTransactiondec()
            );
            Log.e("sam", "The username is " + user_name);
            Log.e("sam", "The id is " + user_id);
            Log.e("sam", "The telephone is " + user_telephone);
            Log.e("sam", "The price is " + book.getPrice());
            setter(user_name, user_id, book.getPrice(), user_telephone);
            daraja.requestMPESAExpress(lnmExpress, new DarajaListener<LNMResult>() {
                        @Override
                        public void onResult(@NonNull LNMResult lnmResult) {
                            Log.e("sam", "descritpion" + lnmResult.ResponseDescription);
                            setter(user_name, user_id, book.getPrice(), user_telephone);
                        }

                        @Override
                        public void onError(String error) {
                            Log.e("sam", error);
                        }
                    }
            );
        }
    };

    public void setter(String name ,int id,int price , int phone)
    {
        Entry tick_entry= new Entry(name,id ,price ,phone);
        Map<String, Object> postValues = tick_entry.mapper();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(byear + "/" + bmonth + "/" +bday + "/" + book.getDeparture_station()
                + "/" + book.getDestination_station() + "/" + book.getEconomical_status()+ "/"+seatnumber , postValues);
        databaseReference.updateChildren(childUpdates);

        Map<String ,Object> usermap = new HashMap<>() ;
        usermap.put("UserTickets/" + FirebaseAuth.getInstance().getCurrentUser().getUid()  , postValues);

        databaseReference.updateChildren(usermap);

        }

}

