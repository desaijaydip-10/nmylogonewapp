package com.example.radio.Fragmenent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class HrProfilekFragment extends Fragment {

    FirebaseAuth auth;
    DatabaseReference databaseReference55;
    CircleImageView circleImageView;
    ImageView editImagview, editimg;
    AllData allData;
    TextView address_textview, designation, date_jointextview, birthdate_textview, addhracar, bloodgp_textview, company_textview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_hr_profilek, container, false);
         auth= FirebaseAuth.getInstance();
        databaseReference55 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());


        circleImageView = view.findViewById(R.id.imageView2);
        editImagview = view.findViewById(R.id.imageView9);
        address_textview = view.findViewById(R.id.textView8);
        designation = view.findViewById(R.id.textView10);
        editimg = view.findViewById(R.id.imageView9);


        date_jointextview = view.findViewById(R.id.textView12);
        birthdate_textview = view.findViewById(R.id.textView13);
        addhracar = view.findViewById(R.id.textView16);
        bloodgp_textview = view.findViewById(R.id.textView18);
        company_textview = view.findViewById(R.id.textView20);


        editimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        databaseReference55.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String em = snapshot.child("mselected").getValue(String.class);


                if (em.equals("Employee")) {

                    allData = snapshot.getValue(AllData.class);
                    String url = allData.getImg_url();
                    if (url == null) {


                        circleImageView.setBackgroundResource(R.drawable.ic_user);

                    } else {

                        Glide.with(getContext()).load(url).into(circleImageView);
                    }

                    address_textview.setText(allData.getmAddress());
                    designation.setText(allData.getMdesignation());
                    date_jointextview.setText(allData.getMjointate());
                    birthdate_textview.setText(allData.getMbirthdate());
                    addhracar.setText(allData.getmAdharcar());
                    bloodgp_textview.setText(allData.getmBloodgrp());
                    company_textview.setText(allData.getmCompanyemail());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), EmployeEditProfileActivity.class);
                intent.putExtra("MyClass", (Parcelable) allData);

                startActivity(intent);


            }
        });


        return view;
    }
}