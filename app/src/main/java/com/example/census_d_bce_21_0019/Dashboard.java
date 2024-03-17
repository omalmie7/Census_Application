package com.example.census_d_bce_21_0019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    EditText username,password;
    Button login,register;
    private int errorCount=0;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);

        preferences= getSharedPreferences("Userinfo",0);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue= username.getText().toString();
                String passwordValue= password.getText().toString();

                String registeredUsername = preferences.getString("username","");
                String registeredPassword = preferences.getString("password","");

                if(usernameValue.equals(registeredUsername) && passwordValue.equals(registeredPassword)){
                    Intent intent = new Intent(Dashboard.this,Home.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    password.setError("Wrong Password");
                    errorCount++;
                    if(errorCount>=3){
                        Context context= getApplicationContext();
                        CharSequence text="Wrong password, Closing App";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast= Toast.makeText(context,text,duration);
                        toast.show();

                        Dashboard.this.finish();
                        System.exit(0);

                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Register.class);
                startActivity(intent);
            }
        });


    }
}