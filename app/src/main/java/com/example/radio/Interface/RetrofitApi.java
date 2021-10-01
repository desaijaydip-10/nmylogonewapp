package com.example.radio.Interface;

import com.example.radio.Activity.Response;
import com.example.radio.Activity.SubModel;
import com.example.radio.Activity.TemplateParams;
import com.example.radio.FirstClass;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitApi {


    @POST("/api/v1.0/email/send")

    Call<Response> SendData();


}
