package com.example.kotlinquizapp.data

import com.example.kotlinquizapp.R

class DataSource {
    fun loadQuestions():List<Int>{
        return listOf<Int>(
            R.string.question1,
            R.string.question2,
            R.string.question3,
            R.string.question4,
            R.string.question5,
            R.string.question6,
            R.string.question7,
            R.string.question8,
            R.string.question9,
            R.string.question10
        )
    }

    fun loadAnswerOptions():List<Int> {
        return listOf<Int>(
            R.array.question1,
            R.array.question2,
            R.array.question3,
            R.array.question4,
            R.array.question5,
            R.array.question6,
            R.array.question7,
            R.array.question8,
            R.array.question9,
            R.array.question10
        )
    }

    fun loadAnswers():List<Int> {
        return listOf<Int>(
            R.string.answer1,
            R.string.answer2,
            R.string.answer3,
            R.string.answer4,
            R.string.answer5,
            R.string.answer6,
            R.string.answer7,
            R.string.answer8,
            R.string.answer9,
            R.string.answer10
        )
    }
}