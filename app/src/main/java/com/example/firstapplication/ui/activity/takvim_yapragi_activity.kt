package com.example.firstapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapplication.R
import com.example.firstapplication.adapter.TakvimAdapter
import com.example.firstapplication.model.TakvimModel
import kotlinx.android.synthetic.main.activity_takvim_yapragi_activity.*
import kotlinx.android.synthetic.main.activity_takvim_yapragi_activity.view.*

class takvim_yapragi_activity : AppCompatActivity() {


    var takvimArray = ArrayList<TakvimModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_takvim_yapragi_activity)

        setSupportActionBar(takvim_yapragi_Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        takvim_yapragi_Toolbar.Toolbar_title_tv.text = "Takvim Yaprağı"

        AddArray()
        takvim_yapragi_recyclerview.layoutManager =
            LinearLayoutManager(this@takvim_yapragi_activity, LinearLayoutManager.VERTICAL, false)
        takvim_yapragi_recyclerview.adapter = TakvimAdapter(takvimArray)


        Toolbar_back_iv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }


    fun AddArray() {


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


}
