package com.example.firstapplication.kazahesapla.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Kaza::class), version = 1)

abstract class kazaDb : RoomDatabase() {
    abstract fun kazaDao(): kazaDao

    companion object {
        @Volatile
        var instance: kazaDb? = null

        @Synchronized
        fun getInstance(context: Context): kazaDb {
            if (instance == null) {

                instance =
                    Room.databaseBuilder(context.applicationContext, kazaDb::class.java, "kaza")
                        .addCallback(roomDbCallback())
                        .build()
            }

            return instance as kazaDb
        }

        private fun roomDbCallback() = object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { PopulateAsyncTask(it).execute() }
            }


        }

        class PopulateAsyncTask(private val db: kazaDb) : AsyncTask<Void, Void, Void>() {

            private val dao: kazaDao by lazy { db.kazaDao() }

            override fun doInBackground(vararg params: Void?): Void? {

                var kaza = Kaza(vakitName = "Sabah", kazaSayisi = 0)
                dao.addKaza(kaza)
                kaza = Kaza(vakitName = "Öğle", kazaSayisi = 0)
                dao.addKaza(kaza)
                kaza = Kaza(vakitName = "İkindi", kazaSayisi = 0)
                dao.addKaza(kaza)
                kaza = Kaza(vakitName = "Akşam", kazaSayisi = 0)
                dao.addKaza(kaza)
                kaza = Kaza(vakitName = "Yatsı", kazaSayisi = 0)
                dao.addKaza(kaza)
                kaza = Kaza(vakitName = "Vitr", kazaSayisi = 0)
                dao.addKaza(kaza)
                kaza = Kaza(vakitName = "Oruç Tutma", kazaSayisi = 0)
                dao.addKaza(kaza)

                return null
            }


        }

    }


}