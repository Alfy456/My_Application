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

class UploadWorker(context: Context, params: WorkerParameters): Worker(context,params) {
    companion object{
        const val OUTPUT_KEY = "OUTPUT KEY"
    }
    override fun doWork(): Result {
        return try {

            val count = inputData.getInt(WorkManagerActivity.COUNT_VALUE,0)

            val time = SimpleDateFormat("dd/m/yyyy hh:mm:ss")
            val currentDate = time.format(Date())
            val outputData = Data.Builder()
                .putString(OUTPUT_KEY,currentDate)
                .build()


            for (i: Int in 0 until count){
                Log.i("MYTAG", "uplading $i")
            }
            Result.success(outputData)
        }catch (e: Exception){
            Result.failure()
        }

    }
}