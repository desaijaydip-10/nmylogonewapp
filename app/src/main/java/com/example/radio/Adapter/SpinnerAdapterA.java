package com.example.radio.Adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.radio.R;


public class SpinnerAdapterA extends ArrayAdapter<String> {
    private String[] objects;

    public SpinnerAdapterA(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        this.objects=objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(final int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent, false);


        final TextView label=(TextView)row.findViewById(R.id.textView19);

        if(position ==0){

            label.setTextColor(Color.GRAY);
        }

        label.setText(objects[position]);
        return row;
    }
}