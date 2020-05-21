package com.example.firstapplication.ui.zikir.RepositoryanViewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firstapplication.ui.zikir.data.Zikir

class UpdateZikirViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: UpdateZikirRepository by lazy { UpdateZikirRepository(application) }


    fun update(zikir: Zikir) = repository.updateZikir(zikir)


}