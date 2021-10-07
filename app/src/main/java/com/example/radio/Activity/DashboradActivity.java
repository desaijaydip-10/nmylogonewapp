package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.radio.Adapter.DashbordAdapter;
import com.example.radio.Fragmenent.EmployeInfoFragment;
import com.example.radio.Model.AllData;
import com.example.radio.Model.CheckModel;
import com.example.radio.R;
import com.example.radio.UserInterface;
import com.example.radio.UserStatusIntetFace;
import com.example.radio.databinding.ActivityDashboradBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboradActivity extends AppCompatActivity implements UserInterface {


    ActivityDashboradBinding dashboradBinding;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    public static ArrayList<AllData> arrayList;
    RecyclerView recyclerView;
    Toolbar toolbar;
    String selectef;
    static boolean checked;
    String value;
    DashbordAdapter dashbordAdapter;
    private DatabaseReference databaseReference1;
    AllData allData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboradBinding = ActivityDashboradBinding.inflate(getLayoutInflater());
        setContentView(dashboradBinding.getRoot());
        auth = FirebaseAuth.getInstance();
        arrayList = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        recyclerView = findViewById(R.id.recyclerview1);


        dashboradBinding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    allData = snapshot1.getValue(AllData.class);
                    String selected = allData.getMselected();


//                    String status = allData.getCheckedStatus();

                    boolean check = allData.isVerifyCheck();

                    if (selected.equals("Employee")) {

                        arrayList.add(allData);
                        recyclerView.setLayoutManager(new LinearLayoutManager(DashboradActivity.this));
                        dashbordAdapter = new DashbordAdapter(DashboradActivity.this, arrayList, new UserStatusIntetFace() {
                            @Override
                            public void userStatusInterface(String statuschecked, String postion) {

                                databaseReference.child(postion).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        snapshot.getRef().child("checkedstatus").setValue(statuschecked);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                recyclerView.setAdapter(dashbordAdapter);

                            }


                            @Override
                            public void showAllDataInterface(AllData allData) {


                                // startActivity(new Intent());

//                                Intent intent = new Intent(DashboradActivity.this,ProfileActivity.class);
//                                startActivity(intent);

                            }
                        });


                    } else {


                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        dashboradBinding.loggout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FirebaseUser user = auth.getCurrentUser();
//                if (user != null) {
//
//                    databaseReference1 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());
//                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                            //   String userlogin2 = snapshot.child("userlogin").getValue(String.class);
//                            snapshot.getRef().child("userlogin").setValue("0");
//                            auth.signOut();
//
//
//
//                            Toast.makeText(DashboradActivity.this, "logut succesfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(DashboradActivity.this, LoginActivity.class));
//
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//
//                        }
//                    });
//
//
//                }
//
//            }
//        });

    }

    private void setUpdateValue() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("statuscheck");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    CheckModel checkModel = snapshot1.getValue(CheckModel.class);
                    checked = checkModel.isChecked();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void userdata(int postion) {

        Intent intent = new Intent(DashboradActivity.this, UserDataActivity.class);
        intent.putExtra("pos", postion);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
//
//    @Override
//    public void userStatusInterface(String statuschecked, String postion) {
//
//
//
//
//
//    }
}