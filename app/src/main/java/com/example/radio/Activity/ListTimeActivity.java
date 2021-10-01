package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.radio.Adapter.ShowAllTimeAdapter;
import com.example.radio.Model.EmpModel;
import com.example.radio.Model.GetAllTimeDataModel;
import com.example.radio.R;
import com.example.radio.databinding.ActivityListTimeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class ListTimeActivity extends AppCompatActivity {

    ActivityListTimeBinding showDate2Binding;
    FirebaseAuth mAuth;
    EmpModel empModel;
    ArrayList<EmpModel> arrayList;
    AlertDialog alertDialog;
    ArrayList<EmpModel> temp;
    Calendar myCalendar;


    DatabaseReference databaseRef;
    private Date arg0Date;
    private Date arg1Date;
    ShowAllTimeAdapter showAllTimeAdapter;
    TextView fromTextview, endTextview;
    Button savebtn;
    boolean ischecked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showDate2Binding = ActivityListTimeBinding.inflate(getLayoutInflater());
        setContentView(showDate2Binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        arrayList = new ArrayList<>();
        temp = new ArrayList<>();
        myCalendar = Calendar.getInstance();


        String userID = getIntent().getStringExtra("userid");
        String user_name = getIntent().getStringExtra("userNname");

        showDate2Binding.textname.setText(user_name);

        showDate2Binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        showDate2Binding.lateentryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String starsDate = showDate2Binding.fromDateTextview.getText().toString();
                String lastDate = showDate2Binding.textView30.getText().toString();


                DatabaseReference d = FirebaseDatabase.getInstance().getReference();
                d.child("checktime")
                        .child(userID)
                        .orderByKey()
                        .startAt(starsDate)
                        .endAt(lastDate).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        arrayList.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                            empModel = snapshot1.getValue(EmpModel.class);
                            String diff = empModel.getDiffrent();
                            String[] separated = diff.split(":");
                            String hours = separated[0];

                            int i = Integer.parseInt(hours);


                            if (i < 0) {
                                arrayList.add(empModel);
                                showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
                                showAllTimeAdapter.notifyDataSetChanged();
//                                showDate2Binding.viewrecyclerview.setHasFixedSize(true);
                                showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
                                showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);

                            } else {

                            }
//
//                            arrayList.add(empModel);
//                            showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
//                            showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
//                            showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//               DatabaseReference refr = FirebaseDatabase.getInstance().getReference().child("checktime").child(userID);
//                refr.addValueEventListener(new ValueEventListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {


//                        arrayList.clear();
//                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                            empModel = snapshot1.getValue(EmpModel.class);
//
//                            if (empModel != null) {
//
//
//                                String diff = empModel.getDiffrent();
//                                String[] separated = diff.split(":");
//                                String hours=separated[0];
//
//                                int i = Integer.parseInt(hours);
//
//                                if(i<0){
//
//                                   arrayList.add(empModel);
//                                    showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
//                                    showAllTimeAdapter.notifyDataSetChanged();
//                                    showDate2Binding.viewrecyclerview.setHasFixedSize(true);
//                                    showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
//                                    showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
//
//                                }
//                                else {
//
//                                }
//
//
//                            }
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                    }
//                });

            }
        });

        showDate2Binding.rightentryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String starsDate = showDate2Binding.fromDateTextview.getText().toString();
                String lastDate = showDate2Binding.textView30.getText().toString();


                DatabaseReference d = FirebaseDatabase.getInstance().getReference();
                d.child("checktime")
                        .child(userID)
                        .orderByKey()
                        .startAt(starsDate)
                        .endAt(lastDate).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        arrayList.clear();

                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                            empModel = snapshot1.getValue(EmpModel.class);

                            String diff = empModel.getDiffrent();
                            String[] separated = diff.split(":");
                            String hours = separated[0];

                            int i = Integer.parseInt(hours);


                            if (i >= 0) {

                                arrayList.add(empModel);
                                showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
                                showAllTimeAdapter.notifyDataSetChanged();

//                                showDate2Binding.viewrecyclerview.setHasFixedSize(true);
                                showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
                                showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);

                            } else {

                            }
//
//                            arrayList.add(empModel);
//                            showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
//                            showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
//                            showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


//                DatabaseReference refr = FirebaseDatabase.getInstance().getReference().child("checktime").child(userID);
//                refr.addValueEventListener(new ValueEventListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        arrayList.clear();
//                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                            empModel = snapshot1.getValue(EmpModel.class);
//
//                            if (empModel != null) {
//
//                                String diff = empModel.getDiffrent();
//                                String[] separated = diff.split(":");
//                                String hours=separated[0];
//
//
//                                int i = Integer.parseInt(hours);
//
//
//                                if(i >=0){
//
//                                    arrayList.add(empModel);
//                                    showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
//                                    showAllTimeAdapter.notifyDataSetChanged();
//                                    showDate2Binding.viewrecyclerview.setHasFixedSize(true);
//                                    showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
//                                    showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
//
//                                }
//                                else {
//
//                                }
//
//
//                            }
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });


            }
        });


        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                try {
                    updateLabel1();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        };


//        showDate2Binding.filterImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(ListTimeActivity.this);
//                alertDialog = builder.create();
//                View view1 = getLayoutInflater().inflate(R.layout.custom_alertdialoge, null, false);
//
//
//                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//                ImageView asendingImagviev = view1.findViewById(R.id.imageView12);
//                ImageView desending = view1.findViewById(R.id.imageView6);
//
//                fromTextview = view1.findViewById(R.id.fromtextView);
//                endTextview = view1.findViewById(R.id.endTextView);
//                savebtn = view1.findViewById(R.id.saveButton);
//                cleantextView = view1.findViewById(R.id.cleanbutton);
//
//
////                savebtn.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////
//////                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
//////
//////
//////                        SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
////
////
////
////                        String starsDate = fromTextview.getText().toString();
////                        String lastDate = endTextview.getText().toString();
////
////
////
////                        DatabaseReference d = FirebaseDatabase.getInstance().getReference();
////                        d.child("checktime")
////                                .child(userID)
////                                .orderByKey()
////                                .startAt(starsDate)
////                                .endAt(lastDate).addListenerForSingleValueEvent(new ValueEventListener() {
////                            @Override
////                            public void onDataChange(@NonNull DataSnapshot snapshot) {
////
////                                arrayList.clear();
////                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
////
////                                    empModel = snapshot1.getValue(EmpModel.class);
////                                    arrayList.add(empModel);
////                                    showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
////                                    showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
////                                    showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
////
////                                }
////
////                            }
////
////                            @Override
////                            public void onCancelled(@NonNull DatabaseError error) {
////
////                            }
////                        });
////                    }
////                });
////
////
////                fromTextview.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////                        DatePickerDialog dialog = new DatePickerDialog(ListTimeActivity.this, R.style.MySpinnerDatePickerStyle, date, myCalendar
////                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
////                                myCalendar.get(Calendar.DAY_OF_MONTH));
////
////                        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
////                        dialog.show();
////
////
////                    }
////                });
////
////
////               endTextview.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////
////                        DatePickerDialog datePickerDialog = new DatePickerDialog(ListTimeActivity.this, R.style.MySpinnerDatePickerStyle, date1, myCalendar
////                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
////                                myCalendar.get(Calendar.DAY_OF_MONTH));
////
////                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
////                        datePickerDialog.show();
////                    }
////                });
////
////
////                databaseRef = FirebaseDatabase.getInstance().getReference().child("checktime").child(userID);
////
////                databaseRef.addValueEventListener(new ValueEventListener() {
////                    @RequiresApi(api = Build.VERSION_CODES.N)
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////
////                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
////                            empModel = snapshot1.getValue(EmpModel.class);
////
////                            if (empModel != null) {
////
////                                arrayList.add(empModel);
////                            }
////                        }
////
////                        showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
////                        showAllTimeAdapter.notifyDataSetChanged();
////                        showDate2Binding.viewrecyclerview.setHasFixedSize(true);
////                        showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
////                        showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
////
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////
////                    }
////                });
//
//
//                cleantextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("checktime").child(userID);
//                        database.addValueEventListener(new ValueEventListener() {
//                            @RequiresApi(api = Build.VERSION_CODES.N)
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                arrayList.clear();
//                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                                    empModel = snapshot1.getValue(EmpModel.class);
//                                    if (empModel != null) {
//
//                                        arrayList.add(empModel);
//                                    }
//                                }
//
//
//                                showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
//                                showAllTimeAdapter.notifyDataSetChanged();
//                                showDate2Binding.viewrecyclerview.setHasFixedSize(true);
//                                showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
//                                showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//                    }
//                });
//
//
//                asendingImagviev.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Collections.sort(arrayList, new Comparator<EmpModel>() {
//                            @Override
//                            public int compare(EmpModel o1, EmpModel o2) {
//
//
//                                SimpleDateFormat format = new SimpleDateFormat(
//
//                                        "dd MMM,yyyy");
//                                try {
//                                    arg0Date = format.parse(o1.getDate());
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                                try {
//
//                                    arg1Date = format.parse(o2.getDate());
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                                return arg0Date.compareTo(arg1Date);
//                            }
//                        });
//
//                        showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
//                        showDate2Binding.viewrecyclerview.setHasFixedSize(true);
//                        showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
//                        showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
//                    }
//                });
//
//                desending.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        DatabaseReference references = FirebaseDatabase.getInstance().getReference().child("checktime").child(userID);
//                        references.addValueEventListener(new ValueEventListener() {
//                            @RequiresApi(api = Build.VERSION_CODES.N)
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                arrayList.clear();
//                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                                    empModel = snapshot1.getValue(EmpModel.class);
//
//                                    if (empModel != null) {
//
//                                        arrayList.add(empModel);
//                                    }
//                                }
//                                Collections.sort(arrayList, new Comparator<EmpModel>() {
//                                    @Override
//                                    public int compare(EmpModel o1, EmpModel o2) {
//
//
//                                        SimpleDateFormat format = new SimpleDateFormat(
//                                                "dd MMM,yyyy");
//                                        try {
//                                            arg0Date = format.parse(o1.getDate());
//                                        } catch (ParseException e) {
//                                            e.printStackTrace();
//                                        }
//                                        try {
//
//                                            arg1Date = format.parse(o2.getDate());
//                                        } catch (ParseException e) {
//                                            e.printStackTrace();
//                                        }
//                                        return arg1Date.compareTo(arg0Date);
//                                    }
//                                });
//
//                                showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
//                                showAllTimeAdapter.notifyDataSetChanged();
//                                showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
//                                showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
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
////                        showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
////                        showDate2Binding.viewrecyclerview.setHasFixedSize(true);
////                        showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
////                        showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
//
//
//                    }
//                });
//
//                alertDialog.setView(view1);
//                alertDialog.show();
//
//            }
//        });


        showDate2Binding.applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
//
//
//                        SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                String starsDate = showDate2Binding.fromDateTextview.getText().toString();
                String lastDate = showDate2Binding.textView30.getText().toString();

                // Log.e("dfgd", starsDate+"//"+lastDate);

                DatabaseReference d = FirebaseDatabase.getInstance().getReference();
                d.child("checktime")
                        .child(userID)
                        .orderByKey()
                        .startAt(starsDate)
                        .endAt(lastDate).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        arrayList.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                            empModel = snapshot1.getValue(EmpModel.class);
                            arrayList.add(empModel);
                            showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
                            showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
                            showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        showDate2Binding.fromDateTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(ListTimeActivity.this, R.style.MySpinnerDatePickerStyle, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));


                Button button1 = dialog.getButton(dialog.BUTTON_POSITIVE);


//                   button1.setOnClickListener(new View.OnClickListener() {
//                       @Override
//                       public void onClick(View v) {
//
//                           Toast.makeText(ListTimeActivity.this, "sdfdsfdsfsffsd", Toast.LENGTH_SHORT).show();
//                       }
//                   });


                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();


            }
        });


        showDate2Binding.toggleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ischecked = !ischecked;

                if (ischecked) {

                    showDate2Binding.toggleImg.setImageResource(R.drawable.ic_group);
                    Collections.sort(arrayList, new Comparator<EmpModel>() {
                        @Override
                        public int compare(EmpModel o1, EmpModel o2) {

                            SimpleDateFormat format = new SimpleDateFormat(
                                    "dd MMM,yyyy");
                            try {
                                arg0Date = format.parse(o1.getDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {

                                arg1Date = format.parse(o2.getDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return arg0Date.compareTo(arg1Date);
                        }
                    });

                    showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);

                    showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
                    showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
                } else {

                    showDate2Binding.toggleImg.setImageResource(R.drawable.ic_group_desending);

                    DatabaseReference references = FirebaseDatabase.getInstance().getReference().child("checktime").child(userID);
                    references.addValueEventListener(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            arrayList.clear();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                empModel = snapshot1.getValue(EmpModel.class);

                                if (empModel != null) {


                                    arrayList.add(empModel);
                                }
                            }
                            Collections.sort(arrayList, new Comparator<EmpModel>() {
                                @Override
                                public int compare(EmpModel o1, EmpModel o2) {

                                    SimpleDateFormat format = new SimpleDateFormat(
                                            "dd MMM,yyyy");
                                    try {
                                        arg0Date = format.parse(o1.getDate());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    try {

                                        arg1Date = format.parse(o2.getDate());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    return arg1Date.compareTo(arg0Date);
                                }
                            });

                            showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
                            showAllTimeAdapter.notifyDataSetChanged();
                            showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
                            showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });


        showDate2Binding.textView30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(ListTimeActivity.this, R.style.MySpinnerDatePickerStyle, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));


                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

                //  showDate2Binding.textView30.setText("");


            }
        });


        showDate2Binding.clearTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("checktime").child(userID);
                database.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        arrayList.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            empModel = snapshot1.getValue(EmpModel.class);
                            if (empModel != null) {

                                arrayList.add(empModel);
                            }
                        }


                        showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
                        showAllTimeAdapter.notifyDataSetChanged();
                        showDate2Binding.viewrecyclerview.setHasFixedSize(true);
                        showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
                        showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        databaseRef = FirebaseDatabase.getInstance().getReference().child("checktime").child(userID);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    empModel = snapshot1.getValue(EmpModel.class);

                    if (empModel != null) {

                        arrayList.add(empModel);
                    }
                }

                showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);
                showAllTimeAdapter.notifyDataSetChanged();

                showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
                showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void sortArrayList(ArrayList<EmpModel> arrayList) {

        if (arrayList != null) {

            Collections.sort(arrayList, new Comparator<EmpModel>() {
                @Override
                public int compare(EmpModel o1, EmpModel o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });

        }

        showAllTimeAdapter = new ShowAllTimeAdapter(ListTimeActivity.this, arrayList);


        showDate2Binding.viewrecyclerview.setLayoutManager(new LinearLayoutManager(ListTimeActivity.this));
        showDate2Binding.viewrecyclerview.setAdapter(showAllTimeAdapter);
    }


    private void updateLabel1() throws ParseException {

        String myFormat = "dd MMM,yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        showDate2Binding.textView30.setText(sdf.format(myCalendar.getTime()));

    }

    private void updateLabel() {
        String myFormat = "dd MMM,yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        showDate2Binding.fromDateTextview.setText(sdf.format(myCalendar.getTime()));
    }

    private void sortArray(ArrayList<EmpModel> arrayList) {

    }
}