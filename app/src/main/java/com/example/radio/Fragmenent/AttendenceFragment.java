package com.example.radio.Fragmenent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radio.MainActivity;
import com.example.radio.Model.TimeDateModel;
import com.example.radio.R;
import com.example.radio.databinding.FragmentAttendenceBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AttendenceFragment extends Fragment {


    FragmentAttendenceBinding binding;


    private String format_time;

    FirebaseAuth auth;
    boolean ischeckedvalued = false;


    Button no_button, yes_button;
    AlertDialog alertDialog;
    String checkvalue = "0";

    String start_time, end_time;

    DatabaseReference databaseReference3;
//    boolean punchIn = false, punchOut = false;

    String current_date;
    String totaltime;

    Date d;
    DatabaseReference ref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAttendenceBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();
        Date c = Calendar.getInstance().getTime();
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd MMM, yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        String dates= "23 Sep, 2021";


        binding.textView9.setText(formattedDate);
        current_date = binding.textView9.getText().toString();
        binding.textView13.setFormat12Hour("hh:mm:ss a");


        DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference().child("checktime");

        databaseReference4.child(auth.getCurrentUser().getUid()).child(current_date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String startime = snapshot.child("starttime").getValue(String.class);
                String endtimes = snapshot.child("endtime").getValue(String.class);




                if (startime != null) {

                    binding.textView21.setBackgroundResource(R.drawable.bg_button);
                    binding.punchIntextView.setBackgroundResource(R.drawable.bg_button_not_selector);

                    binding.punchIntextView.setEnabled(false);
                    binding.textView21.setEnabled(true);

                } else {



                    binding.textView21.setEnabled(false);
                    binding.textView21.setBackgroundResource(R.drawable.bg_button_not_selector);
                    binding.punchIntextView.setBackgroundResource(R.drawable.bg_button);
                }

                if (endtimes != null) {

                    if(current_date.equals(snapshot.child("date").getValue(String.class))){

                        binding.textView21.setBackgroundResource(R.drawable.bg_button_not_selector);
                        binding.punchIntextView.setBackgroundResource(R.drawable.bg_button);

                        binding.punchIntextView.setVisibility(View.GONE);
                        binding.textView21.setVisibility(View.GONE);


                        binding.textView26.setVisibility(View.VISIBLE);
                        binding.textView26.setText("punch out Today");
                    }

                    else {

                        binding.textView26.setVisibility(View.GONE);
                        binding.textView21.setBackgroundResource(R.drawable.bg_button_not_selector);
                        binding.punchIntextView.setBackgroundResource(R.drawable.bg_button);
                    }

                } else {


                }

//                if (snapshot.child("starttime").exists()){
//
//                    binding.textView21.setBackgroundResource(R.drawable.bg_button);
//                    binding.punchIntextView.setBackgroundResource(R.drawable.bg_button_not_selector);
//
//                    binding.punchIntextView.setEnabled(false);
//                    binding.textView21.setEnabled(true);
//
//                }else
//                    {
//
//                    if(current_date.equals(snapshot.child("date").getValue(String.class))){
//                        binding.textView21.setBackgroundResource(R.drawable.bg_button_not_selector);
//                        binding.punchIntextView.setBackgroundResource(R.drawable.bg_button);
//
//                        binding.punchIntextView.setEnabled(true);
//                        binding.textView21.setEnabled(false);
//                    }
//                    else {
//
//
//                        binding.textView21.setBackgroundResource(R.drawable.bg_button_not_selector);
//                        binding.punchIntextView.setBackgroundResource(R.drawable.bg_button);
//
//                    }
//
//
//
//                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference databaseReference6 = FirebaseDatabase.getInstance().getReference().child("checktime");
        databaseReference6.child(auth.getCurrentUser().getUid()).child(current_date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                start_time = snapshot.child("starttime").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.punchIntextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogePunchIn();
            }
        });


        binding.textView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPunchOut();
            }
        });


        return binding.getRoot();
    }


    private void openPunchOut() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        alertDialog = builder.create();
        View view1 = getLayoutInflater().inflate(R.layout.alert_dialoge_layout, null, false);


        no_button = view1.findViewById(R.id.button3);
        yes_button = view1.findViewById(R.id.button4);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setView(view1);
        alertDialog.show();


        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        yes_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


                long time = (long) (System.currentTimeMillis());
                Timestamp tsTemp = new Timestamp(time);


                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("hh:mm a");

                String dateString2 = formatter.format(new Date(time));
                binding.textViewend.setText(dateString2);
                String end_time = String.valueOf(time);


                databaseReference3 = FirebaseDatabase.getInstance().getReference().child("checktime").child(auth.getCurrentUser().getUid()).child(current_date);
                databaseReference3.child("endtime").setValue(end_time);


                long totalSecs = (time - Long.parseLong(start_time));
                long seconds = totalSecs / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;

                totaltime = hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;

                String e = binding.textViewend.getText().toString();


                java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("hh:mm a");
                String endtimes = format.format(new Date(time));


                String dateStart = "06:15 pm";
                String dateStop = endtimes;


                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = format.parse(dateStart);
                    d2 = format.parse(dateStop);

                } catch (ParseException ee) {
                    ee.printStackTrace();
                }


                long diff = d2.getTime() - d1.getTime();




                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000);


                String totaldiffrenttimes = diffHours + ":" + diffMinutes;


                Log.e("timesdiff", totaldiffrenttimes);

                databaseReference3.child("diffrent").setValue(totaldiffrenttimes);
                ;
                binding.textView27.setVisibility(View.VISIBLE);
                binding.textView26.setVisibility(View.VISIBLE);
                binding.textView26.setText(totaltime);


                binding.textView21.setVisibility(View.GONE);
                binding.punchIntextView.setVisibility(View.GONE);

                alertDialog.dismiss();

            }
        });

    }

    private void openDialogePunchIn() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        alertDialog = builder.create();
        View view1 = getLayoutInflater().inflate(R.layout.alert_dialoge_layout, null, false);


        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        no_button = view1.findViewById(R.id.button3);
        yes_button = view1.findViewById(R.id.button4);

        alertDialog.setView(view1);
        alertDialog.show();


        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        yes_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                long t = (long) (System.currentTimeMillis());


                Timestamp tsTemp = new Timestamp(t);



                java.text.SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");


                String dateString = formatter.format(new Date(t));

                binding.textView15.setText(dateString);


                TimeSetModel timeSetModel = new TimeSetModel(String.valueOf(t), null, "", binding.textView9.getText().toString());

                DatabaseReference a = FirebaseDatabase.getInstance().getReference().child("checktime");

                a.child(auth.getCurrentUser().getUid()).child(current_date).setValue(timeSetModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });


                alertDialog.dismiss();


            }
        });


    }
}