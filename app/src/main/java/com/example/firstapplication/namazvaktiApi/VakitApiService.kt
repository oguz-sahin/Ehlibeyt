package com.example.firstapplication.namazvaktiApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VakitApiService {

    @GET("vakitler")
    fun getVakitWithQuery(@Query("ilce") ilce: String): Call<VakitModel>

}