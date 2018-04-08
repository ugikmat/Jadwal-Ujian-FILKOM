package com.example.lutluthfi.poroskotlinretrofit.network

import com.example.lutluthfi.poroskotlinretrofit.model.BeritaResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

    @GET("api/v1/berita")
    fun getBeritaByPage(@Query("page") page : Int) : Observable<BeritaResponse.Beritas>

    @GET("api/v1/berita/{id}")
    fun getBeritaByPath(@Path("id") id : Int) : Observable<BeritaResponse.Berita>
}