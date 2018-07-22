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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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

public class DateChecker_frag extends Fragment implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {
    Spinner to_option,from_options;
    DatabaseReference firebaseDatabase;
    private booking_viewmodel book;
    private Datechecker_viewmodel datechecker_viewmodel;
    RadioButton first_class,economy_class;
    RadioGroup rdgroup;
    ImageView dateicon;
    FloatingActionButton fab ;
    private String economical_class;
    TextView fdates , mv,mn,vm,vn,nm,nv;
    DatePicker picker ;
    ArrayAdapter<CharSequence> adapter;
    private int b_id;


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
    public void onStart() {
        super.onStart();
        Log.e("sammer" , "Activity resumed");
        updateui();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("sammer" , "Activity started");
        super.onActivityCreated(savedInstanceState);
        fdates =getActivity().findViewById(R.id.datepiclerss);
        fdates.setOnClickListener(datess);
        first_class = getActivity().findViewById(R.id.first);
        economy_class = getActivity().findViewById(R.id.second);
//        first_class.setOnCheckedChangeListener(this);
//        economy_class.setOnCheckedChangeListener(this);
        rdgroup = getActivity().findViewById(R.id.radiogroup);
        fab = getActivity().findViewById(R.id.floatingActionButton);
        dateicon = getActivity().findViewById(R.id.datepickericon);
        fab.setOnClickListener(booking);
        dateicon.setOnClickListener(datess);
        book = ViewModelProviders.of(getActivity()).get(booking_viewmodel.class);
        datechecker_viewmodel = ViewModelProviders.of(getActivity()).get(Datechecker_viewmodel.class);
        to_option = getActivity().findViewById(R.id.to);
        from_options = getActivity().findViewById(R.id.from);
         adapter = ArrayAdapter.createFromResource(getContext(),
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
        economical_class = "First class";
        book.setEconomical_status(economical_class);
        datechecker_viewmodel.queryeconomical(economical_class);
        rdgroup.setOnCheckedChangeListener(this);

    }
    View.OnClickListener datess = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog dater= new DatePickerDialog(getContext(), new OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    book.getdatechanged(year,month + 1,dayOfMonth);
                    fdates.setText("" + year +"/" + month + "/" + dayOfMonth);
                }
            }, Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);

            picker=dater.getDatePicker();
            picker.setMinDate(System.currentTimeMillis());
            dater.show();
        }
    };
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switches(checkedId);
        book.setEconomical_status(economical_class);
        datechecker_viewmodel.queryeconomical(economical_class);
        updateui();

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e("sam",parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void switches(int id)
    {
        switch (id) {
            case R.id.first:
                    economical_class = first_class.getText().toString();
                Log.e("sam", "frist clasesese" + economical_class);

                break;
            case R.id.second:
                    economical_class = economy_class.getText().toString();
                Log.e("sam", "economical classsesdsfdsf" + economical_class);

                break;

        }
    }

    private void updateui() {
        mv.setText(String.valueOf(datechecker_viewmodel.getVm()));
        mn.setText(String.valueOf(datechecker_viewmodel.getNm()));
        vm.setText(String.valueOf(datechecker_viewmodel.getVm()));
        vn.setText(String.valueOf(datechecker_viewmodel.getNv()));
        nm.setText(String.valueOf(datechecker_viewmodel.getNm()));
        nv.setText(String.valueOf(datechecker_viewmodel.getNv()));
    }
    private void prices()
    {
        if(book.getDeparture_station().equals("Nairobi")&&book.getDestination_station().equals("Mombasa"))
        {
            book.setPrice(datechecker_viewmodel.getNm());
        }
        else
            if(book.getDeparture_station().equals("Mombasa")&&book.getDestination_station().equals("Nairobi"))
        {
            book.setPrice(datechecker_viewmodel.getNm());
        }
        else
            if(book.getDeparture_station().equals("Mombasa")&&book.getDestination_station().equals("Voi"))
        {
            book.setPrice(datechecker_viewmodel.getVm());
        }
            else
            if(book.getDeparture_station().equals("Voi")&&book.getDestination_station().equals("Mombasa"))
            {
                book.setPrice(datechecker_viewmodel.getVm());
            }
        else
            if(book.getDeparture_station().equals("Nairobi")&&book.getDestination_station().equals("Voi"))
            {
                book.setPrice(datechecker_viewmodel.getNv());
            }
            else
            if(book.getDeparture_station().equals("Voi")&&book.getDestination_station().equals("Nairobi"))
            {
                book.setPrice(datechecker_viewmodel.getNv());
            }

    }
    View.OnClickListener booking = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            firebaseDatabase = FirebaseDatabase.getInstance().getReference();
            String sYear =String.valueOf(book.getmYear());
            String sMonth =String.valueOf(book.getmMonth());
            String sDay =String.valueOf(book.getmDay());
            firebaseDatabase.child(sYear).child(sMonth).child(sDay)
                    .child(book.getDeparture_station()).child(book.getDestination_station())
                    .child(book.getEconomical_status())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.e("sam", "" + dataSnapshot.getChildrenCount());
                            int seats= (int) dataSnapshot.getChildrenCount();
                            datechecker_viewmodel.setSeat_availabe(seats);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            if(datechecker_viewmodel.getSeat_availabe()>=datechecker_viewmodel.getMaximumseats())
            {
                Log.e("sam" , "no seats available");
            }
            else {
                Log.e("sam" , "there are seats available");
                prices();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frags_container, new TrainEntry_frag(), "Booking").addToBackStack(null).commit();
            }

        }
    };


}
