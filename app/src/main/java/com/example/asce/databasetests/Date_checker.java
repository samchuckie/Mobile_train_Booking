package com.example.asce.databasetests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Date_checker extends AppCompatActivity {
    DatePicker datePicker;
    DatabaseReference firebaseDatabase;
    int mYear , mMonth , mDay,price;
    String pickup ,destination,economy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_checker);
        datePicker = findViewById(R.id.dater);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        pickup = "Nairobi";
        destination = "Voi";
        price = 1000;
        economy = "First class";



        String dateFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);
        long currentdate = System.currentTimeMillis();
        String dateString = sdf.format(currentdate);
        Log.e("sam", "" + dateString);
        datePicker.setMinDate(currentdate);
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDay=dayOfMonth;
                mMonth = monthOfYear + 1;
                mYear = year;


            }
        });


    }

    public void radiooo(View view) {
        boolean checked = ((RadioButton) view).isChecked();


        switch (view.getId()) {
            case R.id.first:
                if (checked)
                    Log.e("sam", "First is checked");
                break;
            case R.id.second:
                if (checked)
                    Log.e("sam", "Second is checked");
                break;

        }
    }

    public void post(View view) {
        Intent intent = new Intent(getApplicationContext(),Booking.class);
        intent.putExtra(Booking.EXTRA_DAY , mDay);
        intent.putExtra(Booking.EXTRA_MONTH , mMonth);
        intent.putExtra(Booking.EXTRAYEAR , mYear);
        intent.putExtra(Booking.EXTRA_FROM , pickup);
        intent.putExtra(Booking.EXTRA_TO , destination);
        intent.putExtra(Booking.EXTRA_PRICE , price);
        intent.putExtra(Booking.EXTRA_ECONOMY , economy);
        startActivity(intent);




    }

}