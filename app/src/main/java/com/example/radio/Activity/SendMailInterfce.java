package com.example.radio.Activity;



import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SendMailInterfce {



    @FormUrlEncoded
    @POST("api/v1.0/email/send")
    @Headers({
            "origin: https://idea2codeinfotech.com/",
            "Content-Type: application/json"
    })
    Call<User> createUser(@Body JSONObject jsonObject);
}
