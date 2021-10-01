package com.example.radio;

public class FirstClass {

    String serviceid , templateid, userid;
    TemplatePrarem templatePrarem;

    public FirstClass() {
    }


    public FirstClass(String serviceid, String templateid, String userid, TemplatePrarem templatePrarem) {
        this.serviceid = serviceid;
        this.templateid = templateid;
        this.userid = userid;
        this.templatePrarem = templatePrarem;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getTemplateid() {
        return templateid;
    }

    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public TemplatePrarem getTemplatePrarem() {
        return templatePrarem;
    }

    public void setTemplatePrarem(TemplatePrarem templatePrarem) {
        this.templatePrarem = templatePrarem;
    }
}
