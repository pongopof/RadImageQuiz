package com.example.radimagequiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.data.Answer
import com.example.data.Question
import com.example.data.QuestionBank
import com.example.radimagequiz.ui.layout.ProgramViewModel
import com.example.radimagequiz.ui.layout.QuestionPane
import com.example.radimagequiz.ui.layout.ResultScreen
import com.example.radimagequiz.ui.layout.StartScreen

enum class ScreenNavigator(){
    Start,
    QuestionPane,
    ResultPane
}

@Composable
fun RadQuizApp(gameViewModel: ProgramViewModel){
    val navController = rememberNavController()
    val uiState by gameViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = ScreenNavigator.Start.name){
        composable(route = ScreenNavigator.Start.name){
            StartScreen(
               goToRandomQuestions = { navController.navigate(route = ScreenNavigator.QuestionPane.name) })
        }
        composable(route = ScreenNavigator.QuestionPane.name){
            QuestionPane(
                question = uiState.currentQuestion?: throw IllegalArgumentException ("xList was empty"),
                nextButtonClicked = {
                    if (!uiState.isOver){
                    gameViewModel.nextQuestion()
                    }  else {
                        navController.navigate(ScreenNavigator.ResultPane.name)
                    }
                },
                uiState
            )
        }
        composable(route = ScreenNavigator.ResultPane.name){
            ResultScreen(score = uiState.score, numberOfQuestions = gameViewModel.questions.size)
        }
    }
}