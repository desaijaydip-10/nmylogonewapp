package com.example.radio.Model;

public class LeaveModel {



    String  leavetype;
    String startdate;
    String enddate;
    String leaveday;
    String reasone;
    String statuscheck;
    String userid;
    String categorychoose;
    int leavedate;

    String leavetaken;



    String notifiy;



    public LeaveModel(String userid, String leavetype, String startdate, String enddate, String leaveday, String reasone, String statuschechk, int leavedate, String categorychoose, String leavetaken, String  notifiy  ) {
        this.leavetype = leavetype;
        this.startdate = startdate;
        this.enddate = enddate;
        this.leaveday = leaveday;
        this.reasone = reasone;
        this.statuscheck = statuschechk;
        this.leavedate = leavedate;
        this.userid = userid;
        this.categorychoose= categorychoose;
        this.leavetaken= leavetaken;
        this.notifiy= notifiy;
    }

    public String getLeavetype() {

        return leavetype;
    }

    public String getLeavetaken() {
        return leavetaken;
    }

    public void setLeavetaken(String leavetaken) {
        this.leavetaken = leavetaken;
    }
    public String getNotifiy() {
        return notifiy;
    }

    public void setNotifiy(String notifiy) {
        this.notifiy = notifiy;
    }



    public String getCategorychoose() {
        return categorychoose;
    }

    public void setCategorychoose(String categorychoose) {
        this.categorychoose = categorychoose;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getLeaveday() {
        return leaveday;
    }

    public void setLeaveday(String leaveday) {
        this.leaveday = leaveday;
    }

    public String getReasone() {
        return reasone;
    }

    public void setReasone(String reasone) {
        this.reasone = reasone;
    }

    public String getStatuscheck() {
        return statuscheck;
    }

    public void setStatuscheck(String statuscheck) {
        this.statuscheck = statuscheck;
    }





    public int getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(int leavedate) {
        this.leavedate = leavedate;
    }
}

