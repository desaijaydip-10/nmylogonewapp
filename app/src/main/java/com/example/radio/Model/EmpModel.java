package com.example.radio.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class  EmpModel implements Parcelable, Comparable {

    String starttime;
    String endtime;
    String diffrent;
    String date;


    public EmpModel(String starttime, String endtime, String diffrent, String date) {
        this.starttime = starttime;
        this.endtime = endtime;
        this.diffrent = diffrent;
        this.date = date;
    }

    public EmpModel() {

    }


    protected EmpModel(Parcel in) {
        starttime = in.readString();
        endtime = in.readString();
        diffrent = in.readString();
        date = in.readString();
    }

    public static final Parcelable.Creator<EmpModel> CREATOR = new Parcelable.Creator<EmpModel>() {
        @Override
        public EmpModel createFromParcel(Parcel in) {
            return new EmpModel(in);
        }

        @Override
        public EmpModel[] newArray(int size) {
            return new EmpModel[size];
        }
    };

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getDiffrent() {
        return diffrent;
    }

    public void setDiffrent(String diffrent) {
        this.diffrent = diffrent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(starttime);
        dest.writeString(endtime);
        dest.writeString(diffrent);
        dest.writeString(date);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}