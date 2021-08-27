package com.example.radio.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import com.example.radio.Activity.DashboradActivity;
import com.example.radio.Model.AllData;
import com.example.radio.Model.CheckModel;
import com.example.radio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class DashbordAdapter extends RecyclerView.Adapter<DashbordAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<AllData> arrayList;
     //CheckedStatuts checkedStatuts;

    private ViewBinderHelper binderHelper = new ViewBinderHelper();
    boolean checked;
    String url;
    FirebaseAuth auth;
    private StorageReference storageReference;


    public DashbordAdapter(Context context, ArrayList<AllData> arrayList, DashboradActivity dashboradActivity) {

        this.context = context;
        this.arrayList = arrayList;

        auth = FirebaseAuth.getInstance();
        // checkedStatuts = dashboradActivity;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_layout, parent, false);
       // view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {


        if (arrayList.get(position).getImg_url() == null) {

             holder.pf_img.setBackgroundResource(R.drawable.ic_user);
        }


        holder.textView.setText(arrayList.get(position).getmName());
        Glide.with(context).load(arrayList.get(position).getImg_url()).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.pf_img);

        binderHelper.bind(holder.swipeLayout, arrayList.get(position).getmName());
        holder.bind(arrayList.get(position).getmName());



        holder.frontLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayText = "" + arrayList.get(position).getmName() + " clicked";
                Toast.makeText(context, displayText, Toast.LENGTH_SHORT).show();


            }
        });


        holder.accepttextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checked = true;
                String value = "Request Accept";

                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("statuscheck");

                databaseReference1.child(UUID.randomUUID().toString()).setValue(new CheckModel(checked, value)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        holder.accepttextView.setText(null);
                        holder.checkImg.setVisibility(View.VISIBLE);
                        holder.checkImg.setImageResource(R.drawable.ic_baseline_check_24);


                    }
                });
            }
        });

        holder.declinetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checked == true) {
                    Toast.makeText(context, "Request Approve", Toast.LENGTH_SHORT).show();
                } else {
                    holder.declinetxt.setText(null);
                    holder.cancelImg.setVisibility(View.VISIBLE);
                    holder.cancelImg.setImageResource(R.drawable.ic_baseline_cancel_24);

                }


            }
        });


    }

    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

//


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {


        ImageView pf_img, checkImg, cancelImg;


        SwipeRevealLayout swipeLayout;
        View frontLayout;
        View deleteLayout;
        TextView textView, accepttextView, declinetxt;
        ;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            swipeLayout = (SwipeRevealLayout) itemView.findViewById(R.id.swipe_layout);
            frontLayout = itemView.findViewById(R.id.front_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            textView = (TextView) itemView.findViewById(R.id.userTextView);
            accepttextView = itemView.findViewById(R.id.accepttextView);

            declinetxt = itemView.findViewById(R.id.declineTextView);
            checkImg = itemView.findViewById(R.id.checkImagview);
            cancelImg = itemView.findViewById(R.id.cancelImagview);

            pf_img = itemView.findViewById(R.id.profile_img11);

        }


        public void bind(final String data) {



        }



    }





}
