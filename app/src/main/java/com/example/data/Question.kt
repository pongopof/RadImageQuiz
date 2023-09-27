package com.example.data

class Question(image : RadImage, questionText: String, wrongAnswer1: String, wrongAnswer2 : String, wrongAnswer3: String)
{
    val title: String = image?.description ?: "INCORRECT"
    val image: RadImage = image
    val text: String = questionText
    val answers: MutableList<Answer> = mutableListOf(CorrectAnswer(image.description), WrongAnswer(wrongAnswer1), WrongAnswer(wrongAnswer2), WrongAnswer(wrongAnswer3))//first answer is correct
}