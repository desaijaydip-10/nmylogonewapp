package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.radio.EmployeEditProfileActivity;
import com.example.radio.Fragmenent.AttendenceFragment;
import com.example.radio.Fragmenent.EmployeInfoFragment;
import com.example.radio.Fragmenent.EmployeProfileFragment;
import com.example.radio.Model.AllData;
import com.example.radio.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoHrActivity extends AppCompatActivity {

    NavigationView navigationView;
    ImageView imageView, imageView_editview;
    TextView textView1;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    DatabaseReference databaseReference;


    CircleImageView profile_img;
    TextView name_, email_textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hr);


        navigationView = findViewById(R.id.navigation_view);
        imageView = findViewById(R.id.img);
        textView1 = findViewById(R.id.txtview45);
        drawerLayout = findViewById(R.id.drawer);
        auth = FirebaseAuth.getInstance();
        imageView_editview = findViewById(R.id.imageView10);


        View headerview = navigationView.getHeaderView(0);
        profile_img = headerview.findViewById(R.id.imageView_profile);
        name_ = headerview.findViewById(R.id.textView_name);
        email_textview = headerview.findViewById(R.id.textView_email);


//
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String em = snapshot.child("mselected").getValue(String.class);
//                String  mName = snapshot.child("mName").getValue(String.class);
//
//                String mCompany_email = snapshot.child("mCompanyemail").getValue(String.class);
//                String img_url = snapshot.child("imageUri").getValue(String.class);


//                if(img_url != null){
//
//                    Glide.with(InfoHrActivity.this).load(img_url).into(profile_img);
//                }
//
//                else {
//                   // profile_img.setImageResource(R.drawable.ic_user);
//                }

                //   name_textview.setText(mName);

                //   email_textview.setText(mCompany_email);

                if (em.equals("HR")) {

                    AllData allData = snapshot.getValue(AllData.class);

                       if(allData.getImgUrl() !=null){
                           Glide.with(InfoHrActivity.this).load(allData.getImgUrl()).into(profile_img);
                       }

                       else {

                           profile_img.setImageResource(R.drawable.ic_user);
                       }

                     name_.setText(allData.getmName());
                    email_textview.setText(allData.getmCompanyemail());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageView_editview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InfoHrActivity.this, EmployeEditProfileActivity.class);
                startActivity(intent);

            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AttendenceFragment()).commit();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment temp2 = null;


                  if(item.getItemId()==R.id.attendece_){
                      //temp2 = new AttendenceFragment();
                      textView1.setText("attendece");
                      imageView_editview.setVisibility(View.GONE);
                      getSupportFragmentManager().beginTransaction().replace(R.id.container, new AttendenceFragment()).commit();


                      drawerLayout.closeDrawers();
                  }
                  else  if(item.getItemId()== R.id.profile_) {

                      startActivity(new Intent(InfoHrActivity.this, HrProfileActivity.class));

                      imageView_editview.setVisibility(View.VISIBLE);
                      drawerLayout.closeDrawers();

                  }

                  else  if(item.getItemId()==R.id.emp_info_){

                      getSupportFragmentManager().beginTransaction().replace(R.id.container, new EmployeInfoFragment()).commit();

                      textView1.setText("Employee info");
                      imageView_editview.setVisibility(View.GONE);
                      drawerLayout.closeDrawers();
                     // temp2 = new EmployeInfoFragment();

                  }

                  else {
                      logOut();
                  }





//                switch (item.getItemId()) {
//
//
//                    case R.id.attendece_:
//
//                        temp2 = new AttendenceFragment();
//                        textView1.setText("attendece");
//                        imageView_editview.setVisibility(View.GONE);
//                        drawerLayout.closeDrawers();
//                        break;
//
//                    case R.id.profile_:
//
//                        textView1.setText("public");
//                        imageView_editview.setVisibility(View.VISIBLE);
//                        drawerLayout.closeDrawers();
//
//                        temp2 = new EmployeProfileFragment();
//
//
//                        break;
//
//                    case R.id.emp_info_:
//
//                        textView1.setText("Employee info");
//                        imageView_editview.setVisibility(View.GONE);
//                        drawerLayout.closeDrawers();
//                        temp2 = new EmployeInfoFragment();
//
//
//                        break;
//
//                  case R.id.logout_:
//
//                      logOut();
////                        FirebaseUser user = auth.getCurrentUser();
////
////                        if (user != null) {
////
////                            auth.signOut();
////                            startActivity(new Intent(InfoHrActivity.this, LoginActivity.class));
////                        }
//                }






              //  getSupportFragmentManager().beginTransaction().replace(R.id.container, temp2).addToBackStack(null).commit();

                return true;
            }



        });


    }

    private void logOut() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //   String userlogin2 = snapshot.child("userlogin").getValue(String.class);
                    snapshot.getRef().child("userlogin").setValue("0");
                    auth.signOut();


                    Toast.makeText(InfoHrActivity.this, "logut succesfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InfoHrActivity.this, LoginNewActivity.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {


                }
            });


        }
    }
}