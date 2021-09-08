package com.example.radio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.radio.Activity.InfoHrActivity;
import com.example.radio.Model.AllData;
import com.example.radio.Model.RegisterModel;
import com.example.radio.databinding.ActivityEmployeEditProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeEditProfileActivity extends AppCompatActivity {




    CircleImageView imageView;
    TextView nameEdittex,nEmail,phone_number,date_join, birth_date, addressAdd, adharcartext, bloodgropu, imguul;
    DatabaseReference database;
    FirebaseAuth auth1;
    Spinner designation, blood_gp;

    RadioButton radioButton_emp, radioButton_hr;


    ImageView imgview_back;

    TextView submit_textview;
    private RadioGroup radioGroup;
     String selector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_employe_edit_profile);
        imgview_back = findViewById(R.id.back_arrow2);

        submit_textview = findViewById(R.id.signupTextView);
        imageView= findViewById(R.id.imageview_account_profile);
        nameEdittex= findViewById(R.id.editTextTextPersonName);
        nEmail = findViewById(R.id.userEmail);
        phone_number= findViewById(R.id.editTextTextPersonName6);
        designation= findViewById(R.id.editTextTextPersonName7);


       date_join= findViewById(R.id.spinner2);
       birth_date= findViewById(R.id.editTextTextPersonDate);
       addressAdd= findViewById(R.id.editTextTextPersonAddress);
       adharcartext = findViewById(R.id.editTextTextPersonAharCard);

       blood_gp= findViewById(R.id.editTextTextPersonbloodgroup);

        radioGroup = findViewById(R.id.rbg);

        radioButton_hr = findViewById(R.id.radiobuttomHr);
        radioButton_emp = findViewById(R.id.radiobuttonemp);

        RadioButton value = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        String mselector = value.getText().toString();




        imgview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(EmployeEditProfileActivity.this, InfoHrActivity.class));

            }
        });

        auth1 = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference().child("user").child(auth1.getCurrentUser().getUid());

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    AllData allData= snapshot.getValue(AllData.class);


                    if(allData.getImg_url() !=null){

                    }
                    else {

                        Glide.with(EmployeEditProfileActivity.this).load(allData.getImg_url()).load(imageView);

                    }


                     nameEdittex.setText(allData.getmName());
                     nEmail.setText(allData.getnEmail());
                     phone_number.setText(allData.getmPhoneNumer());
                     date_join.setText(allData.getMjointate());
                     birth_date.setText(allData.getMbirthdate());
                     addressAdd.setText(allData.getmAddress());
                     adharcartext.setText(allData.getmAdharcar());

//                RadioButton value = (RadioButton) findViewById(blood_gp.getch());
//                mselector = value.getText().toString();

                   if(mselector.equals("HR")){


                       radioButton_hr.setSelected(true);

                   }

                   else {
                       radioButton_emp.setSelected(true);
                   }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        submit_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String  url=
                String  name= nameEdittex.getText().toString();
                String  email = nEmail.getText().toString();
                String number  = phone_number.getText().toString();
                String join_date= date_join.getText().toString();
                String  date_birth= birth_date.getText().toString();


                String address_emp= addressAdd.getText().toString();
                String addhardcard = adharcartext.getText().toString();

                RadioButton value = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                selector = value.getText().toString();

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        switch (checkedId) {

                            case R.id.radiobuttonemp:
                                selector = radioButton_emp.getText().toString();
                                break;

                            case R.id.radiobuttomHr:
                                selector = radioButton_hr.getText().toString();
                        }


                    }
                });




               // mselector_value =

                RegisterModel2  registerModel2 =new RegisterModel2(name,email,number,"",join_date,date_birth,address_emp,addhardcard,"",selector, auth1.getCurrentUser().getUid(), "");


                database.setValue(registerModel2);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EmployeEditProfileActivity.this, InfoHrActivity.class));

    }
}