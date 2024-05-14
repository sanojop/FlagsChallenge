package com.app.myflagchallenge.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.app.myflagchallenge.utils.Constants.FLAG_CHALLENGE


class Utilities private constructor() {

    companion object {

        @Volatile
        private var instance: Utilities? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Utilities().also { instance = it }
            }


        fun doSomething() = "Doing something"

        fun setIntervalToStart(context: Context, str: String) {
            val sharedPreferences = context.getSharedPreferences(FLAG_CHALLENGE, MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()
            // write all the data entered by the user in SharedPreference and apply
            myEdit.putString("DelayToStart", str)
            myEdit.apply()
        }

        fun getIntervalToStart(context: Context): String {
            var time_delay = ""
            val preferences = context.getSharedPreferences(Constants.FLAG_CHALLENGE, MODE_PRIVATE)
            time_delay = preferences.getString("DelayToStart", "00:00:00").toString()
            return time_delay
        }
    }
}