package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.radio.EmployeEditProfileActivity;
import com.example.radio.Model.AllData;
import com.example.radio.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmpProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference databaseReference55;
    CircleImageView circleImageView;
    ImageView editImagview;
    AllData allData;
    ImageView editimg, back_img;

    TextView name_text, email_txt, number_textview;
    TextView address_textview, designation, date_jointextview, birthdate_textview, addhracar, bloodgp_textview, company_textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_profile);

        auth = FirebaseAuth.getInstance();
        databaseReference55 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());


        circleImageView = findViewById(R.id.imageView2);
        editImagview = findViewById(R.id.imageView9);
        address_textview = findViewById(R.id.textView8);
        designation = findViewById(R.id.textView10);
        name_text = findViewById(R.id.textView23);
        email_txt = findViewById(R.id.textView24);
        number_textview = findViewById(R.id.phonenumberTextView);

        editimg = findViewById(R.id.editImg);


        back_img = findViewById(R.id.backarrow);



        editimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmpProfileActivity.this, EmployeEditProfileActivity.class);
                startActivity(intent);
            }
        });


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmpProfileActivity.this, UserAttendenceActivity.class);
                startActivity(intent);
            }
        });


        date_jointextview = findViewById(R.id.textView12);
        birthdate_textview = findViewById(R.id.textView14);
        addhracar = findViewById(R.id.textView16);
        bloodgp_textview = findViewById(R.id.textView18);
        company_textview = findViewById(R.id.punchIntextView);

        databaseReference55.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String em = snapshot.child("mselected").getValue(String.class);


                if (em.equals("Employee")) {

                    allData = snapshot.getValue(AllData.class);
                    String url = allData.getImgUrl();

                    if (url==null) {
                        circleImageView.setBackgroundResource(R.drawable.ic_user);

                    } else {
                        Glide.with(EmpProfileActivity.this).load(url).into(circleImageView);
                    }

                    name_text.setText(allData.getmName());
                    email_txt.setText(allData.getnEmail());



                    address_textview.setText(allData.getmAddress());
                    designation.setText(allData.getMdesignation());
                    date_jointextview.setText(allData.getMjointate());
                    birthdate_textview.setText(allData.getMbirthdate());
                    addhracar.setText(allData.getmAdharcar());
                    bloodgp_textview.setText(allData.getmBloodgrp());
                    company_textview.setText(allData.getmCompanyemail());
                    number_textview.setText(allData.getmPhoneNumer());
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
        onBackPressed();
    }
}