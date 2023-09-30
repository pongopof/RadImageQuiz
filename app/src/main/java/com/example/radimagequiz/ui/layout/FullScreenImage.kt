package com.example.radimagequiz.ui.layout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Popup
import com.example.data.Question
import com.example.radimagequiz.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenImage(
    question: Question,
    fullScreenButtonClicked: () -> Unit
){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Question") },
                actions = {
                    IconButton(onClick = fullScreenButtonClicked) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_image_aspect_ratio_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    Log.d("FullScreenImage.kt" , "offsetX: $offsetX, offsetY: $offsetY")

    Box(modifier = Modifier
        .padding(innerPadding)
        .pointerInput(Unit){
        awaitEachGesture {
            awaitFirstDown()
            do {
                val event = awaitPointerEvent()
                scale *= event.calculateZoom()
                val offset = event.calculatePan()
                offsetX += offset.x
                offsetY += offset.y
            } while (event.changes.any { it.pressed})
        }
    }){
        Image(
            painter = painterResource(id = question.image.imageId),
            contentDescription = question.image.description,
            modifier = Modifier
                .fillMaxHeight()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ),

            contentScale = ContentScale.FillHeight
        )
    }
    }
}

