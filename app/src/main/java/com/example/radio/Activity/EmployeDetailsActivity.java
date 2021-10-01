package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.radio.Adapter.EmployeDetailAdapter;
import com.example.radio.Model.LeaveGetModel;
import com.example.radio.R;
import com.example.radio.databinding.ActivityEmployeDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeDetailsActivity extends AppCompatActivity {


    DatabaseReference reference;
    FirebaseAuth auth;
    ActivityEmployeDetailsBinding employeDetailsBinding;
    ArrayList<LeaveGetModel> modelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employeDetailsBinding = ActivityEmployeDetailsBinding.inflate(getLayoutInflater());
        setContentView(employeDetailsBinding.getRoot());

        modelArrayList = new ArrayList<>();

        String userid = getIntent().getStringExtra("userid");

        reference = FirebaseDatabase.getInstance().getReference().child("timeselector").child(userid);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("timeselector");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                modelArrayList.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    String cat = snapshot1.getKey();


                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("timeselector")
                            .child(userid).child(cat).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

//                               String  dates=  snapshot.child("leavetype").getValue(String.class);

                            LeaveGetModel leaveGetModel = snapshot.getValue(LeaveGetModel.class);
                            modelArrayList.add(leaveGetModel);


                            employeDetailsBinding.recyclerviewEmpDetails.setLayoutManager(new LinearLayoutManager(EmployeDetailsActivity.this));

                            EmployeDetailAdapter employeDetailAdapter = new EmployeDetailAdapter(EmployeDetailsActivity.this, modelArrayList, new EmployeStatusInterface() {
                                @Override
                                public void employeStatus(String status, String date, String userid) {

                                    databaseReference.child(userid).child(date).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                                            snapshot.getRef().child("statuscheck").setValue(status);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


//                                    Log.e("asdad", status+"//"+userid);


                                }
                            });

                            employeDetailAdapter.notifyDataSetChanged();
                            employeDetailsBinding.recyclerviewEmpDetails.setAdapter(employeDetailAdapter);


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
}