package com.example.radimagequiz.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.data.Answer
import com.example.data.Question
import com.example.data.QuestionBank

@Composable
fun QuestionPane(
    question: Question,
    nextButtonClicked: () -> Unit,
    uiState: AppUiState
){
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var selectedItem by remember(question) {mutableStateOf<Answer?>(null)}
    var answersOrder: List<Answer> = rememberSaveable (question)  {
        question.answers.shuffled()
    }
    Column (modifier = Modifier) {
        Box (modifier = Modifier.pointerInput(Unit) {
            awaitEachGesture {
                    awaitFirstDown()
                    do {
                        val event = awaitPointerEvent()
                        scale *= event.calculateZoom()
                        val offset = event.calculatePan()
                        offsetX += offset.x
                        offsetY += offset.y
                    } while (event.changes.any { it.pressed })

            }

        }){
            Image(
                painter = painterResource(id = question.image.imageId),
                contentDescription = question.image.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offsetX,
                        translationY = offsetY
                    ),
                contentScale = ContentScale.FillWidth
            )
        }

        Text(text = question.text)
        Box(){
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                answersOrder.forEach {item ->
                    AnswerItem(
                        selected = (selectedItem == item),
                        onClick = {
                            selectedItem = item
                            uiState.currentAnswer = item
                        },
                        answer = item
                    )
                }
            }
        }
        BottomBar(nextButtonClicked = nextButtonClicked)
    }
}

@Composable
fun BottomBar(nextButtonClicked: () -> Unit){
    Box (modifier = Modifier.wrapContentHeight(Alignment.Bottom)){
        Row() {
            Button(
                onClick = nextButtonClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Next")
            }

        }
    }
}

@Composable
fun AnswerItem(
    selected: Boolean,
    onClick: () -> Unit,
    answer: Answer
){
    Card(modifier = Modifier
        .selectableGroup()
        .selectable(
            selected = selected,
            onClick = onClick
        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(text = answer.answerText)
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(
                selected = selected,
                onClick = onClick)
        }
    }
}



@Preview
@Composable
fun Prew(){
    QuestionPane(QuestionBank().questions.get(0), {}, AppUiState())
}