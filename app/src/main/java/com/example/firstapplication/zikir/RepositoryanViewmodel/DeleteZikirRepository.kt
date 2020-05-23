package com.example.firstapplication.zikir.RepositoryanViewmodel

import android.content.Context
import android.os.AsyncTask
import com.example.firstapplication.zikir.data.Zikir
import com.example.firstapplication.zikir.data.ZikirDao
import com.example.firstapplication.zikir.data.ZikirDb

class DeleteZikirRepository(context: Context) {


    private val db by lazy { ZikirDb.getInstance(context) }
    private val dao: ZikirDao by lazy { db.ZikirDao() }

    fun deleteZikir(zikir: Zikir) {
        DeleteAsyncTask(dao).execute(zikir)
    }

    private class DeleteAsyncTask(val dao: ZikirDao) : AsyncTask<Zikir, Void, Void>() {
        override fun doInBackground(vararg params: Zikir?): Void? {
            dao.deleteZikir(params[0]!!)
            return null
        }


    }


}