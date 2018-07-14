package com.example.asce.databasetests;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView  ;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth mAuth;
    public booking_viewmodel book;
    BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            android.support.v4.app.Fragment switcher = null;
            switch (id)
            {
                case R.id.home_id:
                    switcher = new Home_fragment();

                    break;
                case R.id.booking_id:
                    switcher = new Booking_fragement();
                    break;
                case R.id.checking_id:
                    switcher = new Checking_fragment()                    ;
                    break;
                case R.id.logout_id:
                    FirebaseAuth.getInstance().signOut();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frags_container , switcher).commit();


            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.e("sam", "activity createdd");
        book =new booking_viewmodel();
        Log.e("sam", "" + book.getDategotten());
        bottomNavigationView = findViewById(R.id.bottom_drawer);
        getSupportFragmentManager().beginTransaction().add(R.id.frags_container, new Home_fragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);

//
//        NavigationView navigationView = findViewById(R.id.train_nav);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int item_id=item.getItemId();
//                item.setChecked(true);
//
//                switch (item_id)
//                {
//                    case R.id.booking_id:
//                        startActivity(new Intent(getApplicationContext(), Date_checker.class));
//                        break;
//
//
//
//
//                }
//                train_drawer.closeDrawers();
//                return true;
//            }
//        });
//        mAuth =FirebaseAuth.getInstance();
//        authStateListener =new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
//                if(firebaseUser ==null){
//                    startActivity(new Intent(getApplicationContext(), Logger.class));
//
//
//                }
//                else{
//                    Log.e("sam", "you are logged in");
//                }
//            }
//        };
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                train_drawer.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mAuth.addAuthStateListener(authStateListener);
//        Log.e("sam", "added the listener in Home");
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mAuth.removeAuthStateListener(authStateListener);
//        Log.e("sam", "removed the listener in Home");
//    }
//
//}
    }
}
