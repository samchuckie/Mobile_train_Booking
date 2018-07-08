package com.example.asce.databasetests;

import android.app.DatePickerDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    DatePicker datePicker;
    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = findViewById(R.id.dater);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //          FirebaseAuth.getInstance().signOut();


        String dateFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);
        long currentdate = System.currentTimeMillis();
        String dateString = sdf.format(currentdate);
        Log.e("sam", "" + dateString);
        datePicker.setMinDate(currentdate);
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


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

    firebaseDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setter();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }
    public void setter()
    {
//        entry ee= new entry("sam","25852","1500");
//        Map<String, Object> postValues = ee.mapper();
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("2018/09/07" , postValues);
//        firebaseDatabase.updateChildren(childUpdates);
        startActivity(new Intent(getApplicationContext(), Logger.class));

    }
}