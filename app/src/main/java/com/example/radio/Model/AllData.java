package com.example.radio.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class AllData  implements Parcelable {


    //String  address,addharcard,bloog_grp,company_name,user_name,user_phonenumber,birthdate,designation,joindata,password,selected,user_email;


    String mName;
    String nEmail;
    String mPhoneNumer;
    String mpassword;
    String mdesignation;
    String mjointate;
    String mbirthdate;
    String mAddress;
    String mAdharcar;
    String mBloodgrp;
    String mCompanyemail;
    String mselected;
    String img_url;
    String userid;
    String checked_status;



    boolean verifyCheck;





    public AllData(String  userid, String mName, String nEmail, String mPhoneNumer, String mpassword, String mdesignation, String mjointate, String mbirthdate, String mAddress, String mAdharcar, String mBloodgrp,
                   String mCompanyemail, String mselected,
                   String  img_url,String checked_status,  boolean verifyCheck) {
        this.mName = mName;
        this.nEmail = nEmail;
        this.mPhoneNumer = mPhoneNumer;
        this.mpassword = mpassword;
        this.mdesignation = mdesignation;
        this.mjointate = mjointate;
        this.mbirthdate = mbirthdate;
        this.mAddress = mAddress;
        this.mAdharcar = mAdharcar;
        this.mBloodgrp = mBloodgrp;
        this.mCompanyemail = mCompanyemail;
        this.mselected = mselected;
        this.img_url = img_url;
        this.userid = img_url;
        this.checked_status = checked_status;
        this.verifyCheck = verifyCheck;

    }


    public AllData() {

    }


    protected AllData(Parcel in) {
        mName = in.readString();
        nEmail = in.readString();
        mPhoneNumer = in.readString();
        mpassword = in.readString();
        mdesignation = in.readString();
        mjointate = in.readString();
        mbirthdate = in.readString();
        mAddress = in.readString();
        mAdharcar = in.readString();
        mBloodgrp = in.readString();
        mCompanyemail = in.readString();
        mselected = in.readString();
        img_url = in.readString();
        userid = in.readString();
        checked_status = in.readString();
        verifyCheck = in.readByte() != 0;
    }

    public static final Creator<AllData> CREATOR = new Creator<AllData>() {
        @Override
        public AllData createFromParcel(Parcel in) {
            return new AllData(in);
        }

        @Override
        public AllData[] newArray(int size) {
            return new AllData[size];
        }
    };

    public boolean isVerifyCheck() {

        return verifyCheck;
    }

    public void setVerifyCheck(boolean verifyCheck) {
        this.verifyCheck = verifyCheck;
    }


    public String getChecked_status() {
        return checked_status;
    }

    public void setChecked_status(String checked_status) {
        this.checked_status = checked_status;
    }



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getmName() {

        return mName;
    }

    public void setmName(String mName) {

        this.mName = mName;
    }

    public String getnEmail() {
        return nEmail;
    }

    public void setnEmail(String nEmail)
    {
        this.nEmail = nEmail;
    }

    public String getmPhoneNumer() {
        return mPhoneNumer;
    }

    public void setmPhoneNumer(String mPhoneNumer) {
        this.mPhoneNumer = mPhoneNumer;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }

    public String getMdesignation() {
        return mdesignation;
    }

    public void setMdesignation(String mdesignation) {
        this.mdesignation = mdesignation;
    }

    public String getMjointate() {
        return mjointate;
    }

    public void setMjointate(String mjointate) {
        this.mjointate = mjointate;
    }

    public String getMbirthdate() {
        return mbirthdate;
    }

    public void setMbirthdate(String mbirthdate) {
        this.mbirthdate = mbirthdate;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmAdharcar() {
        return mAdharcar;
    }

    public void setmAdharcar(String mAdharcar) {
        this.mAdharcar = mAdharcar;
    }

    public String getmBloodgrp() {
        return mBloodgrp;
    }

    public void setmBloodgrp(String mBloodgrp) {
        this.mBloodgrp = mBloodgrp;
    }

    public String getmCompanyemail() {
        return mCompanyemail;
    }

    public void setmCompanyemail(String mCompanyemail) {
        this.mCompanyemail = mCompanyemail;
    }

    public String getMselected() {
        return mselected;
    }

    public void setMselected(String mselected) {
        this.mselected = mselected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(nEmail);
        dest.writeString(mPhoneNumer);
        dest.writeString(mpassword);
        dest.writeString(mdesignation);
        dest.writeString(mjointate);
        dest.writeString(mbirthdate);
        dest.writeString(mAddress);
        dest.writeString(mAdharcar);
        dest.writeString(mBloodgrp);
        dest.writeString(mCompanyemail);
        dest.writeString(mselected);
        dest.writeString(img_url);
        dest.writeString(userid);
        dest.writeString(checked_status);
        dest.writeByte((byte) (verifyCheck ? 1 : 0));
    }
}


