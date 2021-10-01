package com.example.radio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.Model.LeaveGetModel;
import com.example.radio.R;

import java.util.ArrayList;

public class LeaveStatusAdapter extends RecyclerView.Adapter<LeaveStatusAdapter.CustomViewHolder> {

        Context context;
        ArrayList<LeaveGetModel> arrayList;

public LeaveStatusAdapter(Context context, ArrayList<LeaveGetModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        }

@NonNull
@Override
public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_leave_status_layout, parent, false);
        return new CustomViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {



        LeaveGetModel leaveGetModel = arrayList.get(position);

        if(leaveGetModel.getStatuscheck().equals("0")){
        holder.statusImg.setImageResource(R.drawable.ic_pending_icon);
        }
        else if(leaveGetModel.getStatuscheck().equals("1")){
        holder.statusImg.setImageResource(R.drawable.ic_accept_icon);
        }
        else {
        holder.statusImg.setImageResource(R.drawable.ic_delet_icon);
        }
        holder.startLeaveDate.setText(leaveGetModel.getStartdate());
        holder.endLeaveDate.setText(leaveGetModel.getEnddate());

        int date = leaveGetModel.getLeavedate();

        holder.leaveDayTextView.setText(date +" "+"Days");
        holder.leaveTypeTextView.setText(leaveGetModel.getLeavetype());


        }

@Override
public int getItemCount() {
        return arrayList.size();
        }

class CustomViewHolder extends RecyclerView.ViewHolder {


    TextView startLeaveDate, endLeaveDate, leaveTypeTextView, leaveDayTextView;
    ImageView statusImg;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);



        startLeaveDate = itemView.findViewById(R.id.startLeaveDateTextView);
        endLeaveDate = itemView.findViewById(R.id.endLeaveDateTextView);
        leaveDayTextView = itemView.findViewById(R.id.leaveDayTextView);
        leaveTypeTextView = itemView.findViewById(R.id.leaveTypeTextView);

        statusImg = itemView.findViewById(R.id.statusImageView);
    }
}


}