package com.example.radio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.radio.Activity.EmployeDetailsActivity;
import com.example.radio.Model.AllData;
import com.example.radio.Model.LeaveGetModel;
import com.example.radio.Model.NewDataGetModel;
import com.example.radio.R;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeStatusAdapter extends RecyclerView.Adapter<EmployeStatusAdapter.CustomViewHolder> {

    Context context;
    ArrayList<AllData> allDataArrayList;
    ArrayList<LeaveGetModel> arrayList;
    int  sum;


    public EmployeStatusAdapter(Context context, ArrayList<LeaveGetModel> arrayList,int i ,  ArrayList<AllData> allDataArrayList) {
        this.context = context;
        this.allDataArrayList = allDataArrayList;
        this.arrayList = arrayList;
        this.sum = i;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_leave_status_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        AllData allData = allDataArrayList.get(position);


          int  ss=0;
          ss= ss+sum ;
//       String leave=leaveGetModel.getLeaveTaken();
//        int leavecnt= Integer.parseInt(leave);
//        leavecnt  += leavecnt;

//        Log.e("Sfdsf", String.valueOf(leave));


        holder.cntNotification.setText(String.valueOf(ss));

        holder.empNameTextview.setText(allData.getmName());

        if(allData.getImgUrl() == null){
            holder.empcircleImageView.setImageResource(R.drawable.ic_user);
        }
        else {
            Glide.with(context).load(allData.getImgUrl()).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.empcircleImageView);
        }


          holder.cardView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Intent intent = new Intent(context, EmployeDetailsActivity.class);
                  intent.putExtra("userid",arrayList.get(position).getUserid());
                  context.startActivity(intent);
              }
          });

    }

    @Override
    public int getItemCount() {
        return allDataArrayList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        CircleImageView empcircleImageView;
        TextView empNameTextview, cntNotification;


        CardView cardView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            empcircleImageView = itemView.findViewById(R.id.empprofileImg);
            empNameTextview = itemView.findViewById(R.id.empTextView);
            cardView  = itemView.findViewById(R.id.custom_cardvide);
            cntNotification = itemView.findViewById(R.id.countTextView);
        }
    }
}
