package com.example.myapplication.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapplication.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        receiveInput()
    }

    private fun receiveInput() {

        val REPLYKEY = "reply_key"
        val id = 2
        val channelID = "com.example.myapplication.notification.channel1"
        val txtResult = findViewById<TextView>(R.id.txt_resullt)
        val intent: Intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput!=null){
            val inputString = remoteInput.getCharSequence(REPLYKEY).toString()
            txtResult.text = inputString
        }

        val replyNotification = NotificationCompat.Builder(this,channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Your reply received")
            .build()

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id,replyNotification)

    }
}