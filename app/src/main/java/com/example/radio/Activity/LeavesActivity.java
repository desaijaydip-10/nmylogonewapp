package com.example.radio.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.radio.Adapter.LeaveStatusAdapter;
import com.example.radio.Model.LeaveGetModel;
import com.example.radio.R;
import com.example.radio.databinding.ActivityLeavesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class LeavesActivity extends AppCompatActivity {


    ActivityLeavesBinding leavesBinding;
    FirebaseAuth auth;
    String leavetake;
    String leaves;
    int i, count = 0;

    ArrayList<LeaveGetModel> arrayList;
    DatabaseReference databaseReference, refrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        leavesBinding = ActivityLeavesBinding.inflate(getLayoutInflater());
        setContentView(leavesBinding.getRoot());

        arrayList = new ArrayList<>();

        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("timeselector").child(auth.getCurrentUser().getUid());
        refrence = FirebaseDatabase.getInstance().getReference().child("timeselector").child(auth.getCurrentUser().getUid());


        Calendar c = Calendar.getInstance();

        int currentdate = c.getActualMaximum(Calendar.DATE);
        int lastdate = c.getActualMaximum(Calendar.DATE);



        refrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String getkey = snapshot1.getKey();
                    DatabaseReference refrencee = FirebaseDatabase.getInstance().getReference().child("timeselector")
                            .child(auth.getCurrentUser().getUid())
                            .child(getkey);

                    refrencee.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String getleave = snapshot.child("leavetaken").getValue(String.class);

                            if (getleave.equals("1")) {

                                leaves = leavesBinding.leaveTakenTextView.getText().toString();
                                int  c = Integer.parseInt(leaves);

                                Log.e("sffsdfsd", String.valueOf(c));

                                count = ++c;
                            }
                            leavesBinding.leaveTakenTextView.setText(String.valueOf(count));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        String currentleave = leavesBinding.leaveCarryTextView.getText().toString();
        if (currentdate == lastdate) {

            int i = Integer.parseInt(currentleave);

            i = ++i;
            leavesBinding.leaveCarryTextView.setText(String.valueOf(i));

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("carreyleft").child(auth.getCurrentUser().getUid());

            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    snapshot.getRef().child("timeset").setValue(leavesBinding.leaveCarryTextView.getText().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
//             leavesBinding.leaveCarryTextView.setText(String.valueOf(2));
        } else {


        }



        leavesBinding.backArrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        leavesBinding.holidayTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                LocalDate futureDate = LocalDate.now().plusMonths(1);

                Toast.makeText(LeavesActivity.this, "gdfg", Toast.LENGTH_SHORT).show();
                WebView webView = (WebView) new WebView(LeavesActivity.this);
                webView.loadUrl("https://idea2codeinfotech.com/images/policy/b5f2118a38215d88c175f9edc8d12fe8.pdf");

                webView.getSettings().setJavaScriptEnabled(true);

//               WebView webView = WebView.
//                simpleWebView.getSettings().setJavaScriptEnabled(true);
//                simpleWebView.loadUrl("http://www.google.com");


            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                arrayList.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                    LeaveGetModel leaveGetModel = snapshot1.getValue(LeaveGetModel.class);

                    arrayList.add(leaveGetModel);

                    leavesBinding.recyclerviewLeaveStatus.setLayoutManager(new LinearLayoutManager(LeavesActivity.this));
                    LeaveStatusAdapter adapter = new LeaveStatusAdapter(getApplicationContext(), arrayList);
                    adapter.notifyDataSetChanged();
                    leavesBinding.recyclerviewLeaveStatus.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        leavesBinding.applyLeavetextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setApplyLeaves();
            }
        });
    }

    private void setApplyLeaves() {
        startActivity(new Intent(LeavesActivity.this, ApplyLeaveActivity.class));
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }


    public class MyWebViewClient extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}