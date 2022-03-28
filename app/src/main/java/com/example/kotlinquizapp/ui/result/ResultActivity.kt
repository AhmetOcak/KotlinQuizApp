package com.example.kotlinquizapp.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinquizapp.MainActivity
import com.example.kotlinquizapp.R
import com.example.kotlinquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private var correctAnswers = 0
    private var emptyAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        correctAnswers = intent.getIntExtra(resources.getString(R.string.correctAnswers), 0)
        emptyAnswers = 10 - correctAnswers

        binding.correct.text = getString(R.string.correct, correctAnswers)
        binding.empty.text = getString(R.string.empty, emptyAnswers)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}