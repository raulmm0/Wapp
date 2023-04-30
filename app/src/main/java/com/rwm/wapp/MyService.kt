package com.rwm.wapp

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class MyService : Service() {

    private val logTag: String = "MyServiceLog"
    private lateinit var notMan : NotificationManager

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
        notMan = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // Display a notification about us starting.
        showNotification()
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(logTag, "** MyService onDestroy **")
        super.onDestroy()
    }

    /**
     * Show a notification while this service is running.
     */
    private fun showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        val text = "getText("

        // The PendingIntent to launch our activity if the user selects this notification
        val contentIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java), 0
        )

        // Set the info for the views that show in the notification panel.
        val notification: Notification = Notification.Builder(this)
            .setSmallIcon(R.drawable.dedsec) // the status icon
            .setTicker(text) // the status text
            .setWhen(System.currentTimeMillis()) // the time stamp
            .setContentTitle("getText(R.string.local_service_label)") // the label of the entry
            .setContentText(text) // the contents of the entry
            .setContentIntent(contentIntent) // The intent to send when the entry is clicked
            .build()

        // Send the notification.
        // We use a string id because it is a unique number.  We use it later to cancel.
        notMan.notify(1, notification)
    }

}