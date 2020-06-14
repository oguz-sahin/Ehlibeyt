package com.example.firstapplication.dinigunler.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.dinigunler.adapter.DiniGunAdapter
import com.example.firstapplication.dinigunler.model.DiniGunModel
import com.example.firstapplication.dinigunler.utill.onDiniDaysClickListener
import java.util.*

class dini_days_activity : AppCompatActivity(),
    onDiniDaysClickListener {

    private var dini_days_array = ArrayList<DiniGunModel>()
    private var a: DiniGunModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dini_days_activity)


        add_diniGun()
        enYakınGün()
        val position = dini_days_array.indexOf(a)
        val rv = findViewById<RecyclerView>(R.id.dini_days_recyclerview)

        val layoutManager = LinearLayoutManager(this)
        rv.layoutManager = layoutManager
        layoutManager.scrollToPosition(position + 1)


        rv.adapter =
            DiniGunAdapter(
                this,
                dini_days_array,
                this
            )


    }


    private fun add_diniGun() {
        dini_days_array.add(DiniGunModel("25", 2, "Şubat", 2, "Üç Ayların Başlangıcı"))
        dini_days_array.add(DiniGunModel("27", 27, "Şubat", 2, "Regaib Kandili"))
        dini_days_array.add(DiniGunModel("21", 21, "Mart", 3, "Miraç Kanidili"))
        dini_days_array.add(DiniGunModel("07", 7, "Nisan", 4, "Berat Kandili"))
        dini_days_array.add(DiniGunModel("19", 19, "Mayıs", 5, "Kadir Gecesi"))
        dini_days_array.add(DiniGunModel("23", 23, "Mayıs", 5, "Arefe"))
        dini_days_array.add(DiniGunModel("24", 24, "Mayıs", 5, "Ramazan Bayramı(1.Gün)"))
        dini_days_array.add(DiniGunModel("25", 25, "Mayıs", 5, "Ramazan Bayramı(2.Gün)"))
        dini_days_array.add(DiniGunModel("26", 26, "Mayıs", 5, "Ramazan Bayramı(3.Gün)"))
        dini_days_array.add(DiniGunModel("30", 30, "Temmuz", 7, "Arefe"))
        dini_days_array.add(DiniGunModel("31", 31, "Temmuz", 7, "Kurban Bayramı(1.Gün)"))
        dini_days_array.add(DiniGunModel("01", 1, "Ağustos", 7, "Kurban Bayramı(2.Gün)"))
        dini_days_array.add(DiniGunModel("02", 2, "Ağustos", 7, "Kurban Bayramı(3.Gün)"))
        dini_days_array.add(DiniGunModel("03", 3, "Ağustos", 7, "Kurban Bayramı(4.Gün)"))
        dini_days_array.add(DiniGunModel("20", 20, "Ağustos", 8, "Hicri Yılbaşı"))
        dini_days_array.add(DiniGunModel("29", 29, "Ağustos", 8, "Aşure Günü"))
        dini_days_array.add(DiniGunModel("28", 28, "Ekim", 10, "Mevlid Kandili"))


    }

    private fun enYakınGün() {
        var temp = 12
        var temp2 = 31
        val month = Calendar.getInstance().get(Calendar.MONTH) + 1
        Log.e("ay", month.toString())
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        for (i in 0.until(dini_days_array.size)) {

            if (dini_days_array[i].ayInt >= month) {
                if (dini_days_array[i].tarihInt >= day) {
                    val ayFark = dini_days_array[i].ayInt - month
                    if (temp > ayFark) {
                        temp = ayFark
                        val gunFark = dini_days_array[i].tarihInt - day
                        if (temp2 > gunFark) {
                            temp2 = gunFark
                            a = dini_days_array[i]
                        }
                    }
                }

            }

        }

    }


    override fun ondDiniDaysClick(DiniDays: DiniGunModel) {
        val intent = Intent(this, dini_days_detay_avtivity::class.java)
        intent.putExtra(
            "diniGün",
            "${DiniDays.ozel_gun} texti"
        )
        startActivity(intent)
    }


}


