package com.example.radio.Fragmenent;

public class TimeSetModel {


    String  starttime;
    String endtime;
    String diffrent;
    String  date;

    public TimeSetModel(String starttime, String endtime, String diffrent, String date) {
        this.starttime = starttime;
        this.endtime = endtime;
        this.diffrent = diffrent;
        this.date = date;
    }


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
}
