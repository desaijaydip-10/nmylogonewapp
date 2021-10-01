package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radio.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;
    TextView changeTexView;
    TextView logoutButon;

    String sdfdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        auth= FirebaseAuth.getInstance();

        changeTexView = findViewById(R.id.changeTextview);

        logoutButon = findViewById(R.id.logOutBbutton);

        reference = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                sdfdf=  snapshot.child("checkedstatus").getValue(String.class);

                if(sdfdf.equals("0")){
                    changeTexView.setText("PendindRequest");
                }

                else if(sdfdf.equals("1")){


                    startActivity(new Intent(DashActivity.this, UserAttendenceActivity.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logoutButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {

                    DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());
                    databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //   String userlogin2 = snapshot.child("userlogin").getValue(String.class);
                            snapshot.getRef().child("userlogin").setValue("0");
                            auth.signOut();
                            Toast.makeText(DashActivity.this, "logut succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DashActivity.this, LoginNewActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                        }
                    });

                }






            }
        });


    }
}