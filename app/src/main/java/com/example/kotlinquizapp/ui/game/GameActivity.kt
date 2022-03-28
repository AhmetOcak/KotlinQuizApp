package com.example.kotlinquizapp.ui.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.example.kotlinquizapp.R
import com.example.kotlinquizapp.ui.result.ResultActivity
import com.example.kotlinquizapp.data.DataSource
import com.example.kotlinquizapp.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: ActivityGameBinding
    private val data get() = DataSource()

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.question.text = resources.getString(data.loadQuestions()[0])
        binding.answer1.text = resources.getStringArray(data.loadAnswerOptions()[0])[0]
        binding.answer2.text = resources.getStringArray(data.loadAnswerOptions()[0])[1]
        binding.answer3.text = resources.getStringArray(data.loadAnswerOptions()[0])[2]
        binding.answer4.text = resources.getStringArray(data.loadAnswerOptions()[0])[3]

        Thread(Runnable {
            while (viewModel.s.value!! < 10) {
                viewModel.increaseProgressBarStatus(false)

                handler.post {
                    binding.progressBar.progress = viewModel.progressBarStatus
                }

                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (viewModel.progressBarStatus >= 50) {
                    this.runOnUiThread(Runnable {
                        uptadeView()
                        binding.progressBar.progress = viewModel.progressBarStatus
                    })
                }
            }
        }).start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.killTheThread()
    }

    fun answerButton(view: View) {
        val userAnswer = findViewById<TextView>(view.id).text.toString()

        if (userAnswer == resources.getString(data.loadAnswers()[viewModel.s.value!!])) {
            viewModel.increaseCorrect()
            findViewById<Button>(view.id).setBackgroundColor(resources.getColor(R.color.green))
            handler.postDelayed({
                findViewById<Button>(view.id).setBackgroundColor(resources.getColor(R.color.white))
                uptadeView()
            }, 500)
        } else {
            viewModel.increaseProgressBarStatus(true)
            findViewById<Button>(view.id).setBackgroundColor(resources.getColor(R.color.red))
            handler.postDelayed({
                findViewById<Button>(view.id).setBackgroundColor(resources.getColor(R.color.white))
            }, 500)
        }
    }

    private fun uptadeView() {
        viewModel.reinitializeProgressBar()
        viewModel.increaseS()

        if (viewModel.s.value!! == 10) {
            binding.cardview.visibility = View.INVISIBLE
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(resources.getString(R.string.correctAnswers), viewModel.correct.value)
            startActivity(intent)
        } else {
            binding.question.text = resources.getString(data.loadQuestions()[viewModel.s.value!!])
            binding.answer1.text =
                resources.getStringArray(data.loadAnswerOptions()[viewModel.s.value!!])[0]
            binding.answer2.text =
                resources.getStringArray(data.loadAnswerOptions()[viewModel.s.value!!])[1]
            binding.answer3.text =
                resources.getStringArray(data.loadAnswerOptions()[viewModel.s.value!!])[2]
            binding.answer4.text =
                resources.getStringArray(data.loadAnswerOptions()[viewModel.s.value!!])[3]
        }
    }
}