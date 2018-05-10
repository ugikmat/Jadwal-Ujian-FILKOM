package com.example.lutluthfi.jadwalujianfilkom.network

import com.example.lutluthfi.jadwalujianfilkom.model.BeritaResponse
import com.example.lutluthfi.jadwalujianfilkom.model.JadwalResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiHelper {

    @GET("api/v1/berita")
    fun getBeritaByPage(@Query("page") page : Int) : Observable<BeritaResponse.Beritas>

    @GET("api/v1/berita/{id}")
    fun getBeritaByPath(@Path("id") id : Int) : Observable<BeritaResponse.Berita>

    @GET("jadwal.JSON")
    fun getJadwalDosen() : Observable<JadwalResponse>

        /*
    SIAM Service
     */
    //get cookie
    @GET("index.php")
    fun getCookie() : Observable<String>

    //authenticate cookie with username and password
    @Multipart
    @POST("index.php")
    fun login(@Header("Cookie") cookie : String,
              @Part("username") username : RequestBody,
              @Part("password") password: RequestBody ,
              @Part("login") login : RequestBody ) : Observable<String>

    //get jadwal with authenticated cookie
    @GET("class.php")
    fun jadwal(@Header("Cookie") cookie : String ) : Observable<String>;
}