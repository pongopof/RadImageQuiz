package com.example.radimagequiz.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.radimagequiz.R


@Composable
fun StartScreen(
    goToRandomQuestions: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (modifier =  Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.x_ray_health_arm_doctors_preview),
            contentDescription = "X-ray hand"
        )
        Text(text = "Choose mode")
        Spacer(modifier = Modifier)
        Button(onClick = goToRandomQuestions) {
            Text("random questions")
        }
    }
}


@Preview
@Composable
fun StartPreview(){
    StartScreen(goToRandomQuestions = {})
}