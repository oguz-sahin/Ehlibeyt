package com.example.firstapplication.ui.zikir.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "zikir")
data class Zikir(


    @PrimaryKey(autoGenerate = true)
    var qid: Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo
    var kalinanzikirsayisi: Int = 0,
    @ColumnInfo(name = "bitis_sayisi")
    var bitisSayisi: Int,
    @ColumnInfo(name = "meal")
    var meal: String,
    @ColumnInfo(name = "arapca oknusu")
    var arapca_okunusu: String,
    @ColumnInfo(name = "tur")
    var tur: Int = 0

) : Serializable





