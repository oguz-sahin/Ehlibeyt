package com.example.firstapplication.kazahesapla.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "kazahesapla")
data class Kaza(
    @PrimaryKey(autoGenerate = true)
    var qid: Int = 0,
    @ColumnInfo(name = "vakitName")
    var vakitName: String,
    @ColumnInfo(name = "kazaSayısı")
    var kazaSayisi: Int = 0

)