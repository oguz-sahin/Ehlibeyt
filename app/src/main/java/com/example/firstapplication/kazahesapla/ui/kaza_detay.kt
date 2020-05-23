package com.example.firstapplication.kazahesapla.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.firstapplication.R
import com.example.firstapplication.kazahesapla.data.Kaza
import com.example.firstapplication.kazahesapla.kazaget.kazagetviewmodel
import com.example.firstapplication.kazahesapla.kazaupdate.kazaupdateviewmodel
import kotlinx.android.synthetic.main.activity_kaza_detay.*

class kaza_detay : AppCompatActivity() {

    val gun: Int = 365
    private var sabahKazaSayisi: Int = 0
    private var ogleKazaSayisi: Int = 0
    private var ikindiKazaSayisi: Int = 0
    private var aksamKazaSayisi: Int = 0
    private var yatsiKazaSayisi: Int = 0
    private var vitrKazaSayisi: Int = 0
    private var orucKazaSayisi: Int = 0
    private var deger: Int = 0
    private lateinit var updateViewModel: kazaupdateviewmodel
    private lateinit var getViewModel: kazagetviewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kaza_detay)


        val input = yil_et.text

        getViewModel = ViewModelProviders.of(this).get(kazagetviewmodel::class.java)
        getViewModel.allKaza.observe(this, Observer {

            sabahKazaSayisi = it[0].kazaSayisi
            ogleKazaSayisi = it[1].kazaSayisi
            ikindiKazaSayisi = it[2].kazaSayisi
            aksamKazaSayisi = it[3].kazaSayisi
            yatsiKazaSayisi = it[4].kazaSayisi
            vitrKazaSayisi = it[5].kazaSayisi
            orucKazaSayisi = it[6].kazaSayisi


        })

        hesapla_btn.setOnClickListener {

            deger = hesaplanankaza(input.toString().toDouble())
            sabah_value_tv.text = deger.toString()
            ogle_value_tv.text = deger.toString()
            ikindi_value_tv.text = deger.toString()
            aksam_value_tv.text = deger.toString()
            yatsi_value_tv.text = deger.toString()
            vitr_value_tv.text = deger.toString()

        }

        aktar_btn.setOnClickListener {

            val intent = Intent(this, kaza_hesapla_activity::class.java)
            updateViewModel = ViewModelProviders.of(this).get(kazaupdateviewmodel::class.java)
            updateViewModel.updateKaza(
                Kaza(
                    qid = 1,
                    vakitName = "Sabah",
                    kazaSayisi = deger + sabahKazaSayisi
                )
            )
            updateViewModel.updateKaza(
                Kaza(
                    qid = 2,
                    vakitName = "Öğle",
                    kazaSayisi = deger + ogleKazaSayisi
                )
            )
            updateViewModel.updateKaza(
                Kaza(
                    qid = 3,
                    vakitName = "İkindi",
                    kazaSayisi = deger + ikindiKazaSayisi
                )
            )
            updateViewModel.updateKaza(
                Kaza(
                    qid = 4,
                    vakitName = "Akşam",
                    kazaSayisi = deger + aksamKazaSayisi
                )
            )
            updateViewModel.updateKaza(
                Kaza(
                    qid = 5,
                    vakitName = "Yatsı",
                    kazaSayisi = deger + yatsiKazaSayisi
                )
            )
            updateViewModel.updateKaza(
                Kaza(
                    qid = 6,
                    vakitName = "Vitr",
                    kazaSayisi = deger + vitrKazaSayisi
                )
            )
            updateViewModel.updateKaza(
                Kaza(
                    qid = 7,
                    vakitName = "Oruç Tutma",
                    kazaSayisi = deger + orucKazaSayisi
                )
            )
            startActivity(intent)

        }


    }


    private fun hesaplanankaza(input: Double): Int {

        val hesaplanankaza = input * gun

        return hesaplanankaza.toInt()

    }


}
