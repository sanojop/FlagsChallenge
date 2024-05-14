package com.app.myflagchallenge.utils
import com.app.myflagchallenge.model.Question


object Constants {
    const val FLAG_CHALLENGE = "MySharedPref"
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            1,
            "guess the country from the flag ?",
            "afgan",
            "India",
            "Nepal",
            "Afghanistan",
            "Japan",
            3
        )
        questionsList.add(question1)
        val question2 = Question(
            1,
            "guess the country from the flag ?",
            "canada",
            "India",
            "Canada",
            "China",
            "Japan",
            2
        )
        questionsList.add(question2)

        val question3 = Question(
            1,
            "guess the country from the flag ?",
            "egypt",
            "India",
            "Nepal",
            "China",
            "Egypt",
            4
        )
        questionsList.add(question3)
        val question4 = Question(
            1,
            "guess the country from the flag ?",
            "england",
            "England",
            "Nepal",
            "China",
            "Japan",
            1
        )
        questionsList.add(question4)

        val question5 = Question(
            2,
            "guess the country from the flag ?",
            "france",
            "England",
            "France",
            "China",
            "Japan",
            2
        )
        questionsList.add(question5)

        return questionsList
    }
}