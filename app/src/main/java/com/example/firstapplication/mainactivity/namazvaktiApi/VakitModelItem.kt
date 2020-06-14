package com.example.firstapplication.mainactivity.namazvaktiApi


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VakitModelItem(
    @SerializedName("Aksam")
    var aksam: String,
    @SerializedName("Gun")
    var gun: String,
    @SerializedName("Gunes")
    var gunes: String,
    @SerializedName("Kible")
    var kible: String,
    @SerializedName("KibleSaati")
    var kibleSaati: String,
    @SerializedName("MiladiTarihUzunIso8601")
    var miladiTarihUzunIso8601: String,
    @SerializedName("Ogle")
    var ogle: String,
    @SerializedName("Oglen")
    var oglen: String,
    @SerializedName("Yatsi")
    var yatsi: String,
    @SerializedName("Ikindi")
    var ıkindi: String,
    @SerializedName("Imsak")
    var ımsak: String

): Serializable
