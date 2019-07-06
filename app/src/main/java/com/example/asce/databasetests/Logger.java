package com.example.asce.databasetests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Objects;
import java.util.regex.Pattern;
import static com.example.asce.databasetests.Constants.NETWORK;
import static com.example.asce.databasetests.Constants.REGEX;
import static com.example.asce.databasetests.Constants.UNREGISTERED;

public class Logger extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    EditText username, password;
    LayoutInflater inflater;


    //TODO Add forget password feature
    //TODO Do lint check
    //TODO close soft keyboard before showing toast
    //TODO change font of button
    //TODO PREVENT LOG IN LOOP
    //TODO Search for a less bright white


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        inflater = getLayoutInflater();

        authStateListener = firebaseAuth -> {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            } else {
                Log.e("sam", "not logged in");
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
        String email = username.getText().toString();
        String pass = password.getText().toString();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), Home.class));
                } else {
                    String exception = Objects.requireNonNull(task.getException()).getMessage();
                    if (exception.equals(NETWORK)){
                        Log.e("sam", "Network issues");
                        networkToast();
                    }
                    if(exception.equals(UNREGISTERED)){
                        Log.e("sam", "Sorry unregistered user");
                        userMissingToast();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Enter Something", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
//        startActivity(new Intent(getApplicationContext(), Register.class));
        startActivity(new Intent(getApplicationContext(), Home.class));
    }

    public static boolean checkEmail(String email){
        // This method is called after checking EditText/Input is not empty
        // second part max of 4 dots
        // final static String REGEX = "[a-zA-Z0-9]+@[A-z]+(.[A-z])+";
        boolean emailBool = false;
        if(!email.isEmpty()) {
            if (Pattern.matches(REGEX, email)) {
                emailBool = true;
            }
        }
        return  emailBool;
    }

    private static boolean checkPassword(String password){
        // This method is called after checking EditText/Input is not empty
        // implement check listener and display change icon accordinglt. Weak,strong,average,Really strong
        boolean checker = false;
        if(!password.trim().isEmpty()){
            checker = true;
        }
        return checker;
    }

    public static boolean confirmPassword(String passwordEditText,String confirmPasswordEditText){
        // This method is called after checking EditText/Input is not empty
        // TODO FIRST USE CHECKPASSWORD FOR WHEN ITS EMPTY
        boolean checker = false;
        if(checkPassword(passwordEditText) && checkPassword(confirmPasswordEditText)){
            if(passwordEditText.equals(confirmPasswordEditText)){
                checker = true;
            }
        }
        return checker;
    }

    private void networkToast() {
        View network_toast = inflater.inflate(R.layout.networktoast, findViewById(R.id.network_toast));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(network_toast);
        toast.show();
    }

    private void userMissingToast() {
        View network_toast = inflater.inflate(R.layout.usermissingtoast,  findViewById(R.id.user_missing_toast));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(network_toast);
        toast.show();
    }
}