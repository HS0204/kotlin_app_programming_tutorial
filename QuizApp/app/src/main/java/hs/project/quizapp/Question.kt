package hs.project.quizapp

import androidx.annotation.StringRes

data class Question(
    @StringRes
    val textResId: Int,
    val answer: Boolean
)

data class CheckAnswer(
    val answer: Boolean
)
