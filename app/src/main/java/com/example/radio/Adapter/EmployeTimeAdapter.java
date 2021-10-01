package com.example.radio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.radio.Activity.ListTimeActivity;
import com.example.radio.Model.AllData;
import com.example.radio.Model.EmpModel;
import com.example.radio.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeTimeAdapter extends RecyclerView.Adapter<EmployeTimeAdapter.Custom_emp> {

    Context context;
    ArrayList<AllData> arrayList;

    ArrayList<EmpModel> arrayListemp;

    FirebaseAuth mauth;

    public EmployeTimeAdapter(Context context, ArrayList<AllData> arrayList) {

        this.context = context;
        this.arrayList = arrayList;
        arrayListemp = new ArrayList<>();
        mauth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public Custom_emp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.emp_layout_custom, parent, false);
        //  view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Custom_emp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Custom_emp holder, int position) {


        if (arrayList.get(position).getImgUrl() == null) {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_user);
        }
        else {
            Glide.with(context).load(arrayList.get(position).getImgUrl()).into(holder.circleImageView);
        }
        holder.textView_name.setText(arrayList.get(position).getmName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ListTimeActivity.class);
                intent.putExtra("userid", arrayList.get(position).getUserid());
                intent.putExtra("userNname", arrayList.get(position).getmName());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Custom_emp extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView textView_name;
        CardView cardView;

        public Custom_emp(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.profile_img11);
            textView_name = itemView.findViewById(R.id.userTextView);
            cardView = itemView.findViewById(R.id.checkcardview);


        }
    }

}
