package com.example.asce.databasetests;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText reg_username, reg_password ,confirm_password;
    String user,pass,confirm;
    private FirebaseAuth firebaseAuth;
    private OnCompleteListener<AuthResult> oncomplete = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful())
            {
                Toast.makeText(getApplicationContext() , "Succesfull" , Toast.LENGTH_SHORT).show();

            }

        }
    };
    ConnectivityManager cm ;

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_password=findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        reg_username=findViewById(R.id.new_username);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register_user(View view) {
        getinfo();
        //firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(this ,oncomplete);
        cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        Log.e("sam", " " + isConnected);
    }
    private void getinfo()
    {
        user = reg_username.getText().toString();
        pass = reg_password.getText().toString();
        confirm = confirm_password.getText().toString();

    }
}
