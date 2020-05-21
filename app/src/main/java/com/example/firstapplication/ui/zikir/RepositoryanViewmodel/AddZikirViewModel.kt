package com.example.firstapplication.ui.zikir.RepositoryanViewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firstapplication.ui.zikir.data.Zikir

class AddZikirViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AddZikirRepository by lazy { AddZikirRepository(application) }


    fun insert(zikir: Zikir) = repository.insertZikir(zikir)


}