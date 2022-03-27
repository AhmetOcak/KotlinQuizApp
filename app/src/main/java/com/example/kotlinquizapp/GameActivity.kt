package com.example.kotlinquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.kotlinquizapp.data.DataSource
import com.example.kotlinquizapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val data get() = DataSource()
    private var s = 0
    private val handler = Handler(Looper.getMainLooper())
    private var correct = 0
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
            while (s < 10) {
                progressBarStatus += 1

                handler.post {
                    binding.progressBar.progress = progressBarStatus
                }

                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if(progressBarStatus >= 50) {
                    this.runOnUiThread(Runnable {
                        uptadeView()
                        binding.progressBar.progress = progressBarStatus
                    })
                }
            }
        }).start()
    }

    fun answerButton(view: View) {
        val userAnswer = findViewById<TextView>(view.id).text.toString()

        if (userAnswer == resources.getString(data.loadAnswers()[s])) {
            correct += 1
            findViewById<Button>(view.id).setBackgroundColor(resources.getColor(R.color.green))
            handler.postDelayed({
                findViewById<Button>(view.id).setBackgroundColor(resources.getColor(R.color.white))
                uptadeView()
            }, 500)
        } else {
            progressBarStatus += 15
            findViewById<Button>(view.id).setBackgroundColor(resources.getColor(R.color.red))
            handler.postDelayed({
                findViewById<Button>(view.id).setBackgroundColor(resources.getColor(R.color.white))
            }, 500)
        }
    }

    private fun uptadeView() {
        progressBarStatus = 0
        s += 1
        if (s == 10) {
            binding.cardview.visibility = View.INVISIBLE
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(resources.getString(R.string.correctAnswers), correct)
            startActivity(intent)
        }else {
            binding.question.text = resources.getString(data.loadQuestions()[s])
            binding.answer1.text = resources.getStringArray(data.loadAnswerOptions()[s])[0]
            binding.answer2.text = resources.getStringArray(data.loadAnswerOptions()[s])[1]
            binding.answer3.text = resources.getStringArray(data.loadAnswerOptions()[s])[2]
            binding.answer4.text = resources.getStringArray(data.loadAnswerOptions()[s])[3]
        }
    }
}