package com.example.census_d_bce_21_0019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Home extends AppCompatActivity {
    Button button2,button3,view;
    RelativeLayout relativeLayout;
    int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        view=findViewById(R.id.view);

        relativeLayout = findViewById(R.id.layout);

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        defaultColor = sharedPreferences.getInt("color",0);
        if (defaultColor==0)
            defaultColor = ContextCompat.getColor(Home.this ,R.color.white);

        relativeLayout.setBackgroundColor(defaultColor);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Add.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, ColorPick.class);
                startActivity(intent);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,Userlist.class));
            }
        });

    }
}