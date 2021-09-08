package com.example.radio.Model;

public class TimeDateModel {

    String time ,date, endtime;

    public TimeDateModel(String time, String date, String endtime) {
        this.time = time;
        this.date = date;
        this.endtime = endtime;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
