package com.example.firstapplication.kazahesapla.kazaget

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.firstapplication.kazahesapla.data.Kaza

class kazagetviewmodel(application: Application) : AndroidViewModel(application) {

    private val getrepository: kazagetrepository by lazy { kazagetrepository(application) }

    val allKaza: LiveData<List<Kaza>> = getrepository.getKaza()


}