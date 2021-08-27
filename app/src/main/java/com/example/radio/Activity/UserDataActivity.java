package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.radio.Model.StatusCheckModel;
import com.example.radio.R;
import com.example.radio.databinding.ActivityUserDataBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDataActivity extends AppCompatActivity {



    ActivityUserDataBinding dataBinding;
    DatabaseReference databaseReference1;
    private boolean isCheck;
    private String value;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        textView = findViewById(R.id.changeTextView);

        setContentView(R.layout.activity_user_data);
        textView = findViewById(R.id.changeTextView);

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("statuscheck");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    StatusCheckModel statusCheckModel= dataSnapshot.getValue(StatusCheckModel.class);
                    isCheck= statusCheckModel.isChecked();
                    value= statusCheckModel.getValue();

                    if(isCheck==true){
                        textView.setText(value);
                    }
                    else {
                        textView.setText("Waitng for aprrove reques");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}