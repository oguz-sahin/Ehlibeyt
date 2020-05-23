package com.example.firstapplication.zikir.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.firstapplication.R
import com.example.firstapplication.zikir.RepositoryanViewmodel.UpdateZikirViewModel
import com.example.firstapplication.zikir.data.Zikir
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_zikir_update.*
import kotlinx.android.synthetic.main.activity_zikir_update.view.*

class ZikirUpdateActivity : AppCompatActivity() {


    lateinit var ViewModel: UpdateZikirViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zikir_update)


        setSupportActionBar(zikir_update_Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        zikir_update_Toolbar.Toolbar_title_tv.text = "Zikri Güncelle"
        Toolbar_back_iv.setOnClickListener {

            startActivity(Intent(this, zikir_activity::class.java))

        }


        var zikir = intent.extras?.getSerializable("notify_zikir") as Zikir

        zikir_adi_tv.setText(zikir.name)
        bitis_sayisi_tv.setText(zikir.bitisSayisi.toString())
        zikir_meal_tv.setText(zikir.meal)
        zikir_arapca_tv.setText(zikir.arapca_okunusu)

        ViewModel = ViewModelProviders.of(this).get(UpdateZikirViewModel::class.java)
        update_btn.setOnClickListener {
            if (zikir_adi_tv.text.toString().trim().isEmpty()) {


                zikir_et_layout.error = "Lütfen Zikir adını giriniz"


            }

            if (bitis_sayisi_tv.text.toString().trim().isEmpty()) {

                bitis_sayisi_et_layout.error = "Lütfen bitiş sayısını giriniz"

            } else {


                val zikir_adi = zikir_adi_tv.text.toString()
                val bitis_sayisi = bitis_sayisi_tv.text.toString().toInt()
                val meali = zikir_meal_tv.text.toString()
                val arapcasi = zikir_arapca_tv.text.toString()

                ViewModel.update(
                    Zikir(
                        qid = zikir.qid,
                        name = zikir_adi,
                        bitisSayisi = bitis_sayisi,
                        meal = meali,
                        arapca_okunusu = arapcasi,
                        tur = zikir.tur,
                        kalinanzikirsayisi = zikir.kalinanzikirsayisi
                    )
                )


                val snackbar =
                    Snackbar.make(coordinator, "Güncelleme Başarılı", Snackbar.LENGTH_LONG).show()


                startActivity(Intent(this, zikir_activity::class.java))

            }


        }


    }
}
