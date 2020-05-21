package com.example.firstapplication.ui.zikir.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.firstapplication.R
import com.example.firstapplication.ui.zikir.RepositoryanViewmodel.AddZikirViewModel
import com.example.firstapplication.ui.zikir.data.Zikir
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_zikir_ekle_activity.*
import kotlinx.android.synthetic.main.activity_zikir_ekle_activity.view.*

class zikir_ekle_activity : AppCompatActivity() {

    lateinit var ViewModel: AddZikirViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zikir_ekle_activity)


        setSupportActionBar(zikir_ekle_Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        zikir_ekle_Toolbar.Toolbar_title_tv.text = "Zikir Ekle"


        zikir_ekle_Toolbar.Toolbar_back_iv.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    zikir_activity::class.java
                )
            )


        }


        ekle_btn.setOnClickListener {

            if (zikir_adi_tv.text.toString().trim().isEmpty()) {


                zikir_et_layout.error = "Lütfen Zikir adını giriniz"


            }

            if (bitis_sayisi_tv.text.toString().trim().isEmpty()) {

                bitis_sayisi_et_layout.error = "Lütfen bitiş sayısını giriniz"

            } else {

                ViewModel = ViewModelProviders.of(this).get(AddZikirViewModel::class.java)

                var zikir_adi = zikir_adi_tv.text.toString()
                var bitis_sayisi = bitis_sayisi_tv.text.toString().toInt()
                var meali = zikir_meal_tv.text.toString()
                var arapcasi = zikir_arapca_tv.text.toString()

                ViewModel.insert(
                    Zikir(
                        name = zikir_adi,
                        bitisSayisi = bitis_sayisi,
                        meal = meali,
                        arapca_okunusu = arapcasi
                    )
                )


                val snackbar =
                    Snackbar.make(coordinator, "Ekleme Başarılı", Snackbar.LENGTH_LONG).show()


                startActivity(Intent(this, zikir_activity::class.java))

            }


        }


    }
}
