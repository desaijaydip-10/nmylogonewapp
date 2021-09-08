package com.example.radio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.Activity.CheckTimeActivity;
import com.example.radio.Activity.DashboradActivity;
import com.example.radio.Activity.EmployessActivity;
import com.example.radio.Fragmenent.RequestEmpFragment;
import com.example.radio.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


    Context context;
    int[] img;
    String[] emp_info;

    public CustomAdapter(Context context, int[] img, String[] emp_info) {
        this.context = context;
        this.img = img;
        this.emp_info = emp_info;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_empinfo_layout, null, true);

        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {



          holder.img_empinfo.setImageResource(img[position]);
          holder.emp_permission.setText(emp_info[position]);

          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  if(position==0){
                      context.startActivity(new Intent(context, DashboradActivity.class));

                  }
                  else  if(position==1){
                      context.startActivity(new Intent(context, EmployessActivity.class));
                  }
                   else {


                      context.startActivity(new Intent(context, CheckTimeActivity.class));

                  }

              }
          });






    }

    @Override
    public int getItemCount() {
        return img.length;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView img_empinfo;
        TextView emp_permission;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            img_empinfo = itemView.findViewById(R.id.imageView7);
            emp_permission = itemView.findViewById(R.id.textView22);

        }
    }
}
