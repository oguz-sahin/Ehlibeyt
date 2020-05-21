package com.example.firstapplication.ui.fragment

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.firstapplication.R
import com.example.firstapplication.namazvaktiApi.VakitApiClient
import com.example.firstapplication.namazvaktiApi.VakitApiService
import com.example.firstapplication.namazvaktiApi.VakitModel
import com.example.firstapplication.namazvaktiApi.VakitModelItem
import retrofit2.Call
import retrofit2.Response
import java.time.LocalTime


class vakit_fragment : Fragment() {
    val handler=Handler()
    lateinit var  vakitModelItem:VakitModelItem
    private val ApiClientVakit: VakitApiService by lazy { VakitApiClient.getApiClient() }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vakit_fragment, container, false)


     ApiClientVakit.getVakitWithQuery("9543").enqueue(object : retrofit2.Callback<VakitModel> {
            override fun onFailure(call: Call<VakitModel>, t: Throwable) {
               Log.e("hata responsu","api çekemdi")
            }

            override fun onResponse(call: Call<VakitModel>, response: Response<VakitModel>) {

                vakitModelItem= response.body()!![0]
                val imsak = vakitModelItem.ımsak
                val gunes = vakitModelItem.gunes
                val ogle = vakitModelItem.ogle
                val ikindi =  vakitModelItem.ıkindi
                val aksam = vakitModelItem.aksam
                val yatsi =  vakitModelItem.yatsi

                val imsakf = LocalTime.parse(imsak)
                val gunesf = LocalTime.parse(gunes)
                val oglef = LocalTime.parse(ogle)
                val ikindif = LocalTime.parse(ikindi)
                val aksamf = LocalTime.parse(aksam)
                val yatsif = LocalTime.parse(yatsi)

                val currentTime=LocalTime.now()
                val textView=view.findViewById<TextView>(R.id.textView)
                handler.post(object : Runnable {
                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun run() {


                        if (yatsif.isBefore(currentTime) ) {
                            textView.text= KalanVakit(imsakf)

                        } else if (imsakf.isBefore(currentTime) && gunesf.isAfter(currentTime)) {
                            textView.text= KalanVakit(gunesf)

                        } else if (gunesf.isBefore(currentTime) && oglef.isAfter(currentTime)) {
                            textView.text= KalanVakit(oglef)

                        } else if (oglef.isBefore(currentTime) && ikindif.isAfter(currentTime)) {
                            textView.text= KalanVakit(ikindif)

                        } else if (ikindif.isBefore(currentTime) && aksamf.isAfter(currentTime)) {
                            textView.text= KalanVakit(aksamf)
                        } else if (aksamf.isBefore(currentTime) && yatsif.isAfter(currentTime)) {
                            textView.text= KalanVakit(yatsif)

                        }

                        handler.postDelayed(this, 100)

                    }
                })
            }


        })


        return view
    }

}



@RequiresApi(Build.VERSION_CODES.O)
fun KalanVakit(time: LocalTime): String {

    val current = LocalTime.now()
    val second = time.minusSeconds(current.second.toLong()).second
    var minute = time.minusMinutes(current.minute.toLong()).minute
    var hour = time.minusHours(current.hour.toLong()).hour
    if (time.second < current.second) minute = minute - 1
    if (time.minute < current.minute) hour = hour - 1

    val kalanVakit = hour.toString() + ":" + minute.toString() + ":" + second.toString()
    return kalanVakit
}

