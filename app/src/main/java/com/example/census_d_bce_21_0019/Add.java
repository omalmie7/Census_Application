package com.example.census_d_bce_21_0019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Add extends AppCompatActivity {

    EditText name, age;
    RadioButton male, female;
    Button save, capture_button;
    DBHelper DB;
    Intent camera;
    ImageView imageView;
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.name2);
        age = findViewById(R.id.age);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        capture_button = findViewById(R.id.capture_button);
        imageView = findViewById(R.id.imageView);
        save = findViewById(R.id.save);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        DB = new DBHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String ageTXT = age.getText().toString();
                String genderString = male.isChecked() ? "Male" : "Female";

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, ageTXT, genderString,encodedImage);
                if (checkinsertdata == true) {
                    Toast.makeText(Add.this, "New Entry Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Add.this, "New Entry Not Inserted", Toast.LENGTH_LONG).show();
                }

            }
        });

        capture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((ActivityCompat.checkSelfPermission(
                        Add.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{
                                Manifest.permission.CAMERA,
                        }, 123);
                    }
                } else {
                    camera = new Intent();
                    camera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera, 118);
                }
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==118&& resultCode==RESULT_OK){
            Bitmap photo= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            Toast.makeText(this, "Image saved in SharedPreferences", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "could not captured", Toast.LENGTH_SHORT).show();
}
}
}
