package com.example.kotlinquizapp.ui.game

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel :ViewModel() {

    private var _s = 0
    val s : Int get() = _s

    private var _correct = 0
    val correct : Int get() = _correct

    private var _progressBarStatus = 0
    val progressBarStatus : Int get() = _progressBarStatus

    private val handler = Handler(Looper.getMainLooper())

    fun increaseProgressBarStatus(isWrongAnswer: Boolean) {
        _progressBarStatus += if(isWrongAnswer) {
            15
        }else {
            1
        }
    }

    fun increaseCorrect() {
        _correct = (_correct)?.inc()
    }

    fun increaseS() {
        _s = (_s)?.inc()
    }

    fun killTheThread() {
        _s = 10
    }

    fun reinitializeProgressBar() {
        _progressBarStatus = 0
    }

}