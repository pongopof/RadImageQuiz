package com.example.radimagequiz.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.data.Answer
import com.example.data.CorrectAnswer
import com.example.data.Question

@Composable
fun ResultScreen(score: Int, numberOfQuestions: Int, uiState: AppUiState){
    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        LazyColumn {
            val questions = uiState.answeredQuestions.keys.toList()
            items(questions.size) { i ->
                SingleElementResult(questions[i] ,uiState.answeredQuestions[questions[i]])
            }
        }
    }
}
@Composable
fun SingleElementResult(question: Question?, answer: Answer?){
    Card (){
        Column {
            Box(modifier = Modifier){
                Image(
                    painter = painterResource(id = question!!.image.imageId),
                    contentDescription = null
                )
            }
            Row {
                Text(text = question!!.answers[0].answerText)
                Text(text = if (answer is CorrectAnswer) "1/1" else "0/1")
            }
        }
    }
}