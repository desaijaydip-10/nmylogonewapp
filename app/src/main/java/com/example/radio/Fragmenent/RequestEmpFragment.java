package com.example.radio.Fragmenent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radio.Activity.DashboradActivity;
import com.example.radio.Adapter.DashbordAdapter;
import com.example.radio.Model.AllData;
import com.example.radio.R;
import com.example.radio.UserStatusIntetFace;
import com.example.radio.databinding.ActivityDashboradBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestEmpFragment extends Fragment {


    ActivityDashboradBinding dashboradBinding;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    public static ArrayList<AllData> arrayList;
    RecyclerView recyclerView;
    Toolbar toolbar;
    String selectef;
    static boolean checked;

    String value;

    DashbordAdapter dashbordAdapter;
    private DatabaseReference databaseReference1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_request, container, false);
        auth = FirebaseAuth.getInstance();
        arrayList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        recyclerView = view.findViewById(R.id.recyclerview1);

        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    AllData allData = snapshot1.getValue(AllData.class);
                    String selected = allData.getMselected();
                    boolean check = allData.isVerifyCheck();


                    if (selected.equals("Employee")  &&  check== true) {

                        arrayList.add(allData);

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        dashbordAdapter = new DashbordAdapter(getContext(), arrayList, value, new UserStatusIntetFace() {
                            @Override
                            public void userStatusInterface(String statuschecked, String postion) {

                                databaseReference.child(postion).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        snapshot.getRef().child("checked_status").setValue(statuschecked);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }

                            @Override
                            public void showAllDataInterface(AllData allData) {

                            }
                        });

                        recyclerView.setAdapter(dashbordAdapter);
                        dashbordAdapter.notifyDataSetChanged();
                    } else {


                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return view;
    }
}