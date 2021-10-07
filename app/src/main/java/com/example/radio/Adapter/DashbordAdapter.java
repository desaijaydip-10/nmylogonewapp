package com.example.radio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import com.example.radio.Activity.DashboradActivity;
import com.example.radio.Activity.ProfileActivity;
import com.example.radio.Model.AllData;
import com.example.radio.R;
import com.example.radio.UserStatusIntetFace;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DashbordAdapter extends RecyclerView.Adapter<DashbordAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<AllData> arrayList;

    UserStatusIntetFace userStatusIntetFace;
    private ViewBinderHelper binderHelper = new ViewBinderHelper();
    boolean checked, statuschecek;

    FirebaseAuth auth;
    String statuschecked;
    String value;
    private StorageReference storageReference;
//    UserStatusIntetFace userStatusIntetFace;

    public DashbordAdapter(Context context, ArrayList<AllData> arrayList,  UserStatusIntetFace userStatusIntetFace) {

        this.context = context;
        this.arrayList = arrayList;
        this.userStatusIntetFace = userStatusIntetFace;
        auth = FirebaseAuth.getInstance();

    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_layout, parent, false);
        return new CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        AllData allData= arrayList.get(position);


        if(arrayList.get(position).getCheckedStatus().equals("0")) {

            binderHelper.closeLayout(String.valueOf(holder.swipeLayout));
            holder.img.setVisibility(View.VISIBLE);
            holder.img.setImageResource(R.drawable.ic_baseline_pending_24);

        }

        if (arrayList.get(position).getCheckedStatus().equals("1")) {



            holder.accepttextView.setText(null);
            holder.checkImg.setVisibility(View.VISIBLE);
            holder.checkImg.setImageResource(R.drawable.ic_baseline_check_24);
            holder.img.setVisibility(View.VISIBLE);
            holder.img.setImageResource(R.drawable.ic_right);

            binderHelper.lockSwipe(arrayList.get(position).getUserid());

        }
        else if (arrayList.get(position).getCheckedStatus().equals("2")) {

            holder.img.setVisibility(View.VISIBLE);
            holder.img.setImageResource(R.drawable.ic_cancel_svgrepo_com);

        }

        if (arrayList.get(position).getImgUrl() == null) {
            holder.pf_img.setBackgroundResource(R.drawable.ic_user);

        }

        holder.swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binderHelper.closeLayout(arrayList.get(position).getUserid());
                holder.swipeLayout.setLockDrag(false);
                context.startActivity(new Intent(context, DashboradActivity.class));
            }
        });

        holder.accepttextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                statuschecked = "1";
                userStatusIntetFace.userStatusInterface(statuschecked, arrayList.get(position).getUserid());

                holder.accepttextView.setText(null);
                holder.checkImg.setVisibility(View.VISIBLE);
                holder.checkImg.setImageResource(R.drawable.ic_baseline_check_24);

                holder.img.setImageResource(R.drawable.ic_right);

                holder.swipeLayout.close(false);
                holder.swipeLayout.setLockDrag(true);
                binderHelper.lockSwipe(arrayList.get(position).getUserid());



//                binderHelper.closeLayout(arrayList.get(position).getUserid());
//


//                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("statuscheck");
//
//                databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().toString()).setValue(new CheckModel(checked, value)).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {


//                    }
//                });
            }
        });

        holder.textView.setText(arrayList.get(position).getmName());
        Glide.with(context).load(arrayList.get(position).getImgUrl()).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.pf_img);



        holder.declinetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                statuschecked = "2";
                userStatusIntetFace.userStatusInterface(statuschecked, arrayList.get(position).getUserid());

                holder.declinetxt.setText(null);
                holder.cancelImg.setVisibility(View.VISIBLE);
                holder.cancelImg.setImageResource(R.drawable.ic_baseline_cancel_24);
                holder.img.setImageResource(R.drawable.ic_cancel_svgrepo_com);

                holder.swipeLayout.close(false);
                holder.swipeLayout.setLockDrag(true);
                holder.swipeLayout.cancelLongPress();

            }
        });


        binderHelper.bind(holder.swipeLayout, arrayList.get(position).getmName());
        holder.bind(arrayList.get(position).getmName());


        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                

              //  context.startActivity(new Intent(context, ProfileActivity.class));

                Intent intent= new Intent(context, ProfileActivity.class);
                intent.putExtra("data", allData);
                context.startActivity(intent);

                // Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
            }
        });


//        holder.swipeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "errro", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

//    public void saveStates(Bundle outState) {
//        binderHelper.saveStates(outState);
//    }
//
//    public void restoreStates(Bundle inState) {
//        binderHelper.restoreStates(inState);
//    }

//


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView pf_img, checkImg, cancelImg, img;
        SwipeRevealLayout swipeLayout;
        View frontLayout;
        View deleteLayout;
        TextView textView, accepttextView, declinetxt;


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
            img = itemView.findViewById(R.id.imgcheckvalue);
        }


        public void bind(final String data) {


        }


    }


}
