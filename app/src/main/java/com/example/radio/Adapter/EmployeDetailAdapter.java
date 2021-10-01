package com.example.radio.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.Activity.EmployeStatusInterface;
import com.example.radio.Model.LeaveGetModel;
import com.example.radio.R;

import java.util.ArrayList;

public class EmployeDetailAdapter extends RecyclerView.Adapter<EmployeDetailAdapter.CustomHolder> {

    Context context;
    ArrayList<LeaveGetModel> modelArrayList;
    EmployeStatusInterface employeStatusInterface;

    public EmployeDetailAdapter(Context context, ArrayList<LeaveGetModel> modelArrayList, EmployeStatusInterface employeStatusInterface) {

        this.context = context;
        this.modelArrayList = modelArrayList;
        this.employeStatusInterface= employeStatusInterface;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view_emp_details, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {

          LeaveGetModel leaveGetModel = modelArrayList.get(position);

             holder.leavestypetxt.setText(leaveGetModel.getLeavetype());
             holder.firstDatetxt.setText(leaveGetModel.getStartdate());
             holder.enddatetxt.setText(leaveGetModel.getEnddate());
             holder.durationtxt.setText(leaveGetModel.getLeavedate()+"");
             holder.reasonetxt.setText(leaveGetModel.getReasone());

//          String dates= leaveGetModel.getEnddate();

        holder.declinetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                employeStatusInterface.employeStatus("2", leaveGetModel.getStartdate(), leaveGetModel.getUserid());
            }
        });


        holder.accepttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                employeStatusInterface.employeStatus("1", leaveGetModel.getStartdate(), leaveGetModel.getUserid());

            }
        });


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        TextView leavestypetxt, firstDatetxt, enddatetxt, durationtxt, reasonetxt, declinetxt, accepttxt;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);

            leavestypetxt = itemView.findViewById(R.id.leaveTyepetextView);
            firstDatetxt = itemView.findViewById(R.id.FirstDateTextView);
            enddatetxt = itemView.findViewById(R.id.EndDateTextView);


            durationtxt = itemView.findViewById(R.id.durationDaetTextView);
            reasonetxt = itemView.findViewById(R.id.reasoneTextView);

            declinetxt = itemView.findViewById(R.id.lateentryButton);
            accepttxt = itemView.findViewById(R.id.rightentryButton);
        }
    }

}
