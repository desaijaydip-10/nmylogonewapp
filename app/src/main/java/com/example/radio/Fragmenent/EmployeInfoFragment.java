package com.example.radio.Fragmenent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radio.Adapter.CustomAdapter;
import com.example.radio.R;

public class EmployeInfoFragment extends Fragment {

    int img[];
    String emp_info[];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_employe_info, container, false);
        img = new int[]{R.drawable.ic_interview_1, R.drawable.ic_businessman_1, R.drawable.ic_time_1};


        emp_info = new String[]{"Request", "Employees","Check Time"};


        RecyclerView recyclerView = view.findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CustomAdapter(getContext(), img, emp_info));


        return view;
    }

}