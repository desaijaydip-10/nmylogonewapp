package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radio.Model.AllData;
import com.example.radio.R;

import com.example.radio.databinding.ActivityLoginNewBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginNewActivity extends AppCompatActivity {


    ActivityLoginNewBinding loginNewBinding;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth auth;
    private DatabaseReference databaseReference12;
    ArrayList<AllData> arrayList;
    private int device= 0;
    boolean isReady = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginNewBinding = ActivityLoginNewBinding.inflate(getLayoutInflater());
        setContentView(loginNewBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        arrayList = new ArrayList<>();


        loginNewBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loginNewBinding.emailtextinputLayout.getEditText().getText().toString().isEmpty()) {

                    loginNewBinding.EditTextName11.setError("Enter Valid Email");
                    loginNewBinding.EditTextName11.requestFocus();

                } else if (!loginNewBinding.emailtextinputLayout.getEditText().getText().toString().matches(emailPattern)) {

                    loginNewBinding.EditTextName11.setError("Please Enter Valid Email");
                    loginNewBinding.EditTextName11.requestFocus();

                } else if (loginNewBinding.textinputLayoutpassword.getEditText().getText().toString().isEmpty()) {

                    loginNewBinding.passwordtextEditText.setError("enter password");
                    loginNewBinding.passwordtextEditText.requestFocus();

                } else {

                    AlertDialog.Builder builderlog = new AlertDialog.Builder(LoginNewActivity.this);
                    AlertDialog dialoglog = builderlog.create();
                    dialoglog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    View view2 = getLayoutInflater().inflate(R.layout.spinner_dialoge, null);
                    ProgressBar progressBar = view2.findViewById(R.id.progressbar43);

                    progressBar.setVisibility(View.VISIBLE);
                    dialoglog.setView(view2);
                    dialoglog.show();

                    auth.signInWithEmailAndPassword(loginNewBinding.emailtextinputLayout.getEditText().getText().toString(), loginNewBinding.textinputLayoutpassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                                            String sd = snapshot.child("checkedstatus").getValue(String.class);
                                            String userlogin = snapshot.child("userlogin").getValue(String.class);
                                            snapshot.getRef().child("verifyCheck").setValue(true);


                                            if (userlogin.equals("0")) {

                                                if (!isReady) {

                                                    if (em.equals("HR")) {

                                                        startActivity(new Intent(LoginNewActivity.this, InfoHrActivity.class));
                                                        snapshot.getRef().child("userlogin").setValue("1");

                                                        isReady = true;

                                                    } else {

                                                        if (sd.equals("0")) {

                                                            startActivity(new Intent(LoginNewActivity.this, DashActivity.class));
                                                            snapshot.getRef().child("userlogin").setValue("1");
                                                            isReady = true;

                                                        } else if (sd.equals("1")) {
                                                            startActivity(new Intent(LoginNewActivity.this, UserAttendenceActivity.class));
                                                            snapshot.getRef().child("userlogin").setValue("1");
                                                            isReady = true;
                                                        }
                                                    }
                                                }
                                            } else {
                                                Toast.makeText(LoginNewActivity.this, "User already logging", Toast.LENGTH_SHORT).show();
                                            }


//                                            String em = snapshot.child("mselected").getValue(String.class);
//                                            String userlogin = snapshot.child("userlogin").getValue(String.class);
//                                            snapshot.getRef().child("verifyCheck").setValue(true);
//
//
//
//
//                                            if(userlogin.equals("0")){
//
//                                                if (em.equals("HR"))
//                                                {
//                                                    startActivity(new Intent(LoginActivity.this,InfoHrActivity .class));
//                                                    snapshot.getRef().child("userlogin").setValue("1");
//
//                                                } else {
//
//                                                    startActivity(new Intent(LoginActivity.this, UserAttendenceActivity.class));
//                                                    snapshot.getRef().child("userlogin").setValue("1");
//                                                }
//                                            }
//
//                                            else {
//                                                Toast.makeText(LoginActivity.this, "User already logging", Toast.LENGTH_SHORT).show();
//                                            }



                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {


                                        }
                                    });


                                } else {
                                    Toast.makeText(LoginNewActivity.this, "please verify Email ", Toast.LENGTH_SHORT).show();
                                }


                            } else {

                                dialoglog.dismiss();
                                Toast.makeText(LoginNewActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });


        loginNewBinding.logintextview11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginNewActivity.this, SignUpActivity.class));

            }
        });
        loginNewBinding.textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginNewActivity.this);

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

                                        Toast.makeText(LoginNewActivity.this, "check your Email", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(LoginNewActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
}