package com.app.myflagchallenge.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.app.myflagchallenge.R
import com.app.myflagchallenge.utils.Utilities
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       // val str: String = txt_hr.getText().toString()

    }
    @SuppressLint("SimpleDateFormat")
    fun saveDuration (view: View){
        var hr = findViewById(R.id.txt_hr) as EditText
        var min = findViewById(R.id.txt_min) as EditText
        var sec= findViewById(R.id.txt_sec) as EditText
        val sb = StringBuilder()
        sb.append(hr.text).append(":").append(min.text).append(":").append(sec.text)
        Utilities.setIntervalToStart( this,sb.toString())
        val intent = Intent(this, FlagChallengeActivity::class.java)
      /*  val formatter: DateFormat = SimpleDateFormat("hh:mm:ss a")
        val date: Date = formatter.parse(stringtime)*/
        //intent.putExtra("starttime", stringtime)

        startActivity(intent)
    }
}