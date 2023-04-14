package com.rwm.wapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class MyService : Service() {

    private val logTag = "MyServiceLog"

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
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(logTag, "** MyService onDestroy **")
        super.onDestroy()
    }

}