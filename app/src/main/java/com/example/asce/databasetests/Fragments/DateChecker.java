package com.example.asce.databasetests.Fragments;

import android.app.DatePickerDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asce.databasetests.Fragments.TrainEntry;
import com.example.asce.databasetests.R;
import com.example.asce.databasetests.ViewModel.Datechecker_viewmodel;
import com.example.asce.databasetests.ViewModel.booking_viewmodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static android.app.DatePickerDialog.*;

public class DateChecker extends Fragment implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    Spinner to_option,from_options;
    DatePicker datePicker;
    DatabaseReference firebaseDatabase;
    private booking_viewmodel book;
    private Datechecker_viewmodel datechecker_viewmodel;
    RadioButton first_class,economy_class;
    ImageView dateicon;
    FloatingActionButton fab ;
    private String economical_class;
    TextView fdates , mv,mn,vm,vn,nm,nv;
    final Calendar myCalendar = Calendar.getInstance();
    DatePicker picker ;
    View.OnClickListener datess = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            final Calendar myCalendar = Calendar.getInstance();
//            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//                @Override
//                public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                      int dayOfMonth) {
//                    myCalendar.set(Calendar.YEAR, year);
//                    myCalendar.set(Calendar.MONTH, monthOfYear);
//                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                }
//
//            };

//                    new DatePickerDialog(getContext() ,date, myCalendar
//                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            DatePickerDialog dater= new DatePickerDialog(getContext(), new OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    book.getdatechanged(year,month + 1,dayOfMonth,1000);

                    Log.e("sam", "og -" + year);
                    Log.e("sam", "og -" + month);
                    Log.e("sam", "og -" + dayOfMonth);

                }
            }, Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);

            picker=dater.getDatePicker();
            picker.setMinDate(System.currentTimeMillis());
            dater.show();


        }
    };



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
        fdates =getActivity().findViewById(R.id.datepiclerss);
        fdates.setOnClickListener(datess);
        first_class = getActivity().findViewById(R.id.first);
        economy_class = getActivity().findViewById(R.id.second);
        first_class.setOnCheckedChangeListener(this);
        economy_class.setOnCheckedChangeListener(this);
        fab = getActivity().findViewById(R.id.floatingActionButton);
        dateicon = getActivity().findViewById(R.id.datepickericon);
        fab.setOnClickListener(booking);
        dateicon.setOnClickListener(booking);
        book = ViewModelProviders.of(getActivity()).get(booking_viewmodel.class);
        datechecker_viewmodel = ViewModelProviders.of(getActivity()).get(Datechecker_viewmodel.class);
        to_option = getActivity().findViewById(R.id.to);
        from_options = getActivity().findViewById(R.id.from);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to_option.setAdapter(adapter);
        from_options.setAdapter(adapter);
        to_option.setOnItemSelectedListener(to_listener);
        from_options.setOnItemSelectedListener(from_listener);
        nv= getActivity().findViewById(R.id.nairobi_voi);
        nm= getActivity().findViewById(R.id.Nairobi_Msa);
        vm= getActivity().findViewById(R.id.voi_msa);
        vn= getActivity().findViewById(R.id.voi_nairobi);
        mn= getActivity().findViewById(R.id.msa_nairobi);
        mv= getActivity().findViewById(R.id.msa_voi);

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
        boolean checked = buttonView.isChecked();


        switch (buttonView.getId()) {
            case R.id.first:
                if (checked)
                    economical_class = buttonView.getText().toString();
                    Log.e("sam", "" + economical_class);
                    book.setEconomical_status(economical_class);
                    datechecker_viewmodel.queryeconomical(economical_class);
                    updateui();
                break;
            case R.id.second:
                if (checked)
                economical_class = buttonView.getText().toString();
                book.setEconomical_status(economical_class);
                Log.e("sam", "" + economical_class);
                datechecker_viewmodel.queryeconomical(economical_class);
                break;

        }
    }

    private void updateui() {
        mv.setText(datechecker_viewmodel.getVm());
        mn.setText(datechecker_viewmodel.getNm());
        vm.setText(datechecker_viewmodel.getVm());
        vn.setText(datechecker_viewmodel.getNv());
        nm.setText(datechecker_viewmodel.getNm());
        nv.setText(datechecker_viewmodel.getNv());


    }
}
