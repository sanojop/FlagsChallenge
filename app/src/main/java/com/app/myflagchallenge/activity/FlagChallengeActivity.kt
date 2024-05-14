package com.app.myflagchallenge.activity

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.myflagchallenge.R
import com.app.myflagchallenge.model.Question
import com.app.myflagchallenge.utils.Constants
import com.app.myflagchallenge.utils.Utilities
import java.util.concurrent.TimeUnit


class FlagChallengeActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private lateinit var option1 : TextView
    private lateinit var option2 : TextView
    private lateinit var option3 : TextView
    private lateinit var option4 : TextView
    private lateinit var txtQuestion : TextView
    private lateinit var txt_answer_status : TextView
    private lateinit var lay_options :LinearLayout
    private lateinit var txt_game_over : TextView
    private lateinit var lay_score : LinearLayout
    private lateinit var txt_score_value:TextView
    private lateinit var lay_question : LinearLayout
    private lateinit var txt_question_number: TextView
    private lateinit var main_timer: TextView
    private lateinit var img_flag: ImageView
    private var mTotalScore: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_flag_challenge)

        val duration = Utilities.getIntervalToStart(this)
        val textView = findViewById<View>(R.id.txt_duration) as TextView
        textView.text = duration
        val lay_timer = (findViewById<View>(R.id.lay_timer) as LinearLayout)
        lay_timer.visibility=View.VISIBLE
        lay_question = findViewById<View>(R.id.lay_question) as LinearLayout
        lay_question.visibility = View.GONE
        lay_options = findViewById(R.id.lay_options)
        lay_options.visibility = View.GONE
        txt_game_over = findViewById<View>(R.id.txt_game_over) as TextView
        txt_game_over.visibility = View.GONE
        lay_score = findViewById<View>(R.id.lay_score) as LinearLayout
        lay_score.visibility = View.GONE
        txt_answer_status = findViewById(R.id.txt_answer_status)
        txt_answer_status.visibility = View.GONE
        txt_score_value = findViewById(R.id.txt_score_value)
        txt_question_number = findViewById(R.id.txt_question_number)
        img_flag = findViewById(R.id.img_flag)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        txtQuestion = findViewById(R.id.txt_question)
        main_timer = findViewById(R.id.main_timer)
        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        val time = duration.split(":").toTypedArray()
        val min: Long = time[1].toLong()
        val sec: Long = time[2].toLong()
        val t = min * 60L + sec

        val result: Long = TimeUnit.SECONDS.toMillis(t)
        //textView.text =result.toString()
        Handler().postDelayed(/*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */Runnable {
            setQuestion()
            lay_timer.visibility = View.GONE
            lay_question.visibility = View.VISIBLE
            lay_options.visibility = View.VISIBLE

        }, result
        )
        defaultOptionsView()
        mQuestionList = Constants.getQuestions()


    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, option1)
        options.add(1, option2)
        options.add(2, option3)
        options.add(3, option4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.grey_border_custom
            )
        }

    }
    private fun setQuestion() {

        val question = mQuestionList!!.get(mCurrentPosition - 1)

        defaultOptionsView()

        enableOptions()

        txtQuestion.text = question.question
        txt_question_number.text = mCurrentPosition.toString()
        val drawableID = getResources().getIdentifier(
            question.flagImage, "drawable",
            packageName
        )

        img_flag.setImageDrawable(ContextCompat.getDrawable(this,drawableID));
        option1.text = question.optionOne
        option2.text = question.optionTwo
        option3.text = question.optionThree
        option4.text = question.optionFour
        txt_answer_status.visibility = View.GONE
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        val question = mQuestionList?.get(mCurrentPosition - 1)
        //Toast.makeText(this@FlagChallengeActivity, "11111"+question?.correctOption, Toast.LENGTH_LONG).show()
        //txtQuestion.text = question?.correctOption.toString() + " ," + mSelectedOptionPosition
        if (question!!.correctOption != mSelectedOptionPosition) {
            tv.setTextColor(Color.parseColor("#FB957F"))
            tv.setTypeface(tv.typeface, Typeface.BOLD)
            tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.orange_border_custom
            )
            mTotalScore+=0
            txt_answer_status.visibility = View.VISIBLE
            txt_answer_status.setTextColor(Color.parseColor("#FB957F"))
            txt_answer_status.text = "Wrong !!"

        }else {

            tv.setTextColor(Color.parseColor("#008000"))
            tv.setTypeface(tv.typeface, Typeface.BOLD)
            tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.green_border_custom
            )
            mTotalScore+=10
            txt_answer_status.visibility = View.VISIBLE
            txt_answer_status.text="Correct !!"
            txt_answer_status.setTextColor(Color.parseColor("#008000"))
        }

        Handler().postDelayed(
        Runnable {
            mCurrentPosition+=1
            if (mCurrentPosition != mQuestionList!!.size) {
                setQuestion()
                lay_options.visibility = View.VISIBLE
                txt_answer_status.visibility = View.GONE
                lay_score.visibility = View.GONE
            }else {
                lay_options.visibility = View.GONE
                txt_game_over.visibility = View.VISIBLE
                txt_answer_status.visibility = View.GONE
                lay_question.visibility = View.GONE
                lay_score.visibility = View.VISIBLE
                var score =  (mQuestionList!!.size-1) * 10
                txt_score_value.text =mTotalScore.toString() + "/"+score.toString()
                main_timer.text="00.00"
            }


        }, 1000
        )

    }

    fun disableOptions(){
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
    }
    fun enableOptions(){
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.option1 -> {
                selectedOptionView(option1, 1)
                disableOptions()
            }
            R.id.option2-> {
                selectedOptionView(option2, 2)
                disableOptions()
            }
            R.id.option3 -> {
                selectedOptionView(option3, 3)
                disableOptions()
            }
            R.id.option4 -> {
                selectedOptionView(option4, 4)
                disableOptions()
            }
        }
    }
}


