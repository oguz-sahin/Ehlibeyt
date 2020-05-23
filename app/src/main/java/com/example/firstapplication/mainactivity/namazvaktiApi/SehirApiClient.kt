package com.example.firstapplication.mainactivity.namazvaktiApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SehirApiClient {
    const val BASE_URL = "https://ezanvakti.herokuapp.com/"
    fun getApiClient(): SehirApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(SehirApiService::class.java)

    }


}