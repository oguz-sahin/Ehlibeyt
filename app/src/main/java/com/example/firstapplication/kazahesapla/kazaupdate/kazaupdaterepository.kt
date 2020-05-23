package com.example.firstapplication.kazahesapla.kazaupdate

import android.content.Context
import android.os.AsyncTask
import com.example.firstapplication.kazahesapla.data.Kaza
import com.example.firstapplication.kazahesapla.data.kazaDao
import com.example.firstapplication.kazahesapla.data.kazaDb

class kazaupdaterepository(context: Context) {

    private val db: kazaDb by lazy { kazaDb.getInstance(context) }
    private val dao: kazaDao by lazy { db.kazaDao() }

    fun updateKaza(kaza: Kaza) {
        UpdataeAysncTask(dao).execute(kaza)
    }

    private class UpdataeAysncTask(var dao: kazaDao) : AsyncTask<Kaza, Void, Void>() {

        override fun doInBackground(vararg params: Kaza?): Void? {

            dao.updateKaza(params[0]!!)
            return null
        }


    }


}