package com.example.firstapplication.namazvaktiApi


import com.google.gson.annotations.SerializedName

data class VakitModelItem(
    @SerializedName("Aksam")
    var aksam: String,
    @SerializedName("AyinSekliURL")
    var ayinSekliURL: String,
    @SerializedName("Gunes")
    var gunes: String,
    @SerializedName("GunesBatis")
    var gunesBatis: String,
    @SerializedName("GunesDogus")
    var gunesDogus: String,
    @SerializedName("HicriTarihKisa")
    var hicriTarihKisa: String,
    @SerializedName("HicriTarihUzun")
    var hicriTarihUzun: String,
    @SerializedName("KibleSaati")
    var kibleSaati: String,
    @SerializedName("MiladiTarihKisa")
    var miladiTarihKisa: String,
    @SerializedName("MiladiTarihKisaIso8601")
    var miladiTarihKisaIso8601: String,
    @SerializedName("MiladiTarihUzun")
    var miladiTarihUzun: String,
    @SerializedName("MiladiTarihUzunIso8601")
    var miladiTarihUzunIso8601: String,
    @SerializedName("Ogle")
    var ogle: String,
    @SerializedName("Yatsi")
    var yatsi: String,
    @SerializedName("Ikindi")
    var ıkindi: String,
    @SerializedName("Imsak")
    var ımsak: String
)