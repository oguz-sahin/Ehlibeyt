package com.example.firstapplication.mainactivity.namazvaktiApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object VakitApiClient {


    const val BASE_URL = "https://ezanvakti.herokuapp.com/"
    fun getApiClient(): VakitApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(VakitApiService::class.java)

    }


}