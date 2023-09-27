package com.example.radimagequiz.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ResultScreen(score: Int, numberOfQuestions: Int){
    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        Column {
            Text(text = "Your score: $score / $numberOfQuestions")
        }
    }
}