package com.example.asce.databasetests;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Booking extends AppCompatActivity {

    public static final String EXTRA_DAY = "day";
    public static final String EXTRA_MONTH ="month" ;
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_TO = "to";
    public static final String EXTRA_FROM = "from" ;
    public static final String EXTRAYEAR = "year";
    public static final String EXTRA_ECONOMY = "economy class" ;
    private static final int SEATNUMBER = 2;
    int mYear , mMonth , mDay,price;
    String pickup ,destination, economy,day,month, year, seatnumber;

    DatabaseReference databaseReference;
    EditText usersname, userid,usertelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        usersname = findViewById(R.id.names);
        userid = findViewById(R.id.id);
        usertelephone = findViewById(R.id.telephone);
        Intent intent =getIntent();
        if(intent!=null){
            if(intent.hasExtra(EXTRA_DAY)){
                mDay = intent.getIntExtra(EXTRA_DAY , 0);
                day =String.valueOf(mDay);
            }
            if(intent.hasExtra(EXTRA_MONTH)){
                mMonth = intent.getIntExtra(EXTRA_MONTH , 0);
                month =String.valueOf(mMonth);
            }
            if(intent.hasExtra(EXTRAYEAR)){
                mYear = intent.getIntExtra(EXTRAYEAR , 0);
                year =String.valueOf(mYear);
            }
            if(intent.hasExtra(EXTRA_PRICE)){
                price = intent.getIntExtra(EXTRA_PRICE , 0);
            }
            if(intent.hasExtra(EXTRA_ECONOMY)){
                economy=intent.getStringExtra(EXTRA_ECONOMY);
            }
            if(intent.hasExtra(EXTRA_TO)){
                destination=intent.getStringExtra(EXTRA_TO);
            }
            if(intent.hasExtra(EXTRA_FROM)){
                pickup=intent.getStringExtra(EXTRA_FROM);
            }

        }
        Log.e("sam" , " " + mDay );
        Log.e("sam" , " " + mMonth );
        Log.e("sam" , " " + mYear );
        Log.e("sam" , " " + price );
        Log.e("sam" , " " + economy );
        Log.e("sam" , " " + pickup );
        Log.e("sam" , " " + destination );
          databaseReference.child(String.valueOf(mYear)).child(month).child(day)
          .child(pickup).child(destination).child(economy).addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        int number = (int) dataSnapshot.getChildrenCount();
        if (number<=SEATNUMBER)
        {
            number++;
            seatnumber = String.valueOf(number);

        }
        else
        {
            startActivity(new Intent(getApplicationContext(), Date_checker.class));
            Log.e("sam", "We are to many");
        }
    Log.e("sam", "" + number);

}

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });



    }

    public void book(View view) {

        String n=usersname.getText().toString();
        String i=userid.getText().toString();
        String t=usertelephone.getText().toString();

        setter(n,i,price,t);
    }

    public void setter(String name ,String id,int price , String phone)
    {
        entry ee= new entry(name,id ,price ,phone);
        Map<String, Object> postValues = ee.mapper();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(year + "/" + month + "/" + day + "/" + pickup + "/" + destination  + "/" +  economy + "/" + seatnumber     , postValues);
        databaseReference.updateChildren(childUpdates);
        startActivity(new Intent(getApplicationContext(), Date_checker.class));

    }
}
