package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.radio.Model.AllData;
import com.example.radio.R;
import com.example.radio.UserStatusIntetFace;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity2 extends AppCompatActivity {


    DatabaseReference databaseReference12;
    String selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                if (currentUser != null && currentUser.isEmailVerified()) {


                    databaseReference12 = FirebaseDatabase.getInstance().getReference().child("user").child(currentUser.getUid());
                   databaseReference12.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {

                           String em2 = snapshot.child("mselected").getValue(String.class);

                           if (em2.equals("HR"))
                           {

                               startActivity(new Intent(SplashActivity2.this, DashboradActivity.class));

                           }
                           else {
                               startActivity(new Intent(SplashActivity2.this, UserDataActivity.class));

                           }

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });



                } else {
                    startActivity(new Intent(SplashActivity2.this, LoginActivity.class));

                }

            }


        }, 2000);


    }


}










