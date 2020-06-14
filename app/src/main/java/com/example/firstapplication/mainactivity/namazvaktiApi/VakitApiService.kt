package com.example.firstapplication.mainactivity.namazvaktiApi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VakitApiService {

    @GET("namazsaati")
    suspend fun getVakitWithQuery(
        @Query("mehod") method: String,
        @Query("tarih") tarih: String,
        @Query("sehir") sehir: String,
        @Query("ulke") ulke: String,
        @Query("format") format: String
    ): Response<VakitModelItem>

}