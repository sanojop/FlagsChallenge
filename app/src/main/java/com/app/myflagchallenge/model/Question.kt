package com.app.myflagchallenge.model

data class Question(
    val id: Int,
    val question: String,
    val flagImage: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctOption: Int
)
