package com.example.firstapplication.zikir.RepositoryanViewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.firstapplication.zikir.data.Zikir
import com.example.firstapplication.zikir.data.ZikirDao
import com.example.firstapplication.zikir.data.ZikirDb

class ZikirRepository(context: Context) {

    private val db: ZikirDb by lazy { ZikirDb.getInstance(context) }
    private val dao: ZikirDao by lazy { db.ZikirDao() }

    fun getAllZikir(): LiveData<List<Zikir>> = dao.getzikir()


}