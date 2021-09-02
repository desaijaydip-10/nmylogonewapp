package com.example.radio.Model;

public class StatusCheckModel {

    String checkvalue ;
    private String Value;


    public StatusCheckModel(String checkvalue, String value) {
        this.checkvalue = checkvalue;
        Value = value;
    }


    public StatusCheckModel() {
    }


    public String getCheckvalue() {
        return checkvalue;
    }

    public void setCheckvalue(String checkvalue) {
        this.checkvalue = checkvalue;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}



