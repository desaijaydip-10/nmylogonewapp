package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radio.Model.AllData;
import com.example.radio.Model.StatusCheckModel;
import com.example.radio.R;
import com.example.radio.databinding.ActivityUserDataBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDataActivity extends AppCompatActivity {



    ActivityUserDataBinding dataBinding;
    DatabaseReference databaseReference1;

     String value;
    TextView textView;
    String  nm;

    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        textView = findViewById(R.id.changeTextView);

        setContentView(R.layout.activity_user_data);
        textView = findViewById(R.id.changeTextView);



        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("user");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                              nm= snapshot.child(firebaseAuth.getCurrentUser().getUid()).child("checked_status").getValue(String.class);

                              if(nm.equals("0")){

                                  textView.setText("Pending !!");

                              }
                              else if(nm.equals("1")){
                                  textView.setText("Successfully  Aprrove");
                              }
                              else {

                                  textView.setText("Decline !!");
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
        finishAffinity();
    }
}