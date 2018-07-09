package com.example.asce.databasetests;

import android.content.Intent;
import android.support.annotation.NonNull;
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
    DrawerLayout train_drawer;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        train_drawer = findViewById(R.id.drawers);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        NavigationView navigationView = findViewById(R.id.train_nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int item_id=item.getItemId();
                item.setChecked(true);

                switch (item_id)
                {
                    case R.id.booking_id:
                        startActivity(new Intent(getApplicationContext(), Date_checker.class));
                        break;
                    case R.id.logout_id:
                        FirebaseAuth.getInstance().signOut();
                        break;




                }
                train_drawer.closeDrawers();
                return true;
            }
        });
        mAuth =FirebaseAuth.getInstance();
        authStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
                if(firebaseUser ==null){
                    startActivity(new Intent(getApplicationContext(), Logger.class));


                }
                else{
                    Log.e("sam", "you are logged in");
                }
            }
        };

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                train_drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(authStateListener);
        Log.e("sam", "added the listener in Home");

    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(authStateListener);
        Log.e("sam", "removed the listener in Home");
    }

}
