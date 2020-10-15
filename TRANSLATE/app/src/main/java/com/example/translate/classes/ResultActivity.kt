package com.example.translate.classes
//ARSALAN SHAKIL
//1910097

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.translate.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    var count: Int = 0 //count the number of times result activity is opened
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        count++ //increment count by 1

        //get the data from QuizCompleteSplash activity for total number of correct answers
        val correctAns = intent.getIntExtra("correctAnswers", 0)
        //get the data from QuizCompleteSplash activity for total number of question
        val totalQues = intent.getIntExtra("totalQuestion", 9)
        //get the data from QuizCompleteSplash activity for list of ids of correct answers
        val idsList = intent.getSerializableExtra("ids") as ArrayList<*>?


        //display the result of correct answer / total questions
        show_result.text = "$correctAns / $totalQues"

        //if the goto home button is pressed
        findViewById<TextView>(R.id.goto_home).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //open Main activity
            startActivity(intent)
            //close this activity
            finish()
        }

        //if the goto home button is pressed
        findViewById<Button>(R.id.open_progress_button).setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            //send the data of total number of counts to Stats activity
            intent.putExtra("total", count)
            //send the list of ids of correct answers to Stats activity
            intent.putExtra("ids", idsList)
            //open the Stats activity
            startActivity(intent)
            //close this activity
            finish()

        }

    }

}