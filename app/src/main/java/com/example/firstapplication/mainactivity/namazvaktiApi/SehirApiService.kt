package com.example.firstapplication.mainactivity.namazvaktiApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SehirApiService {


    @GET("sehirler")
    fun getSehirWithQuery(@Query("ulke") ulke: String): Call<SehirModel>


}