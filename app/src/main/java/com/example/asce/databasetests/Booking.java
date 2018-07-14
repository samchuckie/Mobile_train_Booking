package com.example.asce.databasetests;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    Daraja daraja;

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
        daraja = Daraja.with("IUgqx9M5VSyo21ZX6oMUbIjJcZANdLxu", "F2KdwWh7HUh8K37c", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Toast.makeText(getApplicationContext(), "TOKEN : " + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
                Log.e("sam" , "access token is " + accessToken.getAccess_token());
            }

            @Override
            public void onError(String error) {
                Log.e("sam" , "error is " + error);
            }
        });
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
        LNMExpress lnmExpress = new LNMExpress(
                "174379",
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",
                "10",
                "254703318241",
                "174379",
                "254703318241",
                "http://mycallbackurl.com/checkout.php",
                "Sam",
                "Testing"
        );

        daraja.requestMPESAExpress(lnmExpress,new DarajaListener<LNMResult>() {
                    @Override
                    public void onResult(@NonNull LNMResult lnmResult) {
                        Log.e("sam", lnmResult.ResponseDescription);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("sam", error);
                    }
                }
        );
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
