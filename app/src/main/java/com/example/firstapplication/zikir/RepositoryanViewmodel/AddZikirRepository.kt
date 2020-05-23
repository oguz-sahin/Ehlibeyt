package com.example.firstapplication.zikir.RepositoryanViewmodel

import android.content.Context
import android.os.AsyncTask
import com.example.firstapplication.zikir.data.Zikir
import com.example.firstapplication.zikir.data.ZikirDao
import com.example.firstapplication.zikir.data.ZikirDb

class AddZikirRepository(context: Context) {


    private val db by lazy { ZikirDb.getInstance(context) }
    private val dao: ZikirDao by lazy { db.ZikirDao() }

    fun insertZikir(zikir: Zikir) {
        InsertAsyncTask(dao).execute(zikir)
    }

    private class InsertAsyncTask(val dao: ZikirDao) : AsyncTask<Zikir, Void, Void>() {
        override fun doInBackground(vararg params: Zikir?): Void? {
            dao.addZikir(params[0]!!)
            return null
        }


    }
}