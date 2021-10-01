package com.example.radio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.radio.Model.AllData;
import com.example.radio.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {


    TextView address_textview, designation, userTextName, email_txt, mobile_text, date_jointextview, birthdate_textview, addhracar, bloodgp_textview, company_textview;
    ImageView imgvewback, back_img;
    CircleImageView circleImageView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        AllData allData1 = getIntent().getParcelableExtra("data");

        circleImageView = findViewById(R.id.imageView2);

        address_textview = findViewById(R.id.textView8);
        designation = findViewById(R.id.textView10);

        // imageView_edit = findViewById(R.id.imageView9);

        back_img = findViewById(R.id.imgview);
        imgvewback = findViewById(R.id.img);

        date_jointextview = findViewById(R.id.textView12);

        birthdate_textview = findViewById(R.id.textView14);

        addhracar = findViewById(R.id.textView16);
        bloodgp_textview = findViewById(R.id.textView18);
        company_textview = findViewById(R.id.punchIntextView);
        userTextName = findViewById(R.id.textView23);
        email_txt = findViewById(R.id.textView24);
        mobile_text = findViewById(R.id.textView25);



        if (url==null) {

            circleImageView.setBackgroundResource(R.drawable.ic_user);
        } else {

            Glide.with(this).load(url).into(circleImageView);
        }


        String email  =allData1.getnEmail();

        //  Log.e("asd", email);

        userTextName.setText(allData1.getmName());
        email_txt.setText(allData1.getnEmail());
        mobile_text.setText(allData1.getmPhoneNumer());
        address_textview.setText(allData1.getmAddress());
        designation.setText(allData1.getMdesignation());
        date_jointextview.setText(allData1.getMjointate());
        birthdate_textview.setText(allData1.getMbirthdate());
        addhracar.setText(allData1.getmAdharcar());
        bloodgp_textview.setText(allData1.getmBloodgrp());
        company_textview.setText(allData1.getmCompanyemail());


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}