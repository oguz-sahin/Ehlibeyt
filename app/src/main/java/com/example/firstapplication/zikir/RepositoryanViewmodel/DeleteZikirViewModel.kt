package com.example.firstapplication.zikir.RepositoryanViewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firstapplication.zikir.data.Zikir

class DeleteZikirViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DeleteZikirRepository by lazy { DeleteZikirRepository(application) }


    fun delete(zikir: Zikir) = repository.deleteZikir(zikir)


}