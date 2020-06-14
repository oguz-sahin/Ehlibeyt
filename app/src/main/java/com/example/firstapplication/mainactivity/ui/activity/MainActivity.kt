package com.example.firstapplication.mainactivity.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firstapplication.Gone
import com.example.firstapplication.R
import com.example.firstapplication.Visible
import com.example.firstapplication.log
import com.example.firstapplication.mainactivity.adapter.TabAdapter
import com.example.firstapplication.mainactivity.namazvaktiApi.VakitApiClient
import com.example.firstapplication.mainactivity.namazvaktiApi.VakitApiService
import com.example.firstapplication.mainactivity.namazvaktiApi.VakitModelItem
import com.example.firstapplication.mainactivity.ui.fragment.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private val ApiClientVakit: VakitApiService by lazy { VakitApiClient.getApiClient() }
    private lateinit var main_tabAdapter: TabAdapter
    private lateinit var menu_tabAdapter: TabAdapter
    private var mainFragments = ArrayList<Fragment>()
    private var menuFragments = ArrayList<Fragment>()
    private lateinit var job: Job
    private val handler = Handler()
    private lateinit var response: Response<VakitModelItem>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout.Gone()
        progressBarMain.Visible()

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

        getVakit()

    }


    private fun addMainFragment() {

        mainFragments.add(vakit_fragment())
        mainFragments.add(soz_fragment())
        mainFragments.add(ayet_fragment())

    }

    private fun addMenuFragment() {

        menuFragments.add(FirstMenu())
        menuFragments.add(SecondMenuFragment())


    }



    fun getVakit() {

        job = CoroutineScope(Dispatchers.Main).launch {
            val current = Calendar.getInstance()
            val formatter = SimpleDateFormat("dd.MM.yyyy")
            val formattedDate = formatter.format(current.time)

            try {
                withContext(Dispatchers.IO) {
                    response = ApiClientVakit.getVakitWithQuery(
                        "get",
                        formattedDate,
                        "İSTANBUL",
                        "TÜRKİYE",
                        "json"
                    )
                }

                if (response.isSuccessful) {
                    handler.post(object : Runnable {

                        override fun run() {
                            val current2 = Calendar.getInstance()
                            val formatterHourAndMinute = SimpleDateFormat("hh:mm")
                            val currenTimeAndHour = formatterHourAndMinute.parse(
                                current2.get(Calendar.HOUR_OF_DAY).toString() + ":" + current2.get(
                                    Calendar.MINUTE
                                ).toString()
                            )
                            val imsak = response.body()!!.ımsak
                            val gunes = response.body()!!.gunes
                            val ogle = response.body()!!.oglen
                            val ikindi = response.body()!!.ıkindi
                            val aksam = response.body()!!.aksam
                            val yatsi = response.body()!!.yatsi

                            val imsakf = formatterHourAndMinute.parse(imsak)
                            val gunesf = formatterHourAndMinute.parse(gunes)
                            val oglef = formatterHourAndMinute.parse(ogle)
                            val ikindif = formatterHourAndMinute.parse(ikindi)
                            val aksamf = formatterHourAndMinute.parse(aksam)
                            val yatsif = formatterHourAndMinute.parse(yatsi)



                            if (yatsif.before(currenTimeAndHour)) {
                                yatsi_tv.setTextColor(getColor(R.color.Sari))
                                yatsi_values_tv.setTextColor(getColor(R.color.Sari))
                                imsak_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_values_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_values_tv.setTextColor(getColor(R.color.colorBlack))

                            } else if (imsakf.before(currenTimeAndHour) && gunesf.after(
                                    currenTimeAndHour
                                )
                            ) {
                                imsak_tv.setTextColor(getColor(R.color.Sari))
                                imsak_values_tv.setTextColor(getColor(R.color.Sari))
                                yatsi_tv.setTextColor(getColor(R.color.colorBlack))
                                yatsi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_values_tv.setTextColor(getColor(R.color.colorBlack))

                            } else if (gunesf.before(currenTimeAndHour) && oglef.after(
                                    currenTimeAndHour
                                )
                            ) {
                                yatsi_tv.setTextColor(getColor(R.color.colorBlack))
                                yatsi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_values_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_tv.setTextColor(getColor(R.color.Sari))
                                günes_values_tv.setTextColor(getColor(R.color.Sari))
                                ogle_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_values_tv.setTextColor(getColor(R.color.colorBlack))

                            } else if (oglef.before(currenTimeAndHour) && ikindif.after(
                                    currenTimeAndHour
                                )
                            ) {
                                yatsi_tv.setTextColor(getColor(R.color.colorBlack))
                                yatsi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_values_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_tv.setTextColor(getColor(R.color.Sari))
                                ogle_values_tv.setTextColor(getColor(R.color.Sari))
                                ikindi_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_values_tv.setTextColor(getColor(R.color.colorBlack))

                            } else if (ikindif.before(currenTimeAndHour) && aksamf.after(
                                    currenTimeAndHour
                                )
                            ) {
                                yatsi_tv.setTextColor(getColor(R.color.colorBlack))
                                yatsi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_values_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_tv.setTextColor(getColor(R.color.Sari))
                                ikindi_values_tv.setTextColor(getColor(R.color.Sari))
                                aksam_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_values_tv.setTextColor(getColor(R.color.colorBlack))

                            } else if (aksamf.before(currenTimeAndHour) && yatsif.after(
                                    currenTimeAndHour
                                )
                            ) {
                                yatsi_tv.setTextColor(getColor(R.color.colorBlack))
                                yatsi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_values_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_tv.setTextColor(getColor(R.color.Sari))
                                aksam_values_tv.setTextColor(getColor(R.color.Sari))

                            } else if (currenTimeAndHour.before(imsakf)) {
                                yatsi_tv.setTextColor(getColor(R.color.Sari))
                                yatsi_values_tv.setTextColor(getColor(R.color.Sari))
                                imsak_tv.setTextColor(getColor(R.color.colorBlack))
                                imsak_values_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_tv.setTextColor(getColor(R.color.colorBlack))
                                günes_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_tv.setTextColor(getColor(R.color.colorBlack))
                                ogle_values_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_tv.setTextColor(getColor(R.color.colorBlack))
                                ikindi_values_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_tv.setTextColor(getColor(R.color.colorBlack))
                                aksam_values_tv.setTextColor(getColor(R.color.colorBlack))
                            }


                            imsak_values_tv.text = imsak
                            günes_values_tv.text = gunes
                            ogle_values_tv.text = ogle
                            ikindi_values_tv.text = ikindi
                            aksam_values_tv.text = aksam
                            yatsi_values_tv.text = yatsi

                            handler.postDelayed(this, 100)
                            /*   val calendar: Calendar = Calendar.getInstance()
                              calendar.set(Calendar.HOUR_OF_DAY, imsakf.hours)
                              calendar.set(Calendar.MINUTE, imsakf.minutes)
                              calendar.set(Calendar.SECOND, imsakf.seconds)

                             val intent = Intent(this@MainActivity, imsakAlarmReciever::class.java)
                              val pendingIntent = PendingIntent.getBroadcast(
                                  this@MainActivity,
                                  16,
                                  intent,
                                  PendingIntent.FLAG_UPDATE_CURRENT
                              )
                              val am =
                                  this@MainActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                              am.setRepeating(
                                  AlarmManager.RTC_WAKEUP,
                                  calendar.timeInMillis,
                                  AlarmManager.INTERVAL_DAY,
                                  pendingIntent
                            )*/

                        }
                    })

                    progressBarMain.Gone()
                    linearLayout.Visible()

                } else {
                    progressBarMain.Gone()
                    linearLayout.Visible()
                    val builder = MaterialAlertDialogBuilder(this@MainActivity)
                    builder.setBackground(resources.getDrawable(R.drawable.alert_shape, null))
                        .setTitle("Hata")
                        .setMessage("İnternet bağlantısı sağlanadı")

                    val dialog = builder.create()
                    dialog.show()

                }


            } catch (e: HttpException) {


                "cns".log()


            } catch (t: Throwable) {


                "${t.message}".log()

            }


        }

    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }



}


