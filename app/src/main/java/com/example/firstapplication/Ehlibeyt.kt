package com.example.firstapplication

import android.app.Application
import com.facebook.stetho.Stetho

class Ehlibeyt : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}