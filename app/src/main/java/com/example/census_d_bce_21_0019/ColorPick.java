package com.example.census_d_bce_21_0019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ColorPick extends AppCompatActivity {
    Button button;
    RelativeLayout relativeLayout;
    int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_pick);

        button = findViewById(R.id.btn);
        relativeLayout = findViewById(R.id.layout);

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        defaultColor = sharedPreferences.getInt("color",0);
        if (defaultColor==0)
            defaultColor = ContextCompat.getColor(ColorPick.this ,R.color.white);

        relativeLayout.setBackgroundColor(defaultColor);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });
    }

    public void openColorPicker(){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                relativeLayout.setBackgroundColor(defaultColor);
                SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("color",defaultColor);
                editor.apply();
                Intent intent = new Intent(ColorPick.this,Home.class);
                startActivity(intent);
                finish();

            }
        });
        ambilWarnaDialog.show();

    }
}