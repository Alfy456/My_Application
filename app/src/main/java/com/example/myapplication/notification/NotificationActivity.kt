package com.example.myapplication.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapplication.R

class NotificationActivity : AppCompatActivity() {

    private val channelID = "com.example.myapplication.notification.channel1"
    private var notificationManager : NotificationManager? = null
    private val REPLYKEY = "reply_key"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID,"Demo","This is Demo")

        val button = findViewById<Button>(R.id.btn_notification)
        button.setOnClickListener {
            displayNotification()
        }


    }

    private fun displayNotification() {
        val id = 2
        val resultIntent = Intent(this,SecondActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            resultIntent,
            PendingIntent.FLAG_MUTABLE)

        //reply action
        val remoteInput: RemoteInput = RemoteInput.Builder(REPLYKEY).run {
            setLabel("Insert your name here")
            build()
        }

        val replyAction : NotificationCompat.Action = NotificationCompat
            .Action.Builder(0,"REPLY",pendingIntent)
            .addRemoteInput(remoteInput).build()


        //adding action button to the notification
        val intent2 = Intent(this,DetailsActivity::class.java)
        val pendingIntent2: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent2,
            PendingIntent.FLAG_IMMUTABLE)

        val action2 : NotificationCompat.Action = NotificationCompat.Action.Builder(0,"Details",pendingIntent2).build()

        val intent3 = Intent(this,SettingsActivity::class.java)
        val pendingIntent3: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent3,
            PendingIntent.FLAG_IMMUTABLE)

        val action3 : NotificationCompat.Action = NotificationCompat.Action.Builder(0,"Settings",pendingIntent3).build()

        val notification = NotificationCompat.Builder(this@NotificationActivity,channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a Demo")
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(action2)
            .addAction(action3)
            .addAction(replyAction)
            .build()

        notificationManager?.notify(id,notification)


    }




    private fun createNotificationChannel(id: String,name: String,channelDescription: String){
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id,name,importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}