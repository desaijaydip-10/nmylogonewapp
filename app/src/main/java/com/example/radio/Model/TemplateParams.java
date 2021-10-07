
package com.example.radio.Model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TemplateParams {

    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_subject")
    @Expose
    private String userSubject;
    @SerializedName("to_email")
    @Expose
    private String toEmail;
    @SerializedName("user_message")
    @Expose
    private String userMessage;
    @SerializedName("to_name")
    @Expose
    private String toName;


    public TemplateParams(String userName, String userEmail, String userSubject, String toEmail, String userMessage, String toName) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userSubject = userSubject;
        this.toEmail = toEmail;
        this.userMessage = userMessage;
        this.toName = toName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSubject() {
        return userSubject;
    }

    public void setUserSubject(String userSubject) {
        this.userSubject = userSubject;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

}
