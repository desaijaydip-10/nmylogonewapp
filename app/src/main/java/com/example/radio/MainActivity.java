package com.example.radio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements MultiplePermissionsListener {


    RadioButton radioButton;
    ImageView imgview;
    Uri imageUri;


    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup = findViewById(R.id.grp);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);

        RadioButton value = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                switch (checkedId) {

                    case R.id.radioButton:
                        Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radioButton2:
                        Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


//        RadioButton value = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
//
//        String mselector = value.getText().toString();
//
//        Toast.makeText(this, "" + mselector, Toast.LENGTH_SHORT).show();
//
//        imgview = findViewById(R.id.img);
//        button1 = findViewById(R.id.button);
//
//
//        imgview.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//
//                   Dexter.withContext(MainActivity.this)
//                           .withPermissions(
//                                   Manifest.permission.CAMERA,
//                                   Manifest.permission.READ_CONTACTS,
//                                   Manifest.permission.RECORD_AUDIO
//                           ).withListener(new MultiplePermissionsListener() {
//                       @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
//                       @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
//                   }).check();
//
//
//                   Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                   gallery.setType("image/*");
//
//
//                   startActivityForResult(gallery, 200);
//               }
//           });
//
//
//
//
//           button1.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//
//
//                   FirebaseStorage storage = FirebaseStorage.getInstance();
//                   StorageReference storageRef = storage.getReference().child("imgges");
//
//
//                   storageRef.child(UUID.randomUUID().toString()).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                       @Override
//                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                           Toast.makeText(MainActivity.this, "img uploade", Toast.LENGTH_SHORT).show();
//                       }
//                   });
//
//               }
//           });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

    }
}