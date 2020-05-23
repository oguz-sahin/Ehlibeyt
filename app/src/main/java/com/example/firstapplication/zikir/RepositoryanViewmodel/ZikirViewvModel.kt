package com.example.firstapplication.zikir.RepositoryanViewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.firstapplication.zikir.data.Zikir

class ZikirViewvModel(application: Application) : AndroidViewModel(application) {

    private val Repository: ZikirRepository by lazy { ZikirRepository(application) }

    val allZikir: LiveData<List<Zikir>> by lazy { Repository.getAllZikir() }


}