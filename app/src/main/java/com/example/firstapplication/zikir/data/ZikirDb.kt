package com.example.firstapplication.zikir.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = arrayOf(Zikir::class), version = 1)

abstract class ZikirDb : RoomDatabase() {

    abstract fun ZikirDao(): ZikirDao

    companion object {
        @Volatile
        var Instance: ZikirDb? = null

        @Synchronized
        fun getInstance(context: Context): ZikirDb {
            if (Instance == null) {

                Instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZikirDb::class.java,
                    "zikir_db"
                )
                    .addCallback(roomDbCallback)
                    .build()
            }
            return Instance as ZikirDb
        }

        private val roomDbCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsyncTask(Instance).execute()
            }
        }

        class PopulateAsyncTask(private val db: ZikirDb?) : AsyncTask<Void, Void, Void>() {

            private val dao: ZikirDao? by lazy { db?.ZikirDao() }

            override fun doInBackground(vararg params: Void?): Void? {
                var zikir = Zikir(

                    name = "Allahumme salli ala muhammed",
                    bitisSayisi = 20,
                    meal = "alah birdir muhammet onun kulu ve elçisidir",
                    arapca_okunusu = "اللهم صلى على سيدنا محمد"


                )
                dao?.addZikir(zikir)
                zikir = Zikir(

                    name = "Bismillahi Subhanallahi ve Bihamdihi",
                    bitisSayisi = 25,
                    meal = "Ben, Allah’ı ulûhiyet makamına yakışmayan sıfatlardan tenzih eder ve O’na hamd ederim",
                    arapca_okunusu = "اللهم صلى على سيدنا محمد"


                )
                dao?.addZikir(zikir)
                zikir = Zikir(

                    name = " Lâ havle ve lâ kuvvete illâ billâh.",
                    bitisSayisi = 40,
                    meal = "Günahlardan korunmaya güç yetirmek ve taate kuvvet bulmak, ancak Allah’ın tevfik ve yardımıyladır",
                    arapca_okunusu = "اللهم صلى على سيدنا محمد",
                    kalinanzikirsayisi = 21


                )
                dao?.addZikir(zikir)

                return null
            }

        }


    }


}

