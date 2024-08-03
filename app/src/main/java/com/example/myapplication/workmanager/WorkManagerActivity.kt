package com.example.myapplication.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.UpdateAppearance
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myapplication.R
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {

    companion object{
        const val COUNT_VALUE = "INPUT KEY"

    }
    private lateinit var workManager : WorkManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        workManager = WorkManager.getInstance(applicationContext)

        val btnSave = findViewById<Button>(R.id.btn_save)
        btnSave.setOnClickListener {
           // oneTImeRequest()
            periodicWorkRequest()
        }
    }


    private fun oneTImeRequest(){


        //getting the input data from activity ro worker class
        val data: Data = Data.Builder()
            .putInt(COUNT_VALUE,125)
            .build()

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        //chain workers
        val filteringWorker = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()
        val compressingWorker = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()
        val downloadingWorker = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

        val parallelWorks = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadingWorker)
        parallelWorks.add(filteringWorker)
       workManager
           .beginWith(parallelWorks)
           .then(compressingWorker)
           .then(uploadRequest)
           .enqueue()


        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, Observer {

                val txtStatus = findViewById<TextView>(R.id.txt_result)
                txtStatus.text = it.state.name

                //getting the output data from worker class to activity
                if(it.state.isFinished){
                    val data = it.outputData
                    val message = data.getString(UploadWorker.OUTPUT_KEY)
                    txtStatus.text = message
                }
            })


    }

    private fun periodicWorkRequest(){
        val periodicRequest = PeriodicWorkRequest.Builder(DownloadingWorker::class.java,15,TimeUnit.SECONDS)
            .build()

           workManager .enqueue(periodicRequest)
    }
}