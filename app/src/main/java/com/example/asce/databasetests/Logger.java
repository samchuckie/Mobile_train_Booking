package com.example.asce.databasetests;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Logger extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        Log.e("sam", " first instance of user is"+ currentUser);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser!=null)
        {
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    public void logger(View view) {

        String email =username.getText().toString();
        String pass =password.getText().toString();
        if (TextUtils.isEmpty(email)==false && TextUtils.isEmpty(pass)==false) {


            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Log.e("sam", "wrong credientials");
                    }
                }
            });
        }
        else
        {
            Log.e("sam", "enter something");
        }


    }
}
