package com.example.firstapplication.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firstapplication.Gone
import com.example.firstapplication.R
import com.example.firstapplication.Visible
import com.example.firstapplication.adapter.TabAdapter
import com.example.firstapplication.model.MenuModel
import com.example.firstapplication.namazvaktiApi.*
import com.example.firstapplication.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private val ApiClientSehir: SehirApiService by lazy { SehirApiClient.getApiClient() }
    private val ApiClientVakit: VakitApiService by lazy { VakitApiClient.getApiClient() }
    private var menu = ArrayList<MenuModel>()
    private lateinit var main_tabAdapter: TabAdapter
    private lateinit var menu_tabAdapter: TabAdapter
    private var mainFragments = ArrayList<Fragment>()
    private var menuFragments = ArrayList<Fragment>()
    private var EsmaulHusnaArray = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout.Gone()
        progressBarMain.Visible()

        getVakit()

        addMainFragment()
        addMenuFragment()

        main_tabAdapter = TabAdapter(
            supportFragmentManager,
            mainFragments
        )
        ViewPager.adapter = main_tabAdapter


        menu_tabAdapter = TabAdapter(
            supportFragmentManager,
            menuFragments
        )
        menu_viewPager.adapter = menu_tabAdapter


    }


    fun addMainFragment() {

        mainFragments.add(vakit_fragment())
        mainFragments.add(soz_fragment())
        mainFragments.add(ayet_fragment())

    }

    fun addMenuFragment() {

        menuFragments.add(FirstMenu())
        menuFragments.add(SecondMenuFragment())


    }

    fun getSehir() {

        ApiClientSehir.getSehirWithQuery("2").enqueue(object : Callback<SehirModel> {
            override fun onFailure(call: Call<SehirModel>, t: Throwable) {
                Log.e("Mainactivity", t.message)
            }

            override fun onResponse(call: Call<SehirModel>, response: Response<SehirModel>) {
                Log.e("Sehir1=", response.body()?.get(0)?.sehirAdi.toString())
            }

        })


    }

    fun getVakit() {
        ApiClientVakit.getVakitWithQuery("9543").enqueue(object : Callback<VakitModel> {
            override fun onFailure(call: Call<VakitModel>, t: Throwable) {
                Log.e("Mainactivity", t.message)
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<VakitModel>, response: Response<VakitModel>) {

                if (response.isSuccessful) {

                    val time = LocalTime.now()

                    val imsak = response.body()!!.get(0).ımsak
                    val gunes = response.body()!!.get(0).gunes
                    val ogle = response.body()!!.get(0).ogle
                    val ikindi = response.body()!!.get(0).ıkindi
                    val aksam = response.body()!!.get(0).aksam
                    val yatsi = response.body()!!.get(0).yatsi

                    val imsakf = LocalTime.parse(imsak)
                    val gunesf = LocalTime.parse(gunes)
                    val oglef = LocalTime.parse(ogle)
                    val ikindif = LocalTime.parse(ikindi)
                    val aksamf = LocalTime.parse(aksam)
                    val yatsif = LocalTime.parse(yatsi)

                    var kalanVakit: String


                    if (yatsif.isBefore(time) && imsakf.isAfter(time)) {
                        yatsi_tv.setTextColor(getColor(R.color.Sari))
                        yatsi_values_tv.setTextColor(getColor(R.color.Sari))
                        kalanVakit =
                            (imsakf.hour - time.hour).toString() + (imsakf.minute - time.minute).toString()
                    } else if (imsakf.isBefore(time) && gunesf.isAfter(time)) {
                        imsak_tv.setTextColor(getColor(R.color.Sari))
                        imsak_values_tv.setTextColor(getColor(R.color.Sari))
                        kalanVakit =
                            (gunesf.hour - time.hour).toString() + (gunesf.minute - time.minute).toString()
                    } else if (gunesf.isBefore(time) && oglef.isAfter(time)) {
                        günes_tv.setTextColor(getColor(R.color.Sari))
                        günes_values_tv.setTextColor(getColor(R.color.Sari))
                        kalanVakit =
                            (oglef.hour - time.hour).toString() + ":" + (oglef.minute - time.minute).toString()


                        Toast.makeText(this@MainActivity, kalanVakit, Toast.LENGTH_LONG).show()
                    } else if (oglef.isBefore(time) && ikindif.isAfter(time)) {
                        ogle_tv.setTextColor(getColor(R.color.Sari))
                        ogle_values_tv.setTextColor(getColor(R.color.Sari))

                    } else if (ikindif.isBefore(time) && aksamf.isAfter(time)) {
                        ikindi_tv.setTextColor(getColor(R.color.Sari))
                        ikindi_values_tv.setTextColor(getColor(R.color.Sari))

                    } else if (aksamf.isBefore(time) && yatsif.isAfter(time)) {
                        aksam_tv.setTextColor(getColor(R.color.Sari))
                        aksam_values_tv.setTextColor(getColor(R.color.Sari))

                    }




                    imsak_values_tv.text = imsak
                    günes_values_tv.text = gunes
                    ogle_values_tv.text = ogle
                    ikindi_values_tv.text = ikindi
                    aksam_values_tv.text = aksam
                    yatsi_values_tv.text = yatsi

                    progressBarMain.Gone()
                    linearLayout.Visible()


                }


            }


        })


    }





}
