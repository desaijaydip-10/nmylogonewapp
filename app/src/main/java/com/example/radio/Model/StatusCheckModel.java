package com.example.radio.Model;

public class StatusCheckModel {

    private boolean isChecked;
    private String Value;


    public StatusCheckModel(boolean isChecked, String value) {
        this.isChecked = isChecked;
        Value = value;
    }


    public StatusCheckModel() {

    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}



