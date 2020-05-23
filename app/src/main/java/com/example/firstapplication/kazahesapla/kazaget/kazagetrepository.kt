package com.example.firstapplication.kazahesapla.kazaget

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.firstapplication.kazahesapla.data.Kaza
import com.example.firstapplication.kazahesapla.data.kazaDao
import com.example.firstapplication.kazahesapla.data.kazaDb

class kazagetrepository(context: Context) {

    private val db: kazaDb by lazy { kazaDb.getInstance(context) }
    private val dao: kazaDao by lazy { db.kazaDao() }

    fun getKaza(): LiveData<List<Kaza>> = dao.getKaza()
}