package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.radio.Adapter.SpinnerAdapterA;

import com.example.radio.Model.CustomModel;
import com.example.radio.Model.LeaveModel;
import com.example.radio.Model.TemplateParams;
import com.example.radio.R;
;
import com.example.radio.databinding.ActivityApplyLeaveBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApplyLeaveActivity extends AppCompatActivity {


    ActivityApplyLeaveBinding applyLeaveBinding;
    String leaveType[];
    String timeSelector;
    FirebaseAuth auth;
    Calendar mCalendar;
    DatabaseReference reference, ref;
    String newformatDate, newformatEndDate;
    String em;
    int s = 0;
    long f, l;
    private RequestQueue requestQueue;
    String url = "https://api.emailjs.com/api/v1.0/email/send";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyLeaveBinding = ActivityApplyLeaveBinding.inflate(getLayoutInflater());
        setContentView(applyLeaveBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        String currentId = auth.getCurrentUser().getUid();

        leaveType = new String[]{"Leave Type", "Privilege Leave", "Casual Leave ", "Sick Leave"};

        applyLeaveBinding.leaveTypespinner.setAdapter(new SpinnerAdapterA(this, R.layout.custom_layout, leaveType));
        mCalendar = Calendar.getInstance();

        reference = FirebaseDatabase.getInstance().getReference().child("timeselector").child(currentId);


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();
            }
        };


        applyLeaveBinding.firstlinerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ApplyLeaveActivity.this, R.style.MySpinnerDatePickerStyle, date1, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        applyLeaveBinding.secondLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(ApplyLeaveActivity.this, R.style.MySpinnerDatePickerStyle, date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();
            }
        });


        RadioButton value = (RadioButton) findViewById(applyLeaveBinding.linerlayoutfullyday.getCheckedRadioButtonId());
        timeSelector = value.getText().toString();

        applyLeaveBinding.linerlayoutfullyday.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.fulltimeRadioButton:

                        timeSelector = applyLeaveBinding.fulltimeRadioButton.getText().toString();

                        break;
                    case R.id.halftimeRadioButton:
                        timeSelector = applyLeaveBinding.halftimeRadioButton.getText().toString();
                }

            }
        });


        applyLeaveBinding.applyLeavetextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (applyLeaveBinding.leaveTypespinner.getSelectedItem().toString().trim().equals("Leave Type")) {

                } else if (applyLeaveBinding.editReasoneEditTextView.getText().toString().isEmpty()) {

                    applyLeaveBinding.editReasoneEditTextView.setError("Please entet Valide  Resone");
                    applyLeaveBinding.editReasoneEditTextView.requestFocus();

                } else if (applyLeaveBinding.startdateTextView.getText().toString().isEmpty()) {

                    applyLeaveBinding.startdateTextView.setError("Please entet Valide  Resone");
                    applyLeaveBinding.startdateTextView.requestFocus();

                } else if (applyLeaveBinding.endDateTextView.getText().toString().isEmpty()) {

                    applyLeaveBinding.endDateTextView.setError("Please entet Valide  Resone");
                    applyLeaveBinding.endDateTextView.requestFocus();


                } else {


                    String startDate = applyLeaveBinding.startdateTextView.getText().toString();
                    String endDate = applyLeaveBinding.endDateTextView.getText().toString();
                    String leavetpe = applyLeaveBinding.leaveTypespinner.getSelectedItem().toString();
                    String reasone = applyLeaveBinding.editReasoneEditTextView.getText().toString();

                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


                    Date d1 = null;
                    Date d2 = null;

                    try {
                        d1 = format.parse(newformatDate);
                        d2 = format.parse(newformatEndDate);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long diff = d2.getTime() - d1.getTime();


                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;


                    int diffDays = (int) diff / (24 * 60 * 60 * 1000);

                    int countdays = (diffDays + 1);

                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;
                    long daysInMilli = hoursInMilli * 24;

                    long elapsedDays = diff / daysInMilli;
                    int daydiff = (int) elapsedDays;


                    DatabaseReference databaseReference12 = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());

                    databaseReference12.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            em = snapshot.child("mselected").getValue(String.class);

                            LeaveModel leaveModel = new LeaveModel(auth.getCurrentUser().getUid(), leavetpe, startDate, endDate, timeSelector, reasone, "0", daydiff, em, "1" ,"1" );

                            reference.child(startDate).setValue(leaveModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(ApplyLeaveActivity.this, "successs", Toast.LENGTH_SHORT).show();



//                                          String email = "jd.idea2cod@gmail.com";
//                                          String pass = "idea2code";
//
//                                        Properties props = new Properties();
//                                        props.put("mail.smtp.auth", "true");
//                                        props.put("mail.smtp.starttls.enable", "true");
//                                        props.put("mail.smtp.host", "smtp.gmail.com");
//                                        props.put("mail.smtp.port", "586");
//
//                                        Session session = Session.getInstance(props,
//                                                new javax.mail.Authenticator() {
//                                                    protected PasswordAuthentication getPasswordAuthentication() {
//                                                        return new PasswordAuthentication(email, pass);
//                                                    }
//                                                });
//
//                                        try {
//
//                                            Message message = new MimeMessage(session);
//                                            message.setFrom(new InternetAddress(email));
//                                            message.setRecipients(Message.RecipientType.TO,
//                                                    InternetAddress.parse("jd.idea2cod@gmail.com"));
//                                            message.setSubject("Testing Subject");
//                                            message.setText("Dear Mail Crawler,"
//                                                    + "\n\n No spam to my email, please!");
//
//                                            Transport.send(message);
//
//                                            System.out.println("Done");
//
//                                        } catch (MessagingException e) {
//                                            throw new RuntimeException(e);
//                                        }




//                                        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                                        Notification notify=new Notification.Builder
//                                                (getApplicationContext()).setContentTitle("dddsdsjaydeep ").setContentText("accept").
//                                                setContentTitle("Kghkhjhjghj").setSmallIcon(R.drawable.ic_icon).build();
//
//                                        notify.flags = Notification.FLAG_AUTO_CANCEL;
//                                        notif.notify(0, notify);




//                                        Retrofit retrofit = new Retrofit.Builder()
//                                                .baseUrl("https://reqres.in/api/")
//                                                .addConverterFactory(GsonConverterFactory.create())
//                                                .build();
//
//
//                                        TemplateParams templateParams = new TemplateParams("asdsdasda", "jd.idea2code@gmail.com", "asdf", "jj.idea2code@gmail.com", "Hello...", "dgdfgfg");
//                                        CustomModel customModel = new CustomModel("service_xiiiaid", "template_95odc8d", "user_RQafz4zI1Hwtgdh5AOpbq", templateParams);
//
//                                        SendMailInterfce mailInterfce = retrofit.create(SendMailInterfce.class);
//
//                                        Call<CustomModel> call = mailInterfce.setValue(customModel);
//
//                                        call.enqueue(new Callback<CustomModel>() {
//                                            @Override
//                                            public void onResponse(Call<CustomModel> call, Response<CustomModel> response) {
//
//
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<CustomModel> call, Throwable t) {
//
//                                            }
//                                        });


                                    }
                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

            }
        });


    }

    private void senEmail() {

    }

    private void updateLabel1() {

//        SimpleDateFormat df = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault());
//
        String myFormat = "dd MMM, yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        applyLeaveBinding.startdateTextView.setText(sdf.format(mCalendar.getTime()));


        long t = (long) (System.currentTimeMillis());
        Log.e("sdas", String.valueOf(t));

        f = t;
        Timestamp tsTemp = new Timestamp(t);

        java.text.SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");


        String dateString = formatter.format(new Date(t));

        String newFforma = "dd/MM/yyy";
        SimpleDateFormat sdfd = new SimpleDateFormat(newFforma, Locale.US);
        newformatDate = sdfd.format(mCalendar.getTime());


    }

    private void updateLabel() {


        String myFormat = "dd MMM, yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        applyLeaveBinding.endDateTextView.setText(sdf.format(mCalendar.getTime()));

        long end = (long) (System.currentTimeMillis());
        l = end;

        Log.e("sdas", String.valueOf(end));
        Timestamp tsTemp = new Timestamp(end);

        java.text.SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");


        String dateString = formatter.format(new Date(end));

        String newFforma = "dd/MM/yyy";
        SimpleDateFormat sdfd = new SimpleDateFormat(newFforma, Locale.US);
        newformatEndDate = sdfd.format(mCalendar.getTime());

    }

    public void backside(View view) {

        startActivity(new Intent(ApplyLeaveActivity.this, LeavesActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}