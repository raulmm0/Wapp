package com.rwm.wapp

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat


class MyService : Service() {

    private val logTag: String = "MyServiceLog"
    private lateinit var notificationManager : NotificationManager

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(logTag, "** MyService onBind **")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(logTag, "** MyService onStartCommand **")
        return START_NOT_STICKY
    }

    override fun onCreate() {
        Log.d(logTag, "** MyService onCreate **")
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        showNotification()
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(logTag, "** MyService onDestroy **")
        super.onDestroy()
    }

    private fun showNotification() {
        val cantidad = (Math.random() * 17).toInt() + 3

        val builder = NotificationCompat.Builder(this, "002")
            .setSmallIcon(R.drawable.dedsec)
            .setContentTitle("Alerta")
            .setContentText("Esta vez serán $cantidad")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, builder.build())
    }
}