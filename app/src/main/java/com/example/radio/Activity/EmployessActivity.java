package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.radio.Adapter.EmployessAdapter;
import com.example.radio.Fragmenent.EmployeInfoFragment;
import com.example.radio.Interface.ShowAllDataInterfsce;
import com.example.radio.Model.AllData;
import com.example.radio.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployessActivity extends AppCompatActivity {


    FirebaseAuth auth1;
  static ArrayList<AllData> arrayList;
    DatabaseReference databaseReference2;
    RecyclerView recyclerView_emp;
    ImageView back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employess);
        auth1 = FirebaseAuth.getInstance();

        auth1 = FirebaseAuth.getInstance();
        arrayList = new ArrayList<>();

        back_img = findViewById(R.id.back_arrow2);

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("user");
        recyclerView_emp = findViewById(R.id.recyclerview2_emp);


        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    AllData allData = snapshot1.getValue(AllData.class);
                    String selected = allData.getMselected();
                    boolean check = allData.isVerifyCheck();


                    if (selected.equals("Employee") && check == true) {

                        arrayList.add(allData);
                        recyclerView_emp.setLayoutManager(new LinearLayoutManager(EmployessActivity.this));
                        EmployessAdapter emp = new EmployessAdapter(EmployessActivity.this, arrayList , new ShowAllDataInterfsce() {
                            @Override
                            public void showAllDataInterface(AllData allData) {



                            }
                        });

                        recyclerView_emp.setAdapter(emp);
                    } else {

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}