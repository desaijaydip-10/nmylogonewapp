package com.example.radio.Model;

public class RegisterModel {




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
     String selected;
     String userid;
     String imgurl;
     String checked_status;
     String userlogin;
     boolean verifyCheck;


     public RegisterModel(String userid, String mName, String nEmail,
                          String password, String mPhoneNumer, String mdesignation, String mjointate, String mbirthdate, String mAddress, String mAdharcar, String mBloodgrp, String mCompanyemail,
                          String img_url, String checked_status,
                          String ischecked, boolean verifyCheck) {
          this.mName = mName;
          this.nEmail = nEmail;
          this.mpassword = password;
          this.mPhoneNumer = mPhoneNumer;
          this.mdesignation = mdesignation;
          this.mjointate = mjointate;
          this.mbirthdate = mbirthdate;
          this.mAddress = mAddress;
          this.mAdharcar = mAdharcar;
          this.mBloodgrp = mBloodgrp;
          this.mCompanyemail = mCompanyemail;

          this.imgurl = img_url;
          this.userid = userid;
          this.checked_status = checked_status;
          this.userlogin = ischecked;
          this.verifyCheck = verifyCheck;

     }


     public boolean isVerifyCheck() {
          return verifyCheck;
     }

     public void setVerifyCheck(boolean verifyCheck) {
          this.verifyCheck = verifyCheck;
     }

     public String getUserlogin() {
          return userlogin;
     }

     public void setUserlogin(String userlogin) {
          this.userlogin = userlogin;
     }



     public String getChecked_status()
     {
          return checked_status;
     }

     public void setChecked_status(String checked_status) {
          this.checked_status = checked_status;
     }



     public String getMselected() {
          return selected;
     }

     public void setMselected(String mselected) {

          this.selected = mselected;
     }

     public String getImgurl() {
          return imgurl;
     }

     public void setImgurl(String imgurl) {
          this.imgurl = imgurl;
     }

     public String getMpassword() {
          return mpassword;
     }

     public void setMpassword(String mpassword) {
          this.mpassword = mpassword;
     }


     public String getUserid() {
          return userid;
     }

     public void setUserid(String userid) {
          this.userid = userid;
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

     public void setnEmail(String nEmail) {
          this.nEmail = nEmail;
     }

     public String getmPhoneNumer() {
          return mPhoneNumer;
     }

     public void setmPhoneNumer(String mPhoneNumer) {
          this.mPhoneNumer = mPhoneNumer;
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





}




