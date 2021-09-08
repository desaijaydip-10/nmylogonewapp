package com.example.radio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.Model.GetAllTimeDataModel;
import com.example.radio.R;

import java.util.ArrayList;

public class ShowAllTimeAdapter extends RecyclerView.Adapter<ShowAllTimeAdapter.CustomeViewHolder>{


    Context context;
    ArrayList<GetAllTimeDataModel> arrayList;

    public ShowAllTimeAdapter(Context context, ArrayList<GetAllTimeDataModel> arrayList) {
        this.arrayList=arrayList;
        this.context= context;
    }

    @NonNull
    @Override
    public CustomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_all_data_layout, null, false);
        return new CustomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeViewHolder holder, int position) {


        holder.time_in.setText(arrayList.get(position).getStarttime());
        holder.time_out.setText(arrayList.get(position).getEndtime());
        holder.diffrent.setText(arrayList.get(position).getDiffrent());
        holder.current_date.setText(arrayList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class  CustomeViewHolder extends  RecyclerView.ViewHolder{

        TextView time_in, time_out, diffrent, current_date;

        public CustomeViewHolder(@NonNull View itemView) {
            super(itemView);

            time_in= itemView.findViewById(R.id.time_inTextView);

            current_date= itemView.findViewById(R.id.date);
            time_out= itemView.findViewById(R.id.time_inout);
            diffrent= itemView.findViewById(R.id.time_diffrentTextView);
        }
    }
}