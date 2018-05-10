package com.example.lutluthfi.jadwalujianfilkom.network;

import android.database.Observable;

import com.example.lutluthfi.jadwalujianfilkom.model.JadwalResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Service {

    /*
    SIAM Service
     */
    //get cookie
    @GET("index.php")
    Call<String> getCookie();

    //authenticate cookie with username and password
    @Multipart
    @POST("index.php")
    Call<String> login(@Header("Cookie") String cookie,
                       @Part("username") RequestBody username,
                       @Part("password") RequestBody password,
                       @Part("login") RequestBody login);

    //get jadwal with authenticated cookie
    @GET("class.php")
    Call<String> jadwal(@Header("Cookie") String cookie);

    @GET("jadwal.JSON")
    Call<JadwalResponse> getJadwalUAS();
}
