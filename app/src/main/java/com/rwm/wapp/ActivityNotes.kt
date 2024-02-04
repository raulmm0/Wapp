package com.rwm.wapp

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rwm.wapp.databinding.ActivityNotesBinding

class ActivityNotes : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_2)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // sharedPreferences
        sharedPref = this.getPreferences(Context.MODE_PRIVATE)

        // assign text
        binding.editTextTextMultiLine.setText(sharedPref.getString("notes", ""))

        // play music
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.start()
    }

    override fun onDestroy() {
        with (sharedPref.edit()) {
            putString("notes", binding.editTextTextMultiLine.text.toString())
            apply()
        }
        mediaPlayer.stop()
        super.onDestroy()
    }

    override fun onPause() {
        mediaPlayer.pause()
        super.onPause()
    }

    override fun onResume() {
        mediaPlayer.start()
        super.onResume()
    }
}