package com.example.radio.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.Model.EmpModel;
import com.example.radio.Model.GetAllTimeDataModel;
import com.example.radio.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowAllTimeAdapter extends RecyclerView.Adapter<ShowAllTimeAdapter.CustomeViewHolder>{

    Context context;
    ArrayList<EmpModel> arrayList;
    private Date arg0Date;
    private Date arg1Date;
    int hours = 0;
    int minutes = 0;
    int micros;
    boolean isstate = false;

    ArrayList<EmpModel> temp;

    public ShowAllTimeAdapter(Context context, ArrayList<EmpModel> empModel) {
        this.arrayList = empModel;
        this.context = context;
        temp = new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public CustomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_all_data_layout, null, false);
        return new CustomeViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomeViewHolder holder, int position) {

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

        String diff = arrayList.get(position).getDiffrent();


        String[] separated = diff.split(":");
        String hours = separated[0];
        String minutes = separated[1];

        int i = Integer.parseInt(hours);
        int m = Integer.parseInt(minutes);


        if (i < 0) {
            holder.diffrent.setTextColor(Color.RED);

            i = -i;
            m = -m;
            Log.e("sfdsd", String.valueOf(i));
            Log.e("sfdsd", String.valueOf(m));
            String dsf = "" + i + ":" + m;

            holder.diffrent.setText(dsf);


        } else {

            holder.diffrent.setTextColor(context.getResources().getColor(R.color.right_entry));
            holder.diffrent.setText(arrayList.get(position).getDiffrent());

        }


//        String splitTime[] = diff.split(":");
//
//        String hours = splitTime[0];
//
//        int i = Integer.parseInt(hours);
//
//        Log.e("adsdd", String.valueOf(i));
//        String minutes = splitTime[1];
//
//        int i2 = Integer.parseInt(minutes);
//
//        if (i<0 &&  i2< 0) {
//            isstate = true;
//        }
//
//        if(i >=0 && i2>=0) {
//            isstate = false;
//        }


//        holder.diffrent.setText(arrayList.get(position).getDiffrent());
        String arr = arrayList.get(position).getStarttime();

        long l = Long.parseLong(arr);
        String dateString = formatter.format(new Date(l));
        String end = arrayList.get(position).getEndtime();

        long l2 = Long.parseLong(end);

        String end_time = formatter.format(new Date(l2));
        holder.time_in.setText(dateString);
        holder.time_out.setText(end_time);


        holder.current_date.setText(arrayList.get(position).getDate());
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public void setAsending(ArrayList<EmpModel> temp) {


        arrayList.clear();

        arrayList.addAll(temp);
//         Collections.sort(arrayList, new Comparator<EmpModel>() {
////            @Override
////            public int compare(EmpModel o1, EmpModel o2) {
////
////                SimpleDateFormat format = new SimpleDateFormat(
////                        "dd-MMM-yyyy");
////                try {
////
////                    arg0Date = format.parse(o1.getDate());
////
////                } catch (ParseException e) {
////                    e.printStackTrace();
////               }
//                try {
//                    arg1Date = format.parse(o2.getDate());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                return arg0Date.compareTo(arg1Date) ;
//            }
//        });
    }


    class CustomeViewHolder extends RecyclerView.ViewHolder {

        TextView time_in, time_out, diffrent, current_date;

        public CustomeViewHolder(@NonNull View itemView) {
            super(itemView);

            time_in = itemView.findViewById(R.id.time_inTextView);
            current_date = itemView.findViewById(R.id.date);
            time_out = itemView.findViewById(R.id.time_inout);
            diffrent = itemView.findViewById(R.id.time_diffrentTextView);
        }
    }

}