package com.example.radio.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomConfig {

     public  static String   email;
     public  static String   password;

    public CustomConfig(String mcompanyEmail, String userpassword) {

        email = mcompanyEmail;
        password= userpassword;


    }
}
