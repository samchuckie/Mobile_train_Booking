package com.example.asce.databasetests;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DateChecker extends Fragment implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    Spinner to_option,from_options;
    DatePicker datePicker;
    DatabaseReference firebaseDatabase;
    String pickup ,destination,economy;
    private  booking_viewmodel book;
    RadioButton first_class,economy_class;
    FloatingActionButton fab ;
    private String economical_class;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_checker ,container,false);


    }

    AdapterView.OnItemSelectedListener to_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String going =parent.getSelectedItem().toString();
            book.setDestination_station(going);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    AdapterView.OnItemSelectedListener from_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String from =parent.getSelectedItem().toString();
            book.setDeparture_station(from);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        first_class = getActivity().findViewById(R.id.first);
        economy_class = getActivity().findViewById(R.id.second);
        first_class.setOnCheckedChangeListener(this);
        economy_class.setOnCheckedChangeListener(this);
        fab = getActivity().findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(booking);
        book = ViewModelProviders.of(getActivity()).get(booking_viewmodel.class);
        to_option = getActivity().findViewById(R.id.to);
        from_options = getActivity().findViewById(R.id.from);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
            R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to_option.setAdapter(adapter);
        from_options.setAdapter(adapter);
        to_option.setOnItemSelectedListener(to_listener);
        from_options.setOnItemSelectedListener(from_listener);
        datePicker =getActivity().findViewById(R.id.dater);
        Log.e("sam", "" + book.getDategotten());
        datePicker.setMinDate(book.getDategotten());
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                book.getdatechanged(year,monthOfYear+1,dayOfMonth,1000);
                Log.e("sam", "" + book.getmYear());
                Log.e("sam", "" + book.getmDay());
                Log.e("sam", "" + book.getmMonth());



            }
        });



    }
    View.OnClickListener booking = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            firebaseDatabase = FirebaseDatabase.getInstance().getReference();
            String sYear =String.valueOf(book.getmYear());
            String sMonth =String.valueOf(book.getmMonth());
            String sDay =String.valueOf(book.getmDay());
            Log.e("sam", "Year is " + sYear);
            Log.e("sam", "Month is" + sMonth);
            Log.e("sam", "Day is" + sDay);
            Log.e("sam", "from is " + book.getDeparture_station());
            Log.e("sam", "to is " + book.getDestination_station());
            Log.e("sam", "Economical is " + book.getEconomical_status());
            firebaseDatabase.child(sYear).child(sMonth).child(sDay)
                    .child(book.getDeparture_station()).child(book.getDestination_station()).child(book.getEconomical_status())
                    .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.e("sam", "" + dataSnapshot.getChildrenCount());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frags_container ,new TrainEntry(),"Booking").addToBackStack(null).commit();

        }
    };


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e("sam",parent.getItemAtPosition(position).toString());


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        boolean checked = ((RadioButton) buttonView).isChecked();


        switch (buttonView.getId()) {
            case R.id.first:
                if (checked)
                    economical_class = buttonView.getText().toString();
                    Log.e("sam", "" + economical_class);
                    book.setEconomical_status(economical_class);
                break;
            case R.id.second:
                if (checked)
                economical_class = buttonView.getText().toString();
                book.setEconomical_status(economical_class);
                Log.e("sam", "" + economical_class);

                break;

        }
    }
}
