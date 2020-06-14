package com.example.firstapplication.takvimyapragi.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapplication.R
import com.example.firstapplication.mainactivity.namazvaktiApi.VakitApiClient
import com.example.firstapplication.mainactivity.namazvaktiApi.VakitApiService
import com.example.firstapplication.mainactivity.ui.activity.MainActivity
import com.example.firstapplication.takvimyapragi.adapter.TakvimAdapter
import com.example.firstapplication.takvimyapragi.model.TakvimModel
import kotlinx.android.synthetic.main.activity_takvim_yapragi_activity.*
import kotlinx.android.synthetic.main.activity_takvim_yapragi_activity.view.*
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class takvim_yapragi_activity : AppCompatActivity() {

    private val ApiClientVakit: VakitApiService by lazy { VakitApiClient.getApiClient() }
    private var takvimArray = ArrayList<TakvimModel>()
    private lateinit var job: Job

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_takvim_yapragi_activity)

        setupUI()
        getVakit()
        addArray()
        takvim_yapragi_recyclerview.layoutManager =
            LinearLayoutManager(this@takvim_yapragi_activity, LinearLayoutManager.VERTICAL, false)
        takvim_yapragi_recyclerview.adapter =
            TakvimAdapter(
                takvimArray
            )

    }


    private fun addArray() {


        takvimArray.add(
            TakvimModel(
                R.drawable.unknown,
                "Günün Sözü",
                "‘’Lezzet-i hizmet-i imaniye her kederi unutturur.’’ (Barla Lâhikası 144)",
                applicationContext.getColor(R.color.Sari),
                applicationContext.getColor(android.R.color.white)
            )
        )
        takvimArray.add(
            TakvimModel(
                R.drawable.gununsozu,
                "Günün Ayeti",
                "Kör ile gören bir olmaz, iman edip salih ameller işleyen kimseler ile kötülük yapan da bir değildir. Ne kadar da az düşünüyorsunuz!" + "(Mü'min 58)",
                applicationContext.getColor(R.color.mor),
                applicationContext.getColor(R.color.colorBlack)
            )
        )


        takvimArray.add(
            TakvimModel(
                R.drawable.unknown,
                "Günün Hadisi",
                "Kişi büyük günahlardan kaçındığı takdirde, beş vakit namazlar, cumadan cumaya ve Ramazan'dan Ramazan'a, aralarında işlenen günahlara kefarettir.",
                applicationContext.getColor(R.color.Sari),
                applicationContext.getColor(android.R.color.white)
            )
        )


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getVakit() {

        job = CoroutineScope(Dispatchers.IO).launch {

            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val formattedDate = LocalDate.now().format(formatter)

            val response = ApiClientVakit.getVakitWithQuery(
                "get",
                formattedDate,
                "İSTANBUL",
                "TÜRKİYE",
                "json"
            )
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {

                    val vakitModelItem = response.body()!!
                    //takvim_yapragi_tarih_tv.text=vakitModelItem.miladiTarihUzun
                    // takvim_yapragi_hicri_tv.text=vakitModelItem.hicriTarihUzun

                    val time = LocalTime.now()

                    val imsak = vakitModelItem.ımsak
                    val gunes = vakitModelItem.gunes
                    val ogle = vakitModelItem.oglen
                    val ikindi = vakitModelItem.ıkindi
                    val aksam = vakitModelItem.aksam
                    val yatsi = vakitModelItem.yatsi

                    val imsakf = LocalTime.parse(imsak)
                    val gunesf = LocalTime.parse(gunes)
                    val oglef = LocalTime.parse(ogle)
                    val ikindif = LocalTime.parse(ikindi)
                    val aksamf = LocalTime.parse(aksam)
                    val yatsif = LocalTime.parse(yatsi)



                    if (yatsif.isBefore(time)) {
                        yatsi_tv.setTextColor(getColor(R.color.Sari))
                        yatsi_values_tv.setTextColor(getColor(R.color.Sari))

                    } else if (imsakf.isBefore(time) && gunesf.isAfter(time)) {
                        imsak_tv.setTextColor(getColor(R.color.Sari))
                        imsak_values_tv.setTextColor(getColor(R.color.Sari))

                    } else if (gunesf.isBefore(time) && oglef.isAfter(time)) {
                        günes_tv.setTextColor(getColor(R.color.Sari))
                        günes_values_tv.setTextColor(getColor(R.color.Sari))

                    } else if (oglef.isBefore(time) && ikindif.isAfter(time)) {
                        ogle_tv.setTextColor(getColor(R.color.Sari))
                        ogle_values_tv.setTextColor(getColor(R.color.Sari))

                    } else if (ikindif.isBefore(time) && aksamf.isAfter(time)) {
                        ikindi_tv.setTextColor(getColor(R.color.Sari))
                        ikindi_values_tv.setTextColor(getColor(R.color.Sari))

                    } else if (aksamf.isBefore(time) && yatsif.isAfter(time)) {
                        aksam_tv.setTextColor(getColor(R.color.Sari))
                        aksam_values_tv.setTextColor(getColor(R.color.Sari))

                    } else if (time.isBefore(imsakf)) {
                        yatsi_tv.setTextColor(getColor(R.color.Sari))
                        yatsi_values_tv.setTextColor(getColor(R.color.Sari))
                    }


                    imsak_values_tv.text = imsak
                    günes_values_tv.text = gunes
                    ogle_values_tv.text = ogle
                    ikindi_values_tv.text = ikindi
                    aksam_values_tv.text = aksam
                    yatsi_values_tv.text = yatsi

                }
            }
        }

    }

    private fun setupUI() {

        setSupportActionBar(takvim_yapragi_Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        takvim_yapragi_Toolbar.Toolbar_title_tv.text = "Takvim Yaprağı"

        Toolbar_back_iv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}
