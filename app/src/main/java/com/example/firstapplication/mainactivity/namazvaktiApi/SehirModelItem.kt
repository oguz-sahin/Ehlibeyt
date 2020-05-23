package com.example.firstapplication.mainactivity.namazvaktiApi


import com.google.gson.annotations.SerializedName

data class SehirModelItem(
    @SerializedName("SehirAdi")
    var sehirAdi: String,
    @SerializedName("SehirAdiEn")
    var sehirAdiEn: String,
    @SerializedName("SehirID")
    var sehirID: String
)