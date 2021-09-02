package com.example.radio.Model;

public class CheckModel {
    private boolean isChecked;
    private String Value;

    public CheckModel(boolean isChecked, String value) {
        this.isChecked = isChecked;
        Value = value;
    }

    public CheckModel() {
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
