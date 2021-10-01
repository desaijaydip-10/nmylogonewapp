package com.example.radio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.radio.Activity.InfoHrActivity;
import com.example.radio.Activity.UserAttendenceActivity;
import com.example.radio.Adapter.SpinnerAdapterA;
import com.example.radio.Model.AllData;
import com.example.radio.utils.PhoneTextFormatter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeEditProfileActivity extends AppCompatActivity {





    CircleImageView imageView;
    EditText nameEdittex, nEmail, addressAdd, bloodgropu, imguul;
    TextView date_join, birth_date;
    EditText phone_number, adharcartext;


    DatabaseReference database;
    FirebaseAuth auth1;
    Spinner designation, blood_gp;
    FirebaseAuth auth3;


    String imageUri = "";
    String desi;
    RadioButton radioButton_emp, radioButton_hr;
    RadioGroup radioGroup;

    TextView designationErrorTextView, bloodgroupTextView;
    FloatingActionButton floatingActionButton;


    ImageView imgview_back;
    String emailPattern, regexStr;

    TextView submit_textview;


    String selector;
    Calendar myCalendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_employe_edit_profile);

        regexStr = "^[0-9]$";


        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        String[] courses = new String[]{"Designation", "Junior", "Senior", "Trainee"};
        String blodGroup[] = new String[]{"Blood group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        imgview_back = findViewById(R.id.back_arrow2);
        submit_textview = findViewById(R.id.signupTextView);
        imageView = findViewById(R.id.imageview_account_profile);
        nameEdittex = findViewById(R.id.editTextTextPersonName);
        nEmail = findViewById(R.id.userEmail);
        phone_number = findViewById(R.id.editTextTextPersonName6);

        designation = findViewById(R.id.editTextTextPersonName7);
        designationErrorTextView = findViewById(R.id.errortxt);
        bloodgroupTextView = findViewById(R.id.errortxt2);


        blood_gp = findViewById(R.id.editTextTextPersonbloodgroup);
        floatingActionButton = findViewById(R.id.floatingactionButton);
        date_join = findViewById(R.id.spinner2);
        birth_date = findViewById(R.id.editTextTextPersonDate);
        addressAdd = findViewById(R.id.editTextTextPersonAddress);
        adharcartext = findViewById(R.id.editTextTextPersonAharCard);


        auth1 = FirebaseAuth.getInstance();
        myCalendar = Calendar.getInstance();


        designation.setAdapter(new SpinnerAdapterA(this, R.layout.custom_layout, courses));


        SpinnerAdapterA spinnerAdapterA = new SpinnerAdapterA(this, R.layout.custom_layout, blodGroup);
        blood_gp.setAdapter(spinnerAdapterA);

        blood_gp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withContext(EmployeEditProfileActivity.this)
                        .withPermissions(
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.RECORD_AUDIO
                        ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                }).check();


                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.setType("image/*");


                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), 200);


            }
        });


        StorageReference storageRef = FirebaseStorage.getInstance().getReference("Images");
        StorageReference ref = storageRef.child(System.currentTimeMillis() + "." + getExtension(imageUri));
        ref.putFile(Uri.parse(imageUri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageUri = uri.toString();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });


        phone_number.addTextChangedListener(new PhoneTextFormatter(phone_number, " ##### #####"));
        adharcartext.addTextChangedListener(new PhoneTextFormatter(adharcartext, " #### #### ####"));


        imgview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference a = FirebaseDatabase.getInstance().getReference().child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                a.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String em = snapshot.child("mselected").getValue(String.class);

                        if (em.equals("HR")) {

                            startActivity(new Intent(EmployeEditProfileActivity.this, InfoHrActivity.class));
                        } else {

                            Intent intent = new Intent(EmployeEditProfileActivity.this, UserAttendenceActivity.class);
                            startActivity(intent);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO  Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }

        };

        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {


                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();

            }

        };


        date_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog dialog = new DatePickerDialog(EmployeEditProfileActivity.this, R.style.MySpinnerDatePickerStyle, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();


            }
        });


        birth_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(EmployeEditProfileActivity.this, R.style.MySpinnerDatePickerStyle, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();

            }
        });


        database = FirebaseDatabase.getInstance().getReference().child("user").child(auth1.getCurrentUser().getUid());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                AllData allData = snapshot.getValue(AllData.class);
                String url = allData.getImgUrl();


                if (url==null) {

                    imageView.setBackgroundResource(R.drawable.ic_user);


                } else {
                    Glide.with(EmployeEditProfileActivity.this).load(url).into(imageView);
                }


                nameEdittex.setText(allData.getmName());
                nEmail.setText(allData.getnEmail());
                phone_number.setText(allData.getmPhoneNumer());
                date_join.setText(allData.getMjointate());
                birth_date.setText(allData.getMbirthdate());
                designation.setTag(allData.getMdesignation());

                blood_gp.setTag(allData.getmBloodgrp());
                addressAdd.setText(allData.getmAddress());
                adharcartext.setText(allData.getmAdharcar());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        submit_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updatevalue();


            }
        });


    }

    private void updatevalue() {

        String name = nameEdittex.getText().toString();
        String email = nEmail.getText().toString();
        String number = phone_number.getText().toString();
        String join_date = date_join.getText().toString();
        String date_birth = birth_date.getText().toString();

        desi = designation.getSelectedItem().toString();
        String bg = blood_gp.getSelectedItem().toString();

        String address_emp = addressAdd.getText().toString();
        String addhardcard = adharcartext.getText().toString();


        if (number.length() < 12 || number.length() > 13) {

            phone_number.setError("Enter valid phone number");
            phone_number.requestFocus();

        }

        if (addhardcard.length() < 15 || addhardcard.length() > 16) {

            adharcartext.setError("Enter valid adhaar  number");
            adharcartext.requestFocus();

        }

//        if (addhardcard.length() < 15 || addhardcard.length() > 16) {
//
//            addressAdd .setError("Enter valid adhaar  number");
//            addressAdd.requestFocus();
//
//        }


        if (nameEdittex.getText().toString().trim().isEmpty()) {
            nameEdittex.setError("Filled  Required");
            nameEdittex.requestFocus();

        } else if (nEmail.getText().toString().trim().isEmpty()) {

            nEmail.setError("Filled  Required");
            nEmail.requestFocus();

        } else if (!nEmail.getText().toString().trim().matches(emailPattern)) {
            nEmail.setError("Enter Valid  Email");
            nEmail.requestFocus();

        } else if (phone_number.getText().toString().isEmpty()) {

            phone_number.setError("Filled  Required");
            phone_number.requestFocus();
        } else if (nEmail.getText().toString().isEmpty()) {


            nEmail.setError("Filled  Required");
            nEmail.requestFocus();

        } else if (addressAdd.getText().toString().isEmpty()) {

            addressAdd.setError("Enter valid address  number");
            addressAdd.requestFocus();

        } else if (addressAdd.getText().toString().length() < 150) {
            addressAdd.setError("Address require 150 characters");
            addressAdd.requestFocus();
        } else if (designation.getSelectedItem().toString().trim().equals("Designation")) {

            designationErrorTextView.setError("Filled Requied");
            designationErrorTextView.requestFocus();

//            ((TextView)designation.getSelectedView()).setError("Error message");
//            ((TextView)designation.getSelectedView()).requestFocus();


        } else if (blood_gp.getSelectedItem().toString().trim().equals("Blood group")) {

            bloodgroupTextView.setError("Filled Requied");

            bloodgroupTextView.requestFocus();
        } else {


            DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("user").child(auth1.getCurrentUser().getUid());
            databaseref.child("imgurl").setValue(imageUri);


            databaseref.child("mName").setValue(name);
            databaseref.child("nEmail").setValue(email);
            databaseref.child("mPhoneNumer").setValue(number);
            databaseref.child("mjointate").setValue(join_date);
            databaseref.child("mbirthdate").setValue(date_birth);
            databaseref.child("mAddress").setValue(address_emp);
            databaseref.child("mdesignation").setValue(desi);
            databaseref.child("mBloodgrp").setValue(bg);
            databaseref.child("mAdharcar").setValue(addhardcard);


            DatabaseReference aa = FirebaseDatabase.getInstance().getReference().child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            aa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String em = snapshot.child("mselected").getValue(String.class);

                    if (em.equals("HR")) {

                        startActivity(new Intent(EmployeEditProfileActivity.this, InfoHrActivity.class));
                    } else {

                        Intent intent = new Intent(EmployeEditProfileActivity.this, UserAttendenceActivity.class);
                        startActivity(intent);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }

    private void updateLabel1() {


        String myFormat = "dd /MM /yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        birth_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel() {


        String myFormat = "dd /MM /yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        date_join.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EmployeEditProfileActivity.this, InfoHrActivity.class));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {


            imageUri = String.valueOf(data.getData());

            if (imageUri != null) {

                Bitmap bitmap = null;
                try {

                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(imageUri));
                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {

                    e.printStackTrace();
                }
            } else {

                // profile_img = null;

            }

        }

    }

    private String getExtension(String filePath) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(Uri.parse(filePath)));
    }
}