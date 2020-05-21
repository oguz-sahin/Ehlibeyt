package com.example.firstapplication.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.R
import kotlinx.android.synthetic.main.activity_kaza_detay.*
import kotlin.properties.Delegates

class kaza_detay : AppCompatActivity() {

    val gun: Int = 365
    lateinit var Pref: SharedPreferences
    var deger by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kaza_detay)


        var input = yil_et.text

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

            var intent = Intent(this, kaza_hesapla_activity::class.java)
            var gonderilenderger = intent.putExtra("deger", deger)
            startActivity(intent)

        }


    }


    fun hesaplanankaza(input: Double): Int {

        var hesaplanankaza = input * gun

        return hesaplanankaza.toInt()

    }
}
