package com.example.firstapplication.mainactivity.utill

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.firstapplication.mainactivity.namazvaktiApi.VakitApiClient
import com.example.firstapplication.mainactivity.namazvaktiApi.VakitApiService
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

private lateinit var job: Job
private val apiclient: VakitApiService by lazy { VakitApiClient.getApiClient() }

class ApiWorkmanager(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {

    override fun doWork(): Result {


        return Result.success()
    }


    fun getVakit() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val current = Calendar.getInstance()
            val formatter = SimpleDateFormat("dd.MM.yyyy")
            val formattedDate = formatter.format(current.time)

            val response =
                apiclient.getVakitWithQuery("get", formattedDate, "İSTANBUL", "TÜRKİYE", "json")

            formatter.parse(response.body()?.aksam)





            withContext(Dispatchers.Main) {


            }

        }


    }

}


