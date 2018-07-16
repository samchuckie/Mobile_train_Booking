package com.example.asce.databasetests;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Logger extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        authStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
                if(firebaseUser !=null){
                    startActivity(new Intent(getApplicationContext(),Home.class));

                }
                else{
                    Log.e("sam", "not logged in");
                }
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
        Log.e("sam", "added the listener in Logger");

    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(authStateListener);
        Log.e("sam", "removed the listener in Logger");
    }

    public void logger(View view) {

        String email =username.getText().toString();
        String pass =password.getText().toString();
        if (TextUtils.isEmpty(email)==false && TextUtils.isEmpty(pass)==false) {


            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(getApplicationContext(), Home.class));
                    } else {
                        Log.e("sam", "wrong credientials");
                    }
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Enter Something" , Toast.LENGTH_SHORT).show();
        }


    }

    public void register(View view) {
        startActivity(new Intent(getApplicationContext(), Register.class));
    }
}
