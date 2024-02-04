package com.rwm.wapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.rwm.wapp.databinding.ActivityMainBinding
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val logTag: String = "MainActivityLog"
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var notificationManager: NotificationManager
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private var soundChanger: Boolean = true
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "** MainActivity onCreate **")

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getPreferences(Context.MODE_PRIVATE)

        executor = ContextCompat.getMainExecutor(applicationContext)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // open activity
                    startActivity(Intent(applicationContext, ActivityNotes::class.java))
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication Failed", Toast.LENGTH_LONG).show()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext, "Authentication Error", Toast.LENGTH_LONG).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        // cambiar imagen dependiendo el tema
        when (applicationContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.imageButton.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.nexus_logo)
                )
                binding.coordinatorLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.black
                    )
                )
            }
        }

        val firstStart: Boolean = sharedPref.getString("firstStart", "") != ""
        // create notification channel
        if (firstStart) {
            notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            createNotificationChannel()
            with (sharedPref.edit()) {
                putString("firstStart", "NO")
                apply()
            }
        }

        // primary button, make a sound
        binding.imageButton.setOnClickListener {
            if (soundChanger) {
                mediaPlayer = MediaPlayer.create(this, R.raw.wd_1_hack)
                soundChanger = false
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.wd_1_hack_short)
                soundChanger = true
            }
            mediaPlayer.start()
//        startService(Intent(applicationContext, MyService::class.java))
//        Snackbar.make(view, "Esta vez serán $cantidad", Snackbar.LENGTH_LONG).show()
//        Toast.makeText(view.context, "Esta vez serán $cantidad", Toast.LENGTH_LONG).show()
        }

//        // secondary button
//        binding.floatingActionButton.setOnClickListener { view ->
//
//          Snackbar.make(view, "Authentication", Snackbar.LENGTH_LONG).show()
//          biometricPrompt.authenticate(promptInfo)
//
//        }

        Toast.makeText(applicationContext, "¡Bienvenido!", Toast.LENGTH_LONG).show()

    }

    /**
     * Create a notification channel
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            notificationManager.getNotificationChannel("002") == null
        ) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    "002",
                    "General", NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
    }

    override fun onDestroy() {
        Log.d(logTag, "** MainActivity onDestroy **")
        super.onDestroy()
    }

    override fun onPause() {
        Log.d(logTag, "** MainActivity onPause **")
        super.onPause()
    }

    override fun onResume() {
        Log.d(logTag, "** MainActivity onResume **")
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            // Notes
            R.id.notes -> {

                biometricPrompt.authenticate(promptInfo)

            }

            // Games
            R.id.ThreeInARow -> {

                startActivity(Intent(applicationContext, ActivityTicTacToe::class.java))

            }

            R.id.guestTheNumber -> {

//                startActivity(Intent(applicationContext, ActivityGuestTheNumber::class.java))

            }

            // Options
            R.id.options -> {

                startActivity(Intent(applicationContext, SettingsActivity::class.java))

            }
            // Default
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

}