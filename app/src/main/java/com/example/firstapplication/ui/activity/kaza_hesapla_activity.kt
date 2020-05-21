package com.example.firstapplication.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.adapter.KazaHesaplaAdapter
import com.example.firstapplication.model.KazaModel
import com.example.firstapplication.onClickInterface.CallbackDialog
import com.example.firstapplication.onClickInterface.OnKazaItemClickListener
import com.example.firstapplication.onClickInterface.kazaHesaplaDialogClass
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_kaza_hesapla_activity.*
import kotlinx.android.synthetic.main.activity_kaza_hesapla_activity.view.*
import java.util.*

class kaza_hesapla_activity : AppCompatActivity(), OnKazaItemClickListener, CallbackDialog {


    var vakitArray = ArrayList<KazaModel>()
    var vakitValue = arrayListOf<Int>(0, 0, 0, 0, 0, 0, 0)


    lateinit var Pref: SharedPreferences

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kaza_hesapla_activity)



        setSupportActionBar(kaza_hesapla_Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        kaza_hesapla_Toolbar.Toolbar_title_tv.text = "Takvim Yaprağı"

        Toolbar_back_iv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Pref = getSharedPreferences("Kaza Sayıları", Context.MODE_PRIVATE)

        recyclerView = findViewById(R.id.rv_kaza_hesapla)

        recyclerView.layoutManager = LinearLayoutManager(this@kaza_hesapla_activity)
        recyclerView.adapter =
            KazaHesaplaAdapter(this@kaza_hesapla_activity, vakitArray, this, this)


        addArray()

        val olan_sabah_kaza_sayisi = Pref.getInt("Sabah", 0)
        val olan_ogle_kaza_sayisi = Pref.getInt("Öğle", 0)
        val olan_ikindi_kaza_sayisi = Pref.getInt("İkindi", 0)
        val olan_aksam_kaza_sayisi = Pref.getInt("Akşam", 0)
        val olan_yatsi_kaza_sayisi = Pref.getInt("Yatsı", 0)
        val olan_vitr_kaza_sayisi = Pref.getInt("Vitr", 0)

        val gelendeger = intent.getIntExtra("deger", 0)
        val editorsabah = Pref.edit()

        editorsabah.apply {

            putInt("Sabah", gelendeger + olan_sabah_kaza_sayisi)
            putInt("Öğle", gelendeger + olan_ogle_kaza_sayisi)
            putInt("İkindi", gelendeger + olan_ikindi_kaza_sayisi)
            putInt("Akşam", gelendeger + olan_aksam_kaza_sayisi)
            putInt("Yatsı", gelendeger + olan_yatsi_kaza_sayisi)
            putInt("Vitr", gelendeger + olan_vitr_kaza_sayisi)

            apply()

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        val inflater = menuInflater.inflate(R.menu.kaza_hesapla_menu, menu)



        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {


            R.id.sifirla -> {


                val builder = MaterialAlertDialogBuilder(this)
                builder.setMessage("Sıfırlamak istediğinize emin misiniz?")
                    .setTitle("Sıfırla")
                    .setIcon(R.drawable.ic_refresh_black_24dp)
                    .setBackground(resources.getDrawable(R.drawable.alert_shape, null))
                    .setNegativeButton("Hayır", { dialog, which ->


                        dialog.dismiss()


                    })

                    .setPositiveButton("Evet", { dialog, which ->


                        var editorsabah = Pref.edit()

                        editorsabah.apply {

                            putInt("Sabah", 0)
                            putInt("Öğle", 0)
                            putInt("İkindi", 0)
                            putInt("Akşam", 0)
                            putInt("Yatsı", 0)
                            putInt("Vitr", 0)
                            putInt("Oruç Tutma", 0)
                            apply()

                        }

                        recyclerView.adapter?.notifyDataSetChanged()
                    })
                val dialog = builder.create()
                dialog.show()



                true
            }


            R.id.hesapla -> {

                var intent = Intent(this, kaza_detay::class.java)
                startActivity(intent)
                true
            }


            else -> return super.onOptionsItemSelected(item)
        }

    }


    fun addArray() {


        vakitArray.add(KazaModel("Sabah", vakitValue[0], Pref))
        vakitArray.add(KazaModel("Öğle", vakitValue[1], Pref))
        vakitArray.add(KazaModel("İkindi", vakitValue[2], Pref))
        vakitArray.add(KazaModel("Akşam", vakitValue[3], Pref))
        vakitArray.add(KazaModel("Yatsı", vakitValue[4], Pref))
        vakitArray.add(KazaModel("Vitr", vakitValue[5], Pref))
        vakitArray.add(KazaModel("Oruç Tutma", vakitValue[6], Pref))


    }


    override fun onCallbackDialog() {
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun kazaitemclick(gelenKazaModel: KazaModel, CallbackDialog: CallbackDialog) {
        var kazaHesaplaDialog: kazaHesaplaDialogClass =
            kazaHesaplaDialogClass(gelenKazaModel, CallbackDialog)
        kazaHesaplaDialog.show(supportFragmentManager, "dialog")


    }


}
