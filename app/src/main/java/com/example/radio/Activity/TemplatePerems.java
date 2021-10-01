package com.example.radio.Activity;

public class TemplatePerems {

    String  username, useremail, usersubject, toemail, usermessage, toname;


    public TemplatePerems(String username, String useremail, String usersubject, String toemail, String usermessage, String toname) {
        this.username = username;
        this.useremail = useremail;
        this.usersubject = usersubject;
        this.toemail = toemail;
        this.usermessage = usermessage;
        this.toname = toname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsersubject() {
        return usersubject;
    }

    public void setUsersubject(String usersubject) {
        this.usersubject = usersubject;
    }

    public String getToemail() {
        return toemail;
    }

    public void setToemail(String toemail) {
        this.toemail = toemail;
    }

    public String getUsermessage() {
        return usermessage;
    }

    public void setUsermessage(String usermessage) {
        this.usermessage = usermessage;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }
}
