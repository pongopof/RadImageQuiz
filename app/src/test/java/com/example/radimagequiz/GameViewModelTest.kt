package com.example.radimagequiz

import com.example.data.Answer
import com.example.data.CorrectAnswer
import com.example.data.Question
import com.example.radimagequiz.ui.layout.ProgramViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GameViewModelTest{
    private val viewModel = ProgramViewModel()

    @Test
    fun programViewModel_nextQuestion_correctQuestionChange(){
        val questions = viewModel.questions
        viewModel.nextQuestion()
        val secondQuestion: Question? = viewModel.uiState.value.currentQuestion
        assertEquals(questions[1], secondQuestion)
    }

    @Test
    fun programViewModel_scoreGained_correctScoreUpdate(){
        val questions = viewModel.questions
        var correctAnswer: Answer = questions[0].answers[0]
        viewModel.uiState.value.currentAnswer = correctAnswer
        viewModel.nextQuestion()
        assertEquals(viewModel.uiState.value.score, 1)
    }
}