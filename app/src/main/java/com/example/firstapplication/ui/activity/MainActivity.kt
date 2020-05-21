package com.example.firstapplication.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
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
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.time.LocalDateTime

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
        read_json()
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
                    var array = ArrayList<String>()
                    val vakit = response.body()!!.get(0).aksam
                    for (i in vakit) {
                        if (i.toString().equals(":")) continue
                        array.add(i.toString())
                    }
                    val current = LocalDateTime.now()
                    val currentHour = current.hour
                    val currentMinute = current.minute
                    val currentSecond = current.second


                    val aksamSaat = (array[0] + array[1]).toInt()
                    val aksamDakika = (array[2] + array[3]).toInt()

                    Log.e("aksam saat", aksamSaat.toString() + aksamDakika.toString())

                    linearLayout.Visible()
                    progressBarMain.Gone()
                    imsak_values_tv.text = response.body()!!.get(0).ımsak
                    günes_values_tv.text = response.body()!!.get(0).gunes
                    ogle_values_tv.text = response.body()!!.get(0).ogle
                    ikindi_values_tv.text = response.body()!!.get(0).ıkindi
                    aksam_values_tv.text = response.body()!!.get(0).aksam
                    yatsi_values_tv.text = response.body()!!.get(0).yatsi


                }


            }


        })


    }

    fun read_json() {


        var json: String? = null
        try {
            val inputStream = assets.open("esmaulhuna.json")
            json = inputStream.bufferedReader().use { it.readText() }
            var jsonArray = JSONArray(json)

            for (i in 0..jsonArray.length() - 1) {

                var jsonobj = jsonArray.getJSONObject(i)

                EsmaulHusnaArray.add(jsonobj.getString("isim"))


            }

        } catch (e: IOException) {


        }
        Log.e("esmaul hunsna", EsmaulHusnaArray.size.toString())

    }
}
