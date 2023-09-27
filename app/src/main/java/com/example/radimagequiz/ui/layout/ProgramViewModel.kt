package com.example.radimagequiz.ui.layout

import androidx.lifecycle.ViewModel
import com.example.data.Answer
import com.example.data.CorrectAnswer
import com.example.data.Question
import com.example.data.QuestionBank
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProgramViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()
    val questions = QuestionBank().questions.shuffled()

    init{
        firstQuestion()
    }

    fun firstQuestion() {
        val currentQuestion: Question = questions[0]
        updateState(currentQuestion, 0, 0)
    }

    fun nextQuestion() {
        if (_uiState.value.questionIndex + 1 < questions.size)
        {
            var newPoint = checkCorrectAnswer(_uiState.value.currentAnswer)
            println(newPoint)
            val currentQuestionIndex: Int = _uiState.value.questionIndex + 1
            val currentQuestion: Question = questions[currentQuestionIndex]
            updateState(
                currentQuestion = currentQuestion,
                currentQuestionIndex = currentQuestionIndex,
                newPoint = newPoint
            )
        } else {
            _uiState.update{ currentState -> currentState.copy(isOver = true)}
        }
    }

    fun checkCorrectAnswer(givenAnswer: Answer?) : Int{
        if(givenAnswer is CorrectAnswer? || givenAnswer is CorrectAnswer){
            return 1
        }
        return 0
    }


    private fun updateState(currentQuestion: Question, currentQuestionIndex: Int, newPoint: Int){
        _uiState.update { currentState -> currentState.copy(
            currentQuestion = currentQuestion,
            score = _uiState.value.score + newPoint,
            questionIndex = currentQuestionIndex,

        ) }
    }
}