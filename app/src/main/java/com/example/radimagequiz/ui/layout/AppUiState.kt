package com.example.radimagequiz.ui.layout

import com.example.data.Answer
import com.example.data.Question

data class AppUiState(
    val currentQuestion: Question? = null,
    val questionIndex: Int = 0,
    val score: Int = 0,
    var currentAnswer: Answer? = null,
    val isOver: Boolean = false,
    val answeredQuestions: MutableMap<Question?, Answer?> = mutableMapOf()
)

