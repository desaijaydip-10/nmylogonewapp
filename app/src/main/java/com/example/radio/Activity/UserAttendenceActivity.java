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
import com.example.radio.Fragmenent.AttendenceFragment;
import com.example.radio.Fragmenent.EmployeInfoFragment;
import com.example.radio.Fragmenent.HrProfilekFragment;
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

public class UserAttendenceActivity extends AppCompatActivity {


    DrawerLayout drawer;
    ImageView imageView;
    NavigationView navigationView;
    TextView textView1;
    FirebaseAuth auth;


    CircleImageView profile_;
    TextView name_emp, email_emp;



    DatabaseReference databaseReference_employe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attendence);
        auth = FirebaseAuth.getInstance();


        imageView = findViewById(R.id.img);
        navigationView = findViewById(R.id.nav_view);
        textView1 = findViewById(R.id.txtview45);

        drawer = findViewById(R.id.drawer_);
        navigationView = findViewById(R.id.nav_view);


        View headerview = navigationView.getHeaderView(0);
        profile_ = headerview.findViewById(R.id.imageView_profile);


        name_emp = headerview.findViewById(R.id.textView_name);
        email_emp = headerview.findViewById(R.id.textView_email);



        databaseReference_employe = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());

         databaseReference_employe.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {

                 String em = snapshot.child("mselected").getValue(String.class);

                 if (em.equals("Employee")) {

                     AllData allData = snapshot.getValue(AllData.class);
                     if(allData.getImg_url() !=null){

                         Glide.with(UserAttendenceActivity.this).load(allData.getImg_url()).into(profile_);
                     }


                     else {

                         profile_.setImageResource(R.drawable.ic_user);
                     }
                     name_emp.setText(allData.getmName());
                     email_emp.setText(allData.getmCompanyemail());

                 }


             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });



        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                drawer.openDrawer(GravityCompat.START);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AttendenceFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                  if(item.getItemId()== R.id.attendece ){

                      getSupportFragmentManager().beginTransaction().replace(R.id.container, new AttendenceFragment()).commit();

                      drawer.closeDrawers();
                  }
                  else if(item.getItemId()==R.id.profile ){
                      getSupportFragmentManager().beginTransaction().replace(R.id.container, new HrProfilekFragment()).commit();

                      drawer.closeDrawers();
                  }
                  else if(item.getItemId() == R.id.leaves){

                     // getSupportFragmentManager().beginTransaction().replace(R.id.container, new HrProfilekFragment()).commit();

                      drawer.closeDrawers();

                  }

                  else {
                      logout();
                  }




//                Fragment temp = null;
//                switch (item.getItemId()) {
//
//
//                    case R.id.attendece:
//
//                        temp = new AttendenceFragment();
//                        drawer.closeDrawers();
//
//                        // Toast.makeText(InfoHrActivity.this, "attendence", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.profile:
//
//                        temp = new HrProfilekFragment();
//                        drawer.closeDrawers();
//
//                        // Toast.makeText(InfoHrActivity.this, "profile", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.leaves:
//
//
//                        // getSupportFragmentManager().beginTransaction().replace(R.id.container_,new EmployeInfoFragment()).commit();
////                         imageView.setVisibility(View.GONE);
//
//                        //  imageView.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24);
//                        break;
//
//                    case R.id.loggout:
//
//                        Toast.makeText(UserAttendenceActivity.this, "log out", Toast.LENGTH_SHORT).show();
//                        break;
//
////                        FirebaseUser user = auth.getCurrentUser();
////
////                        if (user != null) {
////                            auth.signOut();
////
////                            startActivity(new Intent(UserAttendenceActivity.this, LoginActivity.class));
////                        }
//
//                }
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, temp).commit();


                return true;
            }
        });


    }

    private void logout() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {

            DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());
            databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //   String userlogin2 = snapshot.child("userlogin").getValue(String.class);
                    snapshot.getRef().child("userlogin").setValue("0");
                    auth.signOut();
                    Toast.makeText(UserAttendenceActivity.this, "logut succesfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserAttendenceActivity.this, LoginActivity.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {


                }
            });

        }

    }
}