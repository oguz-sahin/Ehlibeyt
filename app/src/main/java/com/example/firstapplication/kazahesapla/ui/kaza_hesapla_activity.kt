package com.example.firstapplication.kazahesapla.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.kazahesapla.adapter.KazaHesaplaAdapter
import com.example.firstapplication.kazahesapla.data.Kaza
import com.example.firstapplication.kazahesapla.kazaget.kazagetviewmodel
import com.example.firstapplication.kazahesapla.kazaupdate.kazaupdateviewmodel
import com.example.firstapplication.kazahesapla.utill.OnKazaItemClickListener
import com.example.firstapplication.kazahesapla.utill.kazaHesaplaDialogClass
import com.example.firstapplication.mainactivity.ui.activity.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_kaza_hesapla_activity.*
import kotlinx.android.synthetic.main.activity_kaza_hesapla_activity.view.*

class kaza_hesapla_activity : AppCompatActivity(),
    OnKazaItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var kazagetviewmodel: kazagetviewmodel
    private lateinit var kazaupdateviewmodel: kazaupdateviewmodel

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

        recyclerView = findViewById(R.id.rv_kaza_hesapla)

        kazagetviewmodel = ViewModelProviders.of(this)
            .get(com.example.firstapplication.kazahesapla.kazaget.kazagetviewmodel::class.java)
        kazagetviewmodel.allKaza.observe(this, androidx.lifecycle.Observer {

            recyclerView.layoutManager = LinearLayoutManager(this@kaza_hesapla_activity)
            recyclerView.adapter =
                KazaHesaplaAdapter(
                    this@kaza_hesapla_activity,
                    it,
                    this
                )


        })


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

                        kazaupdateviewmodel = ViewModelProviders.of(this)
                            .get(com.example.firstapplication.kazahesapla.kazaupdate.kazaupdateviewmodel::class.java)

                        kazaupdateviewmodel.updateKaza(
                            Kaza(
                                qid = 1,
                                kazaSayisi = 0,
                                vakitName = "Sabah"
                            )
                        )
                        kazaupdateviewmodel.updateKaza(
                            Kaza(
                                qid = 2,
                                kazaSayisi = 0,
                                vakitName = "Öğle"
                            )
                        )
                        kazaupdateviewmodel.updateKaza(
                            Kaza(
                                qid = 3,
                                kazaSayisi = 0,
                                vakitName = "İkindi"
                            )
                        )
                        kazaupdateviewmodel.updateKaza(
                            Kaza(
                                qid = 4,
                                kazaSayisi = 0,
                                vakitName = "Akşam"
                            )
                        )
                        kazaupdateviewmodel.updateKaza(
                            Kaza(
                                qid = 5,
                                kazaSayisi = 0,
                                vakitName = "Yatsı"
                            )
                        )
                        kazaupdateviewmodel.updateKaza(
                            Kaza(
                                qid = 6,
                                kazaSayisi = 0,
                                vakitName = "Vitr"
                            )
                        )
                        kazaupdateviewmodel.updateKaza(
                            Kaza(
                                qid = 7,
                                kazaSayisi = 0,
                                vakitName = "Oruç Tutma"
                            )
                        )


                    })
                val dialog = builder.create()
                dialog.show()



                true
            }


            R.id.hesapla -> {

                val intent = Intent(this, kaza_detay::class.java)
                startActivity(intent)
                true
            }


            else -> return super.onOptionsItemSelected(item)
        }

    }


    override fun kazaitemclick(gelenKazaModel: Kaza) {
        val kazaHesaplaDialog = kazaHesaplaDialogClass(gelenKazaModel)
        kazaHesaplaDialog.show(supportFragmentManager, "dialog")


    }


}
