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
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.data.Answer
import com.example.data.Question
import com.example.data.QuestionBank
import com.example.radimagequiz.ui.layout.FullScreenImage
import com.example.radimagequiz.ui.layout.ProgramViewModel
import com.example.radimagequiz.ui.layout.QuestionPane
import com.example.radimagequiz.ui.layout.ResultScreen
import com.example.radimagequiz.ui.layout.StartScreen

sealed class ScreenNavigator(val route: String){
    object Start: ScreenNavigator("start")
    object QuestionPane: ScreenNavigator("questionPane"){
        object QuestionGrid : ScreenNavigator("questionGrid")
        object FullScreen : ScreenNavigator ("fullScreen")
    }
    object ResultPane: ScreenNavigator("resultPane")
}

@Composable
fun RadQuizApp(gameViewModel: ProgramViewModel){
    val navController = rememberNavController()
    val uiState by gameViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = ScreenNavigator.Start.route){
        composable(route = ScreenNavigator.Start.route){
            StartScreen(
               goToRandomQuestions = { navController.navigate(route = ScreenNavigator.QuestionPane.route) })
        }
        navigation(
            route = ScreenNavigator.QuestionPane.route,
            startDestination = ScreenNavigator.QuestionPane.QuestionGrid.route) {
            composable(route = ScreenNavigator.QuestionPane.QuestionGrid.route) {
                QuestionPane(
                    question = uiState.currentQuestion ?: throw IllegalArgumentException("xList was empty"),
                    nextButtonClicked = {
                        if (!uiState.isOver) {
                            gameViewModel.nextQuestion()
                        } else {
                            navController.navigate(ScreenNavigator.ResultPane.route) } },
                    uiState = uiState,
                    fullSizeOpen = {navController.navigate(route = ScreenNavigator.QuestionPane.FullScreen.route)}
                )
            }
            composable(route = ScreenNavigator.QuestionPane.FullScreen.route){
                FullScreenImage(
                    question = uiState.currentQuestion?: throw IllegalArgumentException("xList was empty"),
                    fullScreenButtonClicked = {navController.navigate(route = ScreenNavigator.QuestionPane.QuestionGrid.route)}
                )
            }
        }
        composable(route = ScreenNavigator.ResultPane.route){
            ResultScreen(score = uiState.score, numberOfQuestions = gameViewModel.questions.size, uiState = uiState)
        }
    }
}