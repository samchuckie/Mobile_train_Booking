package com.example.asce.databasetests;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.asce.databasetests.Fragments.DateChecker_frag;
import com.example.asce.databasetests.Fragments.Home_fragment;
import com.example.asce.databasetests.Fragments.Improve_Home_Frag;
import com.example.asce.databasetests.Fragments.Tickets_frag;
import com.example.asce.databasetests.ViewModel.CurrentTicker;
import com.example.asce.databasetests.ViewModel.Datechecker_viewmodel;
import com.example.asce.databasetests.ViewModel.booking_viewmodel;
import com.example.asce.databasetests.ViewModel.darajaViewModel;

public  class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView  ;
    public booking_viewmodel book;
    public Datechecker_viewmodel daterviewmodel ;
    public darajaViewModel darajavm;
    CurrentTicker currentTicker;
    BottomNavigationView.OnNavigationItemSelectedListener navlistener = item -> {
        int id = item.getItemId();
        String frag_home = null;
        android.support.v4.app.Fragment switcher = null;
        switch (id)
        {
            case R.id.home_id:
                switcher = new Home_fragment();
                //switcher = new Improve_Home_Frag();
                frag_home ="home";

                break;
            case R.id.booking_id:
                switcher = new DateChecker_frag();
                frag_home ="datechecker";
                break;
            case R.id.checking_id:
                  switcher = new Tickets_frag();
                  frag_home ="Ticket";
                break;
            case R.id.logout_id:
//                    FirebaseAuth.getInstance().signOut();
//                    startActivity(new Intent(getApplicationContext(), Logger.class));
                logger();
                break;
        }
        if(frag_home!=null)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frags_container , switcher,frag_home).addToBackStack(null).commit();
        return true;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.e("sam", "activity createdd");
        book =new booking_viewmodel();
        daterviewmodel = new Datechecker_viewmodel();
        currentTicker= new CurrentTicker();
        darajavm =new darajaViewModel();
        Log.e("sam", "" + book.getDategotten());
        bottomNavigationView = findViewById(R.id.bottom_drawer);
        getSupportFragmentManager().beginTransaction().add(R.id.frags_container, new Improve_Home_Frag()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
    }

    public void  logger(){
        Logout_fragemnt logout_fragemnt = new Logout_fragemnt();
        logout_fragemnt.show(getSupportFragmentManager(),"Logout");
    }
}

