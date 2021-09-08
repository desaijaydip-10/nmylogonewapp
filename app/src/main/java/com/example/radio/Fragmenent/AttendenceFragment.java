package com.example.radio.Fragmenent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class AttendenceFragment extends Fragment {


    TextView datetxt, currenttime, endtime;
    TextView button_In, button_Out;
    Button button_no, button_yes;
    boolean ischecked = false;
    AlertDialog alertDialog;
    String currentTime2, currentTime1;
    private Date date1;
    private Date date2;
    TextClock textClock;

    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String  time_start;



    FragmentAttendenceBinding binding;
            


    String currrent_time, endtime_out;
     String format_time;
     String start_time;
     String format_time2;
     String end_time;
    private DatabaseReference databaseReference3;
     Button no_button,yes_button;

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
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid()).child("checktime");

        binding.button2.setEnabled(false);
        binding.button2.setClickable(false);




        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        alertDialog = builder.create();
        View view1 = getLayoutInflater().inflate(R.layout.custom_layout_new, null, false);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        no_button = view1.findViewById(R.id.no_button);
        yes_button = view1.findViewById(R.id.yes_button);

        //   binding.textViewTimein.setText(format_time);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid()).child("checktime");

//        if(falage==0){

//            binding.buttonIn.setClickable(true);
//        }
//        else {
//            binding.button2.setClickable(false);
//        }

        binding.buttonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.button2.setEnabled(false);
                binding.button2.setClickable(false);

                no_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                yes_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        binding.button2.setEnabled(true);
                        binding.button2.setClickable(true);

                        binding.buttonIn.setEnabled(false);
                        binding.buttonIn.setClickable(false);

                        format_time = new android.icu.text.SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());

                        binding.textViewTimein.setText(format_time);

                        start_time = binding.textViewTimein.getText().toString();

                        TimeSetModel timeSetModel = new TimeSetModel(start_time, "", "", "");

                        databaseReference.child(auth.getCurrentUser().getUid()).setValue(timeSetModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(getContext(), "succesfully add", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                        alertDialog.dismiss();
                        // Log.e("timevalue", binding.textViewTimein.getText().toString());
                    }
                });

                alertDialog.show();

                // binding.textViewTimein.setText(format_time);


            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                no_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                yes_button.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View v) {


                        format_time2 = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
                        binding.textView2.setText(format_time2);

                        binding.buttonIn.setEnabled(true);
                        binding.buttonIn.setClickable(true);

                        binding.button2.setEnabled(false);
                        binding.button2.setClickable(false);


                        //   String st_time = currenttime.getText().toString();

                        end_time = binding.textView2.getText().toString();

                        DatabaseReference databaseReference6 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid()).child("checktime");
                        databaseReference6.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                time_start= snapshot.child("starttime").getValue(String.class);


                                Log.e("start_time", time_start);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });




                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

                        LocalTime start = LocalTime.parse(time_start, timeFormatter);
                        LocalTime end = LocalTime.parse(end_time, timeFormatter);

                        Duration diff = Duration.between(start, end);

                        long hours = diff.toHours();
                        long minutes = diff.minusHours(hours).toMinutes();
                        String totalTimeString = String.format("%02d:%02d", hours, minutes);


                        LocalTime fix_time = LocalTime.parse("06:15 PM", timeFormatter);
                        Duration diff2 = Duration.between(fix_time, end);

                        long hours2 = diff2.toHours();
                        long minutes2 = diff2.minusHours(hours2).toMinutes();
                        String totalTimeString2 = String.format("%02d:%02d", hours2, minutes2);
                        //   Log.e("diffrent", totalTimeString2);



                        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid()).child("checktime");
                        databaseReference3.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                snapshot.getRef().child("endtime").setValue(end_time);
                                snapshot.getRef().child("diffrent").setValue(totalTimeString);
                                snapshot.getRef().child("diffrent2").setValue(totalTimeString2);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });




//                        databaseReference.child("checktime").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });




//        datetxt = view.findViewById(R.id.textView9);
//        currenttime = view.findViewById(R.id.textView15);
//        textClock = view.findViewById(R.id.textView13);
//
//        endtime = view.findViewById(R.id.textView_end);
//
//        button_In = view.findViewById(R.id.textView20);
//        button_Out = view.findViewById(R.id.textView21);
//
//
//        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//
//        datetxt.setText(currentDate);
//
//
//        textClock.setFormat12Hour(null);
//        textClock.setFormat24Hour("hh:mm:ss a");
//
//
//
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        alertDialog = builder.create();
//        View view1 = getLayoutInflater().inflate(R.layout.custom_layout_new, null, false);
//
//        button_no = view1.findViewById(R.id.no_button);
//        button_yes = view1.findViewById(R.id.yes_button);
//
//        alertDialog.setView(view1);
//
//        button_In.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                button_no.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        alertDialog.dismiss();
//                    }
//                });
//                button_yes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        format_time = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
//
//                        currenttime.setText(format_time);
//
//                        start_time = currenttime.getText().toString();
//
//                        String current_date= datetxt.getText().toString();
//
//                        TimeSetModel timeSetModel = new TimeSetModel(start_time, "", "",current_date );
//
//                        databaseReference.child(auth.getCurrentUser().getUid()).setValue(timeSetModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//
//                                if (task.isSuccessful()) {
//
//
//                                  //  Toast.makeText(getContext(), "succesfully add", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//
//
//
//                        alertDialog.dismiss();
//                    }
//                });
//
//                alertDialog.show();
//            }
//        });
//
//
//        button_Out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                button_no.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        alertDialog.dismiss();
//                    }
//                });
//                button_yes.setOnClickListener(new View.OnClickListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.O)
//                    @Override
//                    public void onClick(View v) {
//
//                        format_time2 = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
//                        endtime.setText(format_time2);
//                        end_time = endtime.getText().toString();
//
//                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
//
//                        LocalTime start = LocalTime.parse(start_time, timeFormatter);
//                        LocalTime end = LocalTime.parse(end_time, timeFormatter);
//
//                        Duration diff = Duration.between(start, end);
//
//                        long hours = diff.toHours();
//                        long minutes = diff.minusHours(hours).toMinutes();
//                        String totalTimeString = String.format("%02d:%02d", hours, minutes);
//
//                        LocalTime fix_time = LocalTime.parse("06:15 PM", timeFormatter);
//                        Duration diff2 = Duration.between(fix_time, end);
//
//                        long hours2 = diff2.toHours();
//                        long minutes2 = diff2.minusHours(hours2).toMinutes();
//                        String totalTimeString2 = String.format("%02d:%02d", hours2, minutes2);
//
//
//                        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid()).child("checktime");
//                        databaseReference3.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                snapshot.getRef().child("endtime").setValue(end_time);
//                                snapshot.getRef().child("diffrent").setValue(totalTimeString);
//                                snapshot.getRef().child("diffrent2").setValue(totalTimeString2);
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//
//
//
//
//                        alertDialog.dismiss();
//                    }
//                });
//
//                alertDialog.show();
//            }
//        });

        return binding.getRoot();
    }
}