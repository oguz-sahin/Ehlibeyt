package com.example.firstapplication.ui.zikir.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ZikirDao {

    @Query("SELECT * FROM zikir")
    fun getzikir(): LiveData<List<Zikir>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addZikir(zikir: Zikir)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateZikir(zikir: Zikir)

    @Delete
    fun deleteZikir(zikir: Zikir)

}