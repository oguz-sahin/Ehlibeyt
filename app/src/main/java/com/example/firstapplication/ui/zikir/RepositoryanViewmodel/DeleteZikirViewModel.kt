package com.example.firstapplication.ui.zikir.RepositoryanViewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firstapplication.ui.zikir.data.Zikir

class DeleteZikirViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DeleteZikirRepository by lazy { DeleteZikirRepository(application) }


    fun delete(zikir: Zikir) = repository.deleteZikir(zikir)


}