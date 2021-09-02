package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radio.MainActivity;
import com.example.radio.Model.AllData;
import com.example.radio.R;
import com.example.radio.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    ActivityLoginBinding activityLoginBinding;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth auth;
    private DatabaseReference databaseReference12;
    ArrayList<AllData> arrayList;
    private int device= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        auth = FirebaseAuth.getInstance();

        arrayList = new ArrayList<>();


//        databaseReference12.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                    AllData allData = snapshot1.getValue(AllData.class);
//
//                    arrayList.add(allData);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        activityLoginBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activityLoginBinding.emailtextinputLayout.getEditText().getText().toString().isEmpty()) {

                    activityLoginBinding.EditTextName11.setError("Enter Valid Email");
                    activityLoginBinding.EditTextName11.requestFocus();

                } else if (!activityLoginBinding.emailtextinputLayout.getEditText().getText().toString().matches(emailPattern)) {

                    activityLoginBinding.EditTextName11.setError("Please Enter Valid Email");
                    activityLoginBinding.EditTextName11.requestFocus();

                } else if (activityLoginBinding.textinputLayoutpassword.getEditText().getText().toString().isEmpty()) {

                    activityLoginBinding.passwordtextEditText.setError("enter password");
                    activityLoginBinding.passwordtextEditText.requestFocus();

                } else {

                    AlertDialog.Builder builderlog = new AlertDialog.Builder(LoginActivity.this);
                    AlertDialog dialoglog = builderlog.create();
                    dialoglog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    View view2 = getLayoutInflater().inflate(R.layout.spinner_dialoge, null);
                    ProgressBar progressBar = view2.findViewById(R.id.progressbar43);

                    progressBar.setVisibility(View.VISIBLE);
                    dialoglog.setView(view2);
                    dialoglog.show();

                    auth.signInWithEmailAndPassword(activityLoginBinding.emailtextinputLayout.getEditText().getText().toString(), activityLoginBinding.textinputLayoutpassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {


                                databaseReference12 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());
                                dialoglog.dismiss();

                                if (auth.getCurrentUser().isEmailVerified()) {


                                    databaseReference12.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            String em = snapshot.child("mselected").getValue(String.class);
                                            String userlogin = snapshot.child("userlogin").getValue(String.class);
                                            snapshot.getRef().child("verifyCheck").setValue(true);


                                            if(userlogin.equals("0")){

                                                if (em.equals("HR"))
                                                {
                                                    startActivity(new Intent(LoginActivity.this, DashboradActivity.class));
                                                    snapshot.getRef().child("userlogin").setValue("1");

                                                } else {

                                                    startActivity(new Intent(LoginActivity.this, UserDataActivity.class));
                                                    snapshot.getRef().child("userlogin").setValue("1");
                                                }
                                            }


                                            else {

                                                Toast.makeText(LoginActivity.this, "User already logging", Toast.LENGTH_SHORT).show();
                                            }



                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {


                                        }
                                    });


                                } else {
                                    Toast.makeText(LoginActivity.this, "please verify Email ", Toast.LENGTH_SHORT).show();
                                }


                            } else {

                                dialoglog.dismiss();
                                Toast.makeText(LoginActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });


        activityLoginBinding.logintextview11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignupActivity2.class));

            }
        });
        activityLoginBinding.textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);

                AlertDialog dialog = builder1.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                View view = getLayoutInflater().inflate(R.layout.custom_dialoge_layout, null);
                TextView textView = view.findViewById(R.id.forgotloginButton);
                TextView cancelbtn = view.findViewById(R.id.canceltextview);

                EditText editText = view.findViewById(R.id.forgotEditTextName);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString().isEmpty()) {
                            editText.setError("Filled Required");
                            editText.requestFocus();
                        } else if (!editText.getText().toString().matches(emailPattern)) {

                            editText.setError("Enter Valid  Email");
                            editText.requestFocus();

                        } else {

                            auth.sendPasswordResetEmail(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(LoginActivity.this, "check your Email", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }
                });

                cancelbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setView(view);
                dialog.show();


            }
        });


    }


    @Override
    public void onBackPressed() {

        finishAffinity();

    }
}