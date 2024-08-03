package com.example.myapplication.workmanager

import android.content.Context
import android.content.ContextParams
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplication.MainActivity
import kotlinx.coroutines.MainScope
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class DownloadingWorker(context: Context, params: WorkerParameters): Worker(context,params) {

    override fun doWork(): Result {
        return try {


            val time = SimpleDateFormat("dd/m/yyyy hh:mm:ss")
            val currentDate = time.format(Date())


            for (i: Int in 0 ..3000){
                //Log.i("MYTAG", "Downloading $i")
                Log.i("MYTAG", "Current time $currentDate")
            }

            Result.success()
        }catch (e: Exception){
            Result.failure()
        }

    }
}