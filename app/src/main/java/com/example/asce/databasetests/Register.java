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
  //  ConnectivityManager cm ;
   // NetworkInfo activeNetwork;

    boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         //activeNetwork = cm.getActiveNetworkInfo();


        reg_password=findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        reg_username=findViewById(R.id.new_username);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register_user(View view) {
        if(!TextUtils.isEmpty(reg_username.getText().toString()) && !TextUtils.isEmpty(reg_password.getText().toString())&&!TextUtils.isEmpty(confirm_password.getText().toString()))
        {
            if(getinfo())
            {
                firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(this ,oncomplete);
                startActivity(new Intent(this , Logger.class));

            }
            else {
                Toast.makeText(this , "The passwords should match" ,Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this ,"Please input all the fields",Toast.LENGTH_SHORT).show();
        }
//        cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        Log.e("sam", " " + isConnected);
    }
    private Boolean getinfo()
    {
        Boolean passwordmatches = false;
        user = reg_username.getText().toString();
        pass = reg_password.getText().toString();
        confirm = confirm_password.getText().toString();
        if(pass.equals(confirm))
        {
            passwordmatches =true;
        }

        return passwordmatches;

    }
}
