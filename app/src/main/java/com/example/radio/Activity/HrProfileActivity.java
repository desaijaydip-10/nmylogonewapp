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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HrProfileActivity extends AppCompatActivity {


    FirebaseAuth auth;
    DatabaseReference databaseReference55;
    CircleImageView circleImageView;
    ImageView editImagview;
    AllData allData1;

    ImageView imgvewback;


    ArrayList<AllData> arrayList2;
    ImageView imageView_edit;
    ImageView editimg, back_img;

    TextView address_textview, designation, userTextName, email_txt, mobile_text, date_jointextview, birthdate_textview, addhracar, bloodgp_textview, company_textview, date_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_profile);
        auth = FirebaseAuth.getInstance();
        databaseReference55 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());


        circleImageView = findViewById(R.id.imageView2);
        editImagview = findViewById(R.id.imageView9);
        address_textview = findViewById(R.id.textView8);
        designation = findViewById(R.id.textView10);

        date_join = findViewById(R.id.textView14);


        imageView_edit = findViewById(R.id.imageView9);
        imgvewback = findViewById(R.id.img);

        date_jointextview = findViewById(R.id.textView12);
        birthdate_textview = findViewById(R.id.textView13);
        addhracar = findViewById(R.id.textView16);
        bloodgp_textview = findViewById(R.id.textView18);
        company_textview = findViewById(R.id.punchIntextView);
        userTextName = findViewById(R.id.textView23);
        email_txt = findViewById(R.id.textView24);
        mobile_text = findViewById(R.id.textView25);

        editimg = findViewById(R.id.editImg);

        back_img = findViewById(R.id.backarrow);


        arrayList2 = new ArrayList<>();


        editimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HrProfileActivity.this, EmployeEditProfileActivity.class);
                startActivity(intent);
            }
        });


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HrProfileActivity.this, InfoHrActivity.class);
                startActivity(intent);
            }
        });

        databaseReference55.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String em = snapshot.child("mselected").getValue(String.class);


                if (em.equals("HR")) {



                    allData1 = snapshot.getValue(AllData.class);
                    String url = allData1.getImgUrl();

                    if (allData1.getImgUrl()==null)
                    {

                        circleImageView.setBackgroundResource(R.drawable.ic_user);

                    } else {
                        Glide.with(HrProfileActivity.this).load(url).into(circleImageView);
                    }

                    userTextName.setText(allData1.getmName());
                    email_txt.setText(allData1.getnEmail());
                    mobile_text.setText(allData1.getmPhoneNumer());
                    address_textview.setText(allData1.getmAddress());
                    designation.setText(allData1.getMdesignation());
                    date_jointextview.setText(allData1.getMjointate());

                    date_join.setText(allData1.getMbirthdate());
                    addhracar.setText(allData1.getmAdharcar());
                    bloodgp_textview.setText(allData1.getmBloodgrp());

                    company_textview.setText(allData1.getmCompanyemail());
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