package com.rwm.wapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rwm.wapp.databinding.ActivityMainBinding
import com.rwm.wapp.databinding.ActivityTictactoeBinding

class ActivityTicTacToe : AppCompatActivity() {
    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var fisrtTurn = Turn.CROSS
    private var currenttTurn = Turn.CROSS

    private lateinit var binding : ActivityTictactoeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTictactoeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}