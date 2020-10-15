package com.example.translate.classes
//ARSALAN SHAKIL
//1910097

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.translate.R


@Suppress("DEPRECATION")
class QuizCompleteSplashActivity : AppCompatActivity() {
    //set the time for activity to be open for
    private val splashTIME: Long = 3300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_complete_splash)

        //get the data from Quiz activity for total number of correct answers
        val correct = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        //get the data from Quiz activity for total number of questions
        val total = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        //get the data from Quiz activity for list of ids of correct answers
        val idsList = intent.getSerializableExtra("ids") as ArrayList<*>?


        Handler().postDelayed({
            val intent = Intent(this, ResultActivity::class.java)
            //send the data of total number of question to Result activity
            intent.putExtra("totalQuestion", total)
            //send the data of total number of correct answers to Result activity
            intent.putExtra("correctAnswers", correct)
            //send the data of list of ids of correct answers to Result activity
            intent.putExtra("ids", idsList)
            //start the result activity
            startActivity(intent)
            finish()
        }, splashTIME) //show the activity for some amount of time
    }


}