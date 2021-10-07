package com.example.radio.Interface;

import com.example.radio.Activity.Response;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RetrofitApi {


    @POST("/api/v1.0/email/send")

    Call<Response> SendData();


}
