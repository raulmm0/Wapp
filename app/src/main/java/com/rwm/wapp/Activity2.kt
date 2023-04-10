package com.rwm.wapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rwm.wapp.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {

    private lateinit var binding: Activity2Binding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_2)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // sharedPreferences
        sharedPref = this.getPreferences(Context.MODE_PRIVATE)

        // asignar texto
        binding.editTextTextMultiLine.setText(sharedPref.getString("text", ""))
    }

    override fun onDestroy() {
        super.onDestroy()
        with (sharedPref.edit()) {
            putString("text", binding.editTextTextMultiLine.text.toString())
            apply()
        }
    }
}