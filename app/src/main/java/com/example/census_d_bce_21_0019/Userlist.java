package com.example.census_d_bce_21_0019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name,age,gender,image;
    DBHelper DB;
    MyAdapter adapter;
    Button upload;

    private FirebaseFirestore firebaseDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        age = new ArrayList<>();
        gender = new ArrayList<>();
        image = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,name,age,gender,image);
        upload = findViewById(R.id.upload);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0){
            Toast.makeText(Userlist.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while(cursor.moveToNext()){
                name.add(cursor.getString(0));
                age.add(cursor.getString(1));
                gender.add(cursor.getString(2));
                image.add(cursor.getString(3));
            }
        }

        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Cursor cursor = DB.getdata();
                if (cursor.getCount()==0) {
                    Toast.makeText(Userlist.this, "No data entered", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    Map<String,Object> data = new HashMap<>();
                    while (cursor.moveToNext()){
                        String username = cursor.getString(0);
                        data.put("Name",cursor.getString(0)+"\n");
                        data.put("Age",cursor.getString(1)+"\n");
                        data.put("Gender",cursor.getString(2)+"\n");
                        data.put("Profile Photo",cursor.getString(3)+"\n");


                        firebaseDB.collection("Census APP").document(username).set(data)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Userlist.this, "Saved to cloud", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Userlist.this, "Error in saving to cloud!", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }

                }



            }
        });

    }
}