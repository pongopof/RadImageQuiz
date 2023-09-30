package com.example.radimagequiz.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.window.Popup
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.Answer
import com.example.data.Question
import com.example.data.QuestionBank
import com.example.radimagequiz.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionPane(
    question: Question,
    nextButtonClicked: () -> Unit,
    uiState: AppUiState,
    fullSizeOpen: () -> Unit
){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Question") },
                actions = {
                    IconButton(onClick = fullSizeOpen ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_image_aspect_ratio_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
                QuestionPaneGrid(
                    innerPadding = innerPadding,
                    question = question,
                    nextButtonClicked = nextButtonClicked,
                    uiState = uiState)
    }
}




@Composable
fun QuestionPaneGrid(
    innerPadding: PaddingValues,
    question: Question,
    nextButtonClicked: () -> Unit,
    uiState: AppUiState
){

    var selectedItem by remember(question) {mutableStateOf<Answer?>(null)}


    Column(modifier = Modifier.padding(innerPadding)) {
        Box(modifier = Modifier)
        {
            Image(
                painter = painterResource(id = question.image.imageId),
                contentDescription = question.image.description,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
        Text(text = question.text)
        Box() {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                question.shuffledAnswers.forEach{ answer ->
                    Card(modifier = Modifier
                        .selectableGroup()
                        .selectable(
                            selected = (selectedItem == answer),
                            onClick = {
                                selectedItem = answer
                                uiState.currentAnswer = answer
                            }
                        )
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(text = answer.answerText)
                            Spacer(modifier = Modifier.weight(1f))
                            RadioButton(
                                selected = (selectedItem == answer),
                                onClick = {
                                    selectedItem = answer
                                    uiState.currentAnswer = answer
                                }
                            )
                        }
                    }
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

@Preview
@Composable
fun Prew(){
    QuestionPane(QuestionBank().questions.get(0), {}, AppUiState(), {})
}