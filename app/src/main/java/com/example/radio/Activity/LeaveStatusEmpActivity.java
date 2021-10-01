package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.radio.Adapter.EmployeStatusAdapter;
import com.example.radio.Adapter.EmployessAdapter;
import com.example.radio.Model.AllData;
import com.example.radio.Model.LeaveGetModel;
import com.example.radio.Model.NewDataGetModel;
import com.example.radio.R;
import com.example.radio.databinding.LeaveStatusLayoutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaveStatusEmpActivity extends AppCompatActivity {


    LeaveStatusLayoutBinding leaveStatusLayoutBinding;
    FirebaseAuth auth;
    DatabaseReference reference, ref, refrences;
    ArrayList<LeaveGetModel> arrayList;
    String userfirstid;
    public static ArrayList<AllData> allDataArrayList;


    ArrayList<NewDataGetModel> dataGetModels;
//    public static ArrayList<AllData> allDataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        leaveStatusLayoutBinding = LeaveStatusLayoutBinding.inflate(getLayoutInflater());
        setContentView(leaveStatusLayoutBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        arrayList = new ArrayList<>();

        dataGetModels = new ArrayList<>();
        allDataArrayList = new ArrayList<>();


        leaveStatusLayoutBinding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        reference = FirebaseDatabase.getInstance().getReference().child("timeselector");

        ref = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());

        refrences = FirebaseDatabase.getInstance().getReference().child("user");


        refrences.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String userfirstid = dataSnapshot.getKey();
                    AllData allData = dataSnapshot.getValue(AllData.class);


//                    leaveStatusLayoutBinding.employerecyclerview1.setLayoutManager(new LinearLayoutManager(LeaveStatusEmpActivity.this));
//                    leaveStatusLayoutBinding.employerecyclerview1.setAdapter(new EmployeStatusAdapter(LeaveStatusEmpActivity.this, allDataArrayList));
////

                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            allDataArrayList.clear();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                                String userid = snapshot1.getKey();

                                if (userfirstid.equals(userid)) {


                                    allDataArrayList.add(allData);

                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("timeselector").child(userid);
                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            arrayList.clear();


                                            for (DataSnapshot snapshot11 : snapshot.getChildren()) {


                                                if (snapshot11.getValue() != null) {

                                                    LeaveGetModel leaveGetModel = snapshot11.getValue(LeaveGetModel.class);
                                                    String category = leaveGetModel.getCategorychoose();

                                                    if (category.equals("Employee")) {

                                                        arrayList.add(leaveGetModel);

                                                    }

                                                    leaveStatusLayoutBinding.employerecyclerview1.setLayoutManager(new LinearLayoutManager(LeaveStatusEmpActivity.this));

                                                    EmployeStatusAdapter employeStatusAdapter = new EmployeStatusAdapter(LeaveStatusEmpActivity.this, arrayList, allDataArrayList);
                                                    employeStatusAdapter.notifyDataSetChanged();
                                                    leaveStatusLayoutBinding.employerecyclerview1.setAdapter(employeStatusAdapter);


                                                }

                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                                }


                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}