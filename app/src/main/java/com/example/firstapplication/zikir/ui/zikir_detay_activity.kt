package com.example.firstapplication.zikir.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.firstapplication.R
import com.example.firstapplication.zikir.RepositoryanViewmodel.UpdateZikirViewModel
import com.example.firstapplication.zikir.data.Zikir
import com.example.firstapplication.zikir.utill.TabAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_zikir_detay_activity.*

class zikir_detay_activity : AppCompatActivity() {


    lateinit var UpdateViewModel: UpdateZikirViewModel
    lateinit var zikir: Zikir
    var kalinan_zikir: Float = 0f
    var tur_sayisi = 0
    var bitis_sayisi: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zikir_detay_activity)



        setSupportActionBar(zikir_detay_Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        zikir_detay_Toolbar.title = "Zikir Detayı"
        Toolbar_back_iv.setOnClickListener {
            val intent = startActivity(Intent(this, zikir_activity::class.java))

        }


        UpdateViewModel = ViewModelProviders.of(this).get(UpdateZikirViewModel::class.java)

        zikir = intent.extras!!.getSerializable("zikir") as Zikir


        val adapter: TabAdapter = TabAdapter(supportFragmentManager)
        adapter.apply {

            addFragment(ZikirOkunusFragment(zikir), "Okunuşu")
            addFragment(mealFragment(zikir), "Meal")
            addFragment(arapcaFragment(zikir), "Arapça")


        }

        zikir_viewpager.adapter = adapter
        zikir_tab_layout.setupWithViewPager(zikir_viewpager)


        kalinan_zikir = zikir.kalinanzikirsayisi.toFloat()
        bitis_sayisi = zikir.bitisSayisi.toFloat()
        tur_sayisi = zikir.tur


        zikir_sayi_tv.text = kalinan_zikir.toInt().toString()
        zikir_tur_tv.text = tur_sayisi.toString()


        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {

            progressMax = bitis_sayisi

            setProgressWithAnimation(kalinan_zikir, 1000)
            // Set ProgressBar Color
            progressBarColor = applicationContext.getColor(android.R.color.black)

            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set background ProgressBar Color
            backgroundProgressBarColor = applicationContext.getColor(android.R.color.darker_gray)
            // or with gradient

            backgroundProgressBarColorDirection =
                CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set Width
            progressBarWidth = 20f // in DP
            backgroundProgressBarWidth = 20f // in DP

            // Other
            roundBorder = true
            startAngle = 180f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }


        circularProgressBar.setOnClickListener {

            kalinan_zikir++
            zikir_sayi_tv.text = kalinan_zikir.toInt().toString()
            circularProgressBar.setProgressWithAnimation(kalinan_zikir, 100)
            vibratePhone(this)

            // circularProgressBar.progressDirection = CircularProgressBar.ProgressDirection.TO_LEFT
            //  circularProgressBar.progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
            if (kalinan_zikir == bitis_sayisi) {

                tur_sayisi++
                zikir_tur_tv.text = tur_sayisi.toString()
                kalinan_zikir = 0f


            }


            val updatebitissayisi = bitis_sayisi.toInt()

            val updatezikirsayisi = kalinan_zikir.toInt()

            val updatetursayisi = tur_sayisi


            UpdateViewModel.update(
                Zikir(
                    qid = zikir.qid,
                    name = zikir.name,
                    kalinanzikirsayisi = updatezikirsayisi,
                    bitisSayisi = updatebitissayisi,
                    arapca_okunusu = zikir.arapca_okunusu,
                    tur = updatetursayisi,
                    meal = zikir.meal
                )
            )


        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater = menuInflater.inflate(R.menu.zikir_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.zikirsifirla -> {

                var builder = MaterialAlertDialogBuilder(this)
                    .setBackground(resources.getDrawable(R.drawable.alert_shape, null))
                    .setIcon(R.drawable.ic_refresh_black_24dp)
                    .setTitle("Sıfırlama Onayı")
                    .setMessage("Sıfırlamak istediğinize emin misiniz?")
                    .setNegativeButton("Hayır", { dialog, which ->

                        dialog.dismiss()

                    })
                    .setPositiveButton("Evet", { dialog, which ->


                        kalinan_zikir = 0f
                        zikir_sayi_tv.text = kalinan_zikir.toInt().toString()

                        tur_sayisi = 0
                        zikir_tur_tv.text = tur_sayisi.toString()
                        circularProgressBar.setProgressWithAnimation(kalinan_zikir, 100)

                        UpdateViewModel.update(
                            Zikir(
                                qid = zikir.qid,
                                name = zikir.name,
                                kalinanzikirsayisi = kalinan_zikir.toInt(),
                                bitisSayisi = bitis_sayisi.toInt(),
                                arapca_okunusu = zikir.arapca_okunusu,
                                tur = tur_sayisi,
                                meal = zikir.meal
                            )
                        )


                    })
                val dialog = builder.create()
                dialog.show()

                true


            }
            else -> return super.onOptionsItemSelected(item)


        }


    }


    fun vibratePhone(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }
}