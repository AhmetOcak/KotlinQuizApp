package com.example.kotlinquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.os.HandlerCompat.postDelayed
import com.example.kotlinquizapp.data.DataSource
import com.example.kotlinquizapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val data get() = DataSource()
    private var s = 0
    private val handler = Handler(Looper.getMainLooper())
    private var correct = 0
    private var wrong = 0
    private var progressBarStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.question.text = resources.getString(data.loadQuestions()[s])
        binding.answer1.text = resources.getStringArray(data.loadAnswerOptions()[s])[0]
        binding.answer2.text = resources.getStringArray(data.loadAnswerOptions()[s])[1]
        binding.answer3.text = resources.getStringArray(data.loadAnswerOptions()[s])[2]
        binding.answer4.text = resources.getStringArray(data.loadAnswerOptions()[s])[3]

        Thread(Runnable {
            while (progressBarStatus < 100) {
                progressBarStatus += 1

                handler.post {
                    binding.progressBar.progress = progressBarStatus
                }

                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if(progressBarStatus == 100) {
                    this.runOnUiThread(Runnable {
                        uptadeView()
                    })
                    progressBarStatus = 0
                }
            }
        }).start()
    }

    fun answerButton(view: View) {
        val userAnswer = findViewById<TextView>(view.id).text.toString()

        if (userAnswer == resources.getString(data.loadAnswers()[s])) {
            Log.e("e", "true")
            uptadeView()
        } else {
            wrong += 1
            Log.e("e", "false")
        }
    }

    private fun uptadeView() {
        s += 1
        correct += 1
        if (s == 10) s = 0

        Log.e("q", s.toString())

        binding.question.text = resources.getString(data.loadQuestions()[s])
        binding.answer1.text = resources.getStringArray(data.loadAnswerOptions()[s])[0]
        binding.answer2.text = resources.getStringArray(data.loadAnswerOptions()[s])[1]
        binding.answer3.text = resources.getStringArray(data.loadAnswerOptions()[s])[2]
        binding.answer4.text = resources.getStringArray(data.loadAnswerOptions()[s])[3]
    }
}