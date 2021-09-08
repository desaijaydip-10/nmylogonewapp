package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.radio.Adapter.ShowAllTimeAdapter;
import com.example.radio.Model.GetAllTimeDataModel;
import com.example.radio.databinding.ActivityListTimeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListTimeActivity extends AppCompatActivity {


    ActivityListTimeBinding showDate2Binding;
    FirebaseAuth mAuth;
    ArrayList<GetAllTimeDataModel> arrayList;
    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showDate2Binding = ActivityListTimeBinding.inflate(getLayoutInflater());
        setContentView(showDate2Binding.getRoot());


        arrayList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(mAuth.getCurrentUser().getUid()).child("checktime");


        mDatabaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    GetAllTimeDataModel getAllTimeDataModel = snapshot1.getValue(GetAllTimeDataModel.class);
                    arrayList.add(getAllTimeDataModel);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       // showDate2Binding.viewrecyclerview.hasFixedSize();
        showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        showDate2Binding.viewrecyclerview.setAdapter(new ShowAllTimeAdapter(ListTimeActivity.this, arrayList));


    }
}