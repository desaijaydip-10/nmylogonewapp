package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radio.Adapter.SpinnerAdapterA;
import com.example.radio.Model.RegisterModel;
import com.example.radio.R;
import com.example.radio.UserStatusIntetFace;

import com.example.radio.databinding.ActivitySignup2Binding;
import com.example.radio.utils.PhoneTextFormatter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity2 extends AppCompatActivity implements MultiplePermissionsListener {


    ActivitySignup2Binding activitySignup2Binding;
    String emailPattern, regexStr;
    static String mselector;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    String imageUri = "";

    Spinner spinner, spinner2;
    boolean verifyCheck = false;

    AlertDialog dialog5;
    String uid = null;

    String ischecked = "0";
    CircleImageView profile_img;

    Calendar myCalendar;
    EditText userEdittext, userAdharCard;
    RadioGroup rg;
    RadioButton radiohr, radioemp;
    FirebaseStorage storage;
    StorageReference storageRef;
    TextView textView, textview2;

    String chechedstatus = "0";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    private String imageurl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        activitySignup2Binding = ActivitySignup2Binding.inflate(getLayoutInflater());
        setContentView(activitySignup2Binding.getRoot());

        profile_img = findViewById(R.id.imageview_account_profile);

        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        regexStr = "^[0-9]$";
        rg = findViewById(R.id.rbg);

        spinner = findViewById(R.id.editTextTextPersonName7);
        spinner2 = findViewById(R.id.editTextTextPersonbloodgroup);


        radiohr = findViewById(R.id.radiobuttomHr);
        radioemp = findViewById(R.id.radiobuttonemp);
        textView = findViewById(R.id.errortxt);
        textview2 = findViewById(R.id.errortxt2);


        userEdittext = findViewById(R.id.editTextTextPersonName6);
        userEdittext.addTextChangedListener(new PhoneTextFormatter(userEdittext, " ##### #####"));

        userAdharCard = findViewById(R.id.editTextTextPersonAharCard);
        userAdharCard.addTextChangedListener(new PhoneTextFormatter(userAdharCard, " #### #### ####"));


        String[] courses = new String[]{"Designation", "Junior", "Senior", "Trainee"};
        String blodGroup[] = new String[]{"Blood group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};


        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        mAuth = FirebaseAuth.getInstance();

        myCalendar = Calendar.getInstance();


        activitySignup2Binding.editTextTextPersonName7.setAdapter(new SpinnerAdapterA(this, R.layout.custom_layout, courses));
        activitySignup2Binding.editTextTextPersonbloodgroup.setAdapter(new SpinnerAdapterA(this, R.layout.custom_layout, blodGroup));


        RadioButton value = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
        mselector = value.getText().toString();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.radiobuttonemp:
                        mselector = radioemp.getText().toString();
                        break;

                    case R.id.radiobuttomHr:
                        mselector = radiohr.getText().toString();
                }


            }
        });


        activitySignup2Binding.logintextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignupActivity2.this, LoginNewActivity.class));
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


                // TODO Auto-generated method stu
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();


            }

        };


        activitySignup2Binding.spinner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(SignupActivity2.this, R.style.MySpinnerDatePickerStyle, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();

            }
        });

        activitySignup2Binding.editTextTextPersonName7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        activitySignup2Binding.editTextTextPersonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity2.this, R.style.MySpinnerDatePickerStyle, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });


        activitySignup2Binding.floatingactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withContext(SignupActivity2.this)
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
                //gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), 200);


                // startActivityForResult(gallery,200);
            }
        });


        activitySignup2Binding.signupTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sotreFirebaseData();


            }
        });

    }


    private void sotreFirebaseData() {


        String mobilenumber = activitySignup2Binding.editTextTextPersonName6.getText().toString();
        String addharcard = activitySignup2Binding.editTextTextPersonAharCard.getText().toString();
        String username = activitySignup2Binding.editTextTextPersonName.getText().toString();

        String password1 = activitySignup2Binding.textINputLayout.getEditText().getText().toString();
        String useremail = activitySignup2Binding.userEmail.getText().toString();
        String userdesignation = activitySignup2Binding.editTextTextPersonName7.getSelectedItem().toString();
        String userdateofjoin = activitySignup2Binding.spinner2.getText().toString();

        String userdob = activitySignup2Binding.editTextTextPersonDate.getText().toString();
        String useraddress = activitySignup2Binding.editTextTextPersonAddress.getText().toString();
        String bloodgrp = activitySignup2Binding.editTextTextPersonbloodgroup.getSelectedItem().toString();

        String password = activitySignup2Binding.textINputLayout.getEditText().getText().toString().trim();
        String confpassword = activitySignup2Binding.conforomTextinputLayout.getEditText().getText().toString().trim();
        String company_email = activitySignup2Binding.editTextTextCompanyEmail.getText().toString().trim();


        int selectedItemOfMySpinner = spinner.getSelectedItemPosition();
        String actualPositionOfMySpinner = (String) spinner.getItemAtPosition(selectedItemOfMySpinner);


        if (profile_img == null) {

            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
        } else if (activitySignup2Binding.editTextTextPersonName.getText().toString().isEmpty()) {

            activitySignup2Binding.editTextTextPersonName.setError("Filled  Required");


        } else if (activitySignup2Binding.userEmail.getText().toString().isEmpty()) {

            activitySignup2Binding.userEmail.setError("Filled  Required");
            activitySignup2Binding.userEmail.requestFocus();

        } else if (!activitySignup2Binding.userEmail.getText().toString().trim().matches(emailPattern)) {

            activitySignup2Binding.userEmail.setError("Enter Valid  Email");
            activitySignup2Binding.userEmail.requestFocus();

        } else if (activitySignup2Binding.textINputLayout.getEditText().getText().toString().isEmpty()) {

            activitySignup2Binding.EditTextName1.setError("Filled required");
            activitySignup2Binding.EditTextName1.requestFocus();

        } else if (activitySignup2Binding.conforomTextinputLayout.getEditText().getText().toString().isEmpty()) {

            activitySignup2Binding.conforomTextinputLayout.setPasswordVisibilityToggleEnabled(false);
            activitySignup2Binding.confPasswordEditTextView.setError("Filled required");
            activitySignup2Binding.confPasswordEditTextView.requestFocus();
        } else if (!PASSWORD_PATTERN.matcher(activitySignup2Binding.textINputLayout.getEditText().getText().toString()).matches()) {

            activitySignup2Binding.EditTextName1.setError("at least 1 special character");
            activitySignup2Binding.EditTextName1.requestFocus();
        } else if (!password.equals(confpassword)) {

            activitySignup2Binding.conforomTextinputLayout.setPasswordVisibilityToggleEnabled(false);
            activitySignup2Binding.confPasswordEditTextView.setError("Password Not Match");
            activitySignup2Binding.confPasswordEditTextView.requestFocus();
        } else if (mobilenumber.isEmpty()) {

            activitySignup2Binding.editTextTextPersonName6.setError("Filled required");
            activitySignup2Binding.editTextTextPersonName6.requestFocus();
        } else if (mobilenumber.length() < 12 || mobilenumber.length() > 13 || mobilenumber.matches(regexStr)) {

            activitySignup2Binding.editTextTextPersonName6.setError("Enter valid phone number");
            activitySignup2Binding.editTextTextPersonName6.requestFocus();


        } else if (spinner.getSelectedItem().toString().trim().equals("Designation")) {

            //  Toast.makeText(this, "designation", Toast.LENGTH_SHORT).show();

            // textView.setVisibility(View.VISIBLE);
            textView.setError("Filled  Requied");
            textView.requestFocus();


//            View selectedView = spinner.getSelectedView();
//            if (selectedView != null && selectedView instanceof TextView) {
//                TextView selectedTextView = (TextView) selectedView;
//
//                // String errorString = selectedTextView.getResources().getString(mErrorStringResource);
//                selectedTextView.setError("errorString");
//
//
//            }

        } else if (activitySignup2Binding.spinner2.getText().toString().isEmpty()) {
            activitySignup2Binding.spinner2.setError("Filled required");
            activitySignup2Binding.spinner2.requestFocus();

        } else if (activitySignup2Binding.editTextTextPersonDate.getText().toString().isEmpty()) {

            activitySignup2Binding.editTextTextPersonDate.setError("Filled required");
            activitySignup2Binding.editTextTextPersonDate.requestFocus();

        } else if (activitySignup2Binding.editTextTextPersonAddress.getText().toString().isEmpty()) {

            activitySignup2Binding.editTextTextPersonAddress.setError("Filled required");
            activitySignup2Binding.editTextTextPersonAddress.requestFocus();

        } else if (addharcard.isEmpty()) {
            activitySignup2Binding.editTextTextPersonAharCard.setError("Filled required");
            activitySignup2Binding.editTextTextPersonAharCard.requestFocus();
        } else if (addharcard.length() < 15 || mobilenumber.length() > 16) {

            activitySignup2Binding.editTextTextPersonAharCard.setError("Enter valid  Adharcard number");
            activitySignup2Binding.editTextTextPersonAharCard.requestFocus();

        } else if (spinner2.getSelectedItem().toString().trim().equals("Blood group")) {


            textview2.setVisibility(View.VISIBLE);
            textview2.setError("Filled  Requied");
            textview2.requestFocus();


//
//            View selectedView = spinner2.getSelectedView();
//
//            TextView selectedTextView = (TextView) selectedView;


            //   Toast.makeText(this, "please blood Group select", Toast.LENGTH_SHORT).show();
            // selectedTextView.setError("Error blood group");
        } else if (activitySignup2Binding.editTextTextCompanyEmail.getText().toString().trim().isEmpty()) {

            activitySignup2Binding.editTextTextCompanyEmail.setError("Filled required");
            activitySignup2Binding.editTextTextCompanyEmail.requestFocus();
        } else if (activitySignup2Binding.editTextTextCompanyEmail.getText().toString().isEmpty()) {


            activitySignup2Binding.editTextTextCompanyEmail.setError("Enter Company Email");
            activitySignup2Binding.editTextTextCompanyEmail.requestFocus();


        } else if (!activitySignup2Binding.editTextTextCompanyEmail.getText().toString().trim().matches(emailPattern)) {

            activitySignup2Binding.editTextTextCompanyEmail.setError("Enter Company valid  Email");
            activitySignup2Binding.editTextTextCompanyEmail.requestFocus();
        } else {


//                        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
//                                .setLink(Uri.parse("https://appdemo12.page.link/verify"))
//                                .setDomainUriPrefix("https://appdemo12.page.link")
//                                .setAndroidParameters(
//                                        new DynamicLink.AndroidParameters.Builder("com.example.demo")
//                                                .setMinimumVersion(125)
//                                                .build())
//                                .setIosParameters(
//                                        new DynamicLink.IosParameters.Builder("com.example.ios")
//                                                .setAppStoreId("123456789")
//                                                .setMinimumVersion("1.0.1")
//                                                .build())
//                                .setGoogleAnalyticsParameters(
//                                        new DynamicLink.GoogleAnalyticsParameters.Builder()
//                                                .setSource("orkut")
//                                                .setMedium("social")
//                                                .setCampaign("example-promo")
//                                                .build())
//                                .setItunesConnectAnalyticsParameters(
//                                        new DynamicLink.ItunesConnectAnalyticsParameters.Builder()
//                                                .setProviderToken("123456")
//                                                .setCampaignToken("example-promo")
//                                                .build())
//                                .setSocialMetaTagParameters(
//                                        new DynamicLink.SocialMetaTagParameters.Builder()
//                                                .setTitle("Example of a Dynamic Link")
//                                                .setDescription("This link works whether the app is installed or not!")
//                                                .build())
//                                .buildDynamicLink();  // Or buildShortDynamicLink()
//

            mAuth.createUserWithEmailAndPassword(company_email, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if (task.isSuccessful()) {

                        AlertDialog.Builder builder3 = new AlertDialog.Builder(SignupActivity2.this);

                        dialog5 = builder3.create();
                        View view4 = getLayoutInflater().inflate(R.layout.spinner_dialoge, null, false);
                        ProgressBar progressBar23 = view4.findViewById(R.id.progressbar43);
                        progressBar23.setVisibility(View.VISIBLE);

                        dialog5.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        dialog5.setView(view4);


                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


                        dialog5.show();
                        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


                        StorageReference storageRef = FirebaseStorage.getInstance().getReference("Images");


                        StorageReference ref = storageRef.child(System.currentTimeMillis() + "." + getExtension(imageUri));
                        ref.putFile(Uri.parse(imageUri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        imageurl2 = uri.toString();

                                        //  reference2.setValue(imageurl);//store link in database
//                        Log.i("Image url", uri.toString());

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Handle any errors
                                    }
                                });
                            }
                        });


                        RegisterModel model = new RegisterModel(uid, username, useremail, password, mobilenumber, userdesignation, userdateofjoin,
                                userdob, useraddress, addharcard, bloodgrp, company_email, imageurl2, chechedstatus, ischecked, verifyCheck);
                        model.setMselected(mselector);


                        //  to create   databaserefrence  uploade  data
                        databaseReference.child(uid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    // progressBar23.setVisibility(View.GONE);
//                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//
//                                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                    dialog5.dismiss();

                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {


                                                // FirebaseStorage storage = FirebaseStorage.getInstance();
                                                // StorageReference storageRef = storage.getReference().child("userchild");
//                                                storageRef.child("Img").putFile(Uri.parse(imageUri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                                    @Override
//                                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                                                        Toast.makeText(SignupActivity2.this, "successs", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                });


                                                AlertDialog.Builder builder2 = new AlertDialog.Builder(SignupActivity2.this);
                                                AlertDialog dialog1 = builder2.create();
                                                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                                                View view2 = getLayoutInflater().inflate(R.layout.alertdialog, null);
                                                dialog1.setView(view2);


//                                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
////


                                                TextView btn = view2.findViewById(R.id.Button12);


                                                btn.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {

                                                       // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


                                                        startActivity(new Intent(SignupActivity2.this, LoginNewActivity.class));

                                                        dialog1.dismiss();
                                                    }
                                                });


                                                dialog1.show();
                                            } else {

                                                Toast.makeText(SignupActivity2.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {


                                    Toast.makeText(SignupActivity2.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {

                        Toast.makeText(SignupActivity2.this, "user already sigg up   ", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }

    private String getString(String s) {

        return "Fileed require";
    }


    private void updateLabel1() {

        String myFormat = "dd /MM /yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        activitySignup2Binding.editTextTextPersonDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel() {
        String myFormat = "dd /MM /yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        activitySignup2Binding.spinner2.setText(sdf.format(myCalendar.getTime()));
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
                    profile_img.setImageBitmap(bitmap);

                } catch (IOException e) {

                    e.printStackTrace();
                }
            } else {

                profile_img = null;

            }

        }

    }

    private String getExtension(String filePath) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(Uri.parse(filePath)));
    }


    @Override
    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

    }


    private void setSpinnerError(Spinner spinner, String error) {
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message 
            spinner.performClick(); // to open the spinner list if error is found.

        }
    }


}