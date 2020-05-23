package com.example.firstapplication.kazahesapla.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface kazaDao {
    @Query("SELECT * FROM kazahesapla")
    fun getKaza(): LiveData<List<Kaza>>

    @Insert
    fun addKaza(kaza: Kaza)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateKaza(kaza: Kaza)
}