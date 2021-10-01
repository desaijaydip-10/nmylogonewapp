package com.example.radio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.radio.Activity.ProfileActivity;
import com.example.radio.Interface.ShowAllDataInterfsce;
import com.example.radio.Model.AllData;
import com.example.radio.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployessAdapter  extends  RecyclerView.Adapter<EmployessAdapter.Custom_emp> {

    Context context;
    ArrayList<AllData> arrayList;
    ShowAllDataInterfsce showAllDataInterfsce;
    public EmployessAdapter(Context context, ArrayList<AllData> arrayList, ShowAllDataInterfsce showAllDataInterfsce) {

        this.context= context;
        this.arrayList= arrayList;
        this.showAllDataInterfsce= showAllDataInterfsce;

    }


    @NonNull
    @Override
    public Custom_emp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_layout_custom, null, true);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Custom_emp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Custom_emp holder, int position) {
        AllData allData= arrayList.get(position);

        Glide.with(context).load(arrayList.get(position).getImgUrl()).into(holder.circleImageView);
        holder.textView_name.setText(arrayList.get(position).getmName());


        holder.textView_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(context, ProfileActivity.class);
                intent.putExtra("data", allData);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Custom_emp extends RecyclerView.ViewHolder{

   CircleImageView circleImageView;
   TextView textView_name;
        public Custom_emp(@NonNull View itemView) {
            super(itemView);

            circleImageView= itemView.findViewById(R.id.profile_img11);
            textView_name = itemView.findViewById(R.id.userTextView);










    }
    }


}
