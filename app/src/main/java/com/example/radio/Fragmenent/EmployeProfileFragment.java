package com.example.radio.Fragmenent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class EmployeProfileFragment extends Fragment {

    FirebaseAuth auth;
    DatabaseReference databaseReference55;
    CircleImageView circleImageView;
    ImageView editImagview;
    AllData allData1;

    ImageView imgvewback;


    ArrayList<AllData> arrayList2;
    ImageView imageView_edit;

    TextView address_textview, designation,userTextName,email_txt,mobile_text, date_jointextview, birthdate_textview, addhracar, bloodgp_textview, company_textview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_employe, container, false);
        auth = FirebaseAuth.getInstance();

        databaseReference55 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());

        circleImageView = view.findViewById(R.id.imageView2);
        editImagview = view.findViewById(R.id.imageView9);
        address_textview = view.findViewById(R.id.textView8);
        designation = view.findViewById(R.id.textView10);

        imageView_edit = view.findViewById(R.id.imageView9);

        imgvewback = view.findViewById(R.id.img);

        date_jointextview = view.findViewById(R.id.textView12);
        birthdate_textview = view.findViewById(R.id.textView13);
        addhracar = view.findViewById(R.id.textView16);
        bloodgp_textview = view.findViewById(R.id.textView18);
        company_textview = view.findViewById(R.id.textView20);
        userTextName= view.findViewById(R.id.textView23);
        email_txt= view.findViewById(R.id.textView24);
        mobile_text= view.findViewById(R.id.textView25);

        arrayList2 = new ArrayList<>();


        databaseReference55.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String em = snapshot.child("mselected").getValue(String.class);


                if (em.equals("HR")) {

                    allData1 = snapshot.getValue(AllData.class);
                    String url = allData1.getImgUrl();
                    if (url == null) {

                        circleImageView.setBackgroundResource(R.drawable.ic_user);

                    } else {

                        Glide.with(getContext()).load(url).into(circleImageView);
                    }

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

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                arrayList2.add(allData1);
                Intent intent = new Intent(getContext(), EmployeEditProfileActivity.class);
                intent.putExtra("array", arrayList2);
                startActivity(intent);
            }
        });


        return view;
    }
}