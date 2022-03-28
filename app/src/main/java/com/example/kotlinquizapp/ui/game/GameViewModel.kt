package com.example.kotlinquizapp.ui.game

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel :ViewModel() {

    private var _s = MutableLiveData(0)
    val s : LiveData<Int> get() = _s

    private var _correct = MutableLiveData(0)
    val correct : LiveData<Int> get() = _correct

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
        _correct.value = (_correct.value)?.inc()
    }

    fun increaseS() {
        _s.value = (_s.value)?.inc()
    }

    fun killTheThread() {
        _s.value = 10
    }

    fun reinitializeProgressBar() {
        _progressBarStatus = 0
    }

}