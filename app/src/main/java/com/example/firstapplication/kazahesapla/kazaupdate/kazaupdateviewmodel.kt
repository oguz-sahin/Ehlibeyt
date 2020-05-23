package com.example.firstapplication.kazahesapla.kazaupdate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firstapplication.kazahesapla.data.Kaza

class kazaupdateviewmodel(application: Application) : AndroidViewModel(application) {

    private val updaterepository: kazaupdaterepository by lazy { kazaupdaterepository(application) }

    fun updateKaza(kaza: Kaza) = updaterepository.updateKaza(kaza)

}