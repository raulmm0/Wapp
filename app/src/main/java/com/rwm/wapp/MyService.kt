package com.rwm.wapp

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.IBinder
import android.util.Log


class MyService : Service() {

    private val logTag = "eiba"

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
        registerReceiver(connectedReceiver,IntentFilter(Intent.ACTION_POWER_CONNECTED))
    }

    private val disconnectedReceiver :BroadcastReceiver= object :BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d(logTag, "** Disconnected **")
            unregisterReceiver(batteryChangeReceiver)
            registerReceiver(connectedReceiver,IntentFilter(Intent.ACTION_POWER_CONNECTED))
            unregisterReceiver(this)
        }
    }

    private val connectedReceiver: BroadcastReceiver = object :BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d(logTag, "** Connected **")
            registerReceiver(batteryChangeReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            registerReceiver(disconnectedReceiver, IntentFilter(Intent.ACTION_POWER_DISCONNECTED))
            unregisterReceiver(this)
        }
    }

    private val batteryChangeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
                context.registerReceiver(null, ifilter)
            }
            val batteryPct: Float? = batteryStatus?.let { i ->
                val level: Int = i.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale: Int = i.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                level * 100 / scale.toFloat()
            }
            Log.d(logTag, "Current battery level: $batteryPct")
            if ((batteryPct is Float) && (batteryPct >= 80)) {
                Log.d(logTag, "battery fully loaded")
            }
        }
    }

    override fun onDestroy() {
        Log.d(logTag, "** MyService onDestroy **")
        super.onDestroy()
    }

}