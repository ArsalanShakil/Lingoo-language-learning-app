package com.example.translate.classes
//ARSALAN SHAKIL
//1910097



import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.translate.R
import kotlinx.android.synthetic.main.activity_quiz.*

@Suppress("DEPRECATION")

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private var globalCurrentPosition: Int = 1                      //current position of question
    private var globalQuestionList: ArrayList<Question>? = null     //save the question in the array
    private var globalSelectedOptionPosition: Int = 0               //the selected option's position
    private var globalCorrectAnswers: Int = 0                       //count the number of correct answers
    private var soundPoolWrong: SoundPool? = null                   //sound pool for the wrong answer
    private var soundPoolRight: SoundPool? = null                   //sound pool for the right answer
    private var soundPoolComplete: SoundPool? = null                //sound pool for completing the quiz
    private val soundId = 1
    private var correctAnswer = 0                                   //save the id of the correct asnwer
    val ids: ArrayList<Int> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Take Quiz"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        //put the question and options from the object in globalQuestionList
        globalQuestionList = Constants.getQuestions()
        globalQuestionList!!.shuffle()
        globalCorrectAnswers = 0
        setQuestion()

        question_option_one.setOnClickListener(this)  //when option one is pressed
        question_option_two.setOnClickListener(this)    //when option two is pressed
        question_option_three.setOnClickListener(this)  //when option three is pressed
        question_option_four.setOnClickListener(this)   //when option four is pressed
        submit_button.setOnClickListener(this)      //when submit button is pressed

        soundPoolWrong = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPoolWrong!!.load(baseContext, R.raw.wrong_answer, 1)   //load the sound in the soundPoolWrong variable
        soundPoolRight = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPoolRight!!.load(baseContext, R.raw.right_answer, 1)   //load the sound in the soundPoolRight variable
        soundPoolComplete = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPoolComplete!!.load(baseContext, R.raw.quiz_completed, 1)   //load the sound in the soundPoolComplete variable

    }

    //go back when the back button is pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //set the questions to their default style
    private fun setQuestion() {
        val question = globalQuestionList!![globalCurrentPosition - 1]
        defaultOption()
        if (globalCurrentPosition == globalQuestionList!!.size) {
            submit_button.text = "Finish" //if its the last question then change the submit button to finnish
        } else {
            submit_button.text = "Submit" //if there are more question left keep the submit button
        }

        progress_bar.progress = globalCurrentPosition   //increase the progress in the progress bar
        progressBar_text.text = "$globalCurrentPosition" + "/" + progress_bar.max

        question_text.text = question!!.question    //set the text to question from the object

        question_option_one.text = question.optionOne //set the text to option one
        question_option_two.text = question.optionTwo   //set the text to option two
        question_option_three.text = question.optionTree    //set the text to option three
        question_option_four.text = question.optionFour     //set the text to option three



    }
    //the default option when the question is opened
    private fun defaultOption() {
        val options = ArrayList<TextView>()
        options.add(0, question_option_one)
        options.add(1, question_option_two)
        options.add(2, question_option_three)
        options.add(3, question_option_four)

        for (option in options) {
            //set the text color
            option.setTextColor(Color.parseColor("#7A8089"))
            //set the background to default
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_background
            )
        }

    }

    //when button or options are clicked
    override fun onClick(p0: View?) {

        //to close the activity
        val clear = Runnable { finish() }
        val handler = Handler()

        when (p0?.id) {
            //when option one is pressed set the background to selected option
            R.id.question_option_one -> {
                selectedOption(question_option_one, 1)
            }
            //when option two is pressed set the background to selected option
            R.id.question_option_two -> {
                selectedOption(question_option_two, 2)
            }
            //when option three is pressed set the background to selected option
            R.id.question_option_three -> {
                selectedOption(question_option_three, 3)
            }
            //when option four is pressed set the background to selected option
            R.id.question_option_four -> {
                selectedOption(question_option_four, 4)
            }
            //when submit is pressed
            R.id.submit_button -> {
                //check if globalSelectedPosition is equal to 0
                if (globalSelectedOptionPosition == 0) {
                    globalCurrentPosition++  //increase the current position to the next question

                    when {
                        //when there are more questions left in the list
                        globalCurrentPosition <= globalQuestionList!!.size -> {
                            setQuestion()   //set the next question
                        }
                        //if no more question left in the list
                        else -> {

                            val intent = Intent(this, QuizCompleteSplashActivity::class.java)
                            //send the total number of correct answers to the QuizCompleteSplashActivity
                            intent.putExtra(Constants.CORRECT_ANSWERS, globalCorrectAnswers)
                            //send the total number of questions to the QuizCompleteSplashActivity
                            intent.putExtra(Constants.TOTAL_QUESTIONS, 9)
                            //send the arraylist of ids of correct answers to the QuizCompleteSplashActivity
                            intent.putExtra("ids", ids)
                            //start the QuizCompleteSplashActivity
                            startActivity(intent)
                            //play the quiz completed sound
                            playSoundComplete()

                            //delay the finnish() by 3 seconds
                            handler.postDelayed(clear, 3000)
                        }
                    }
                } else {
                    val question = globalQuestionList?.get(globalCurrentPosition - 1)
                    //if the question is not correct
                    if (question!!.correctAnswer != globalSelectedOptionPosition) {
                        //set the background red as the wrong answer
                        answerView(globalSelectedOptionPosition, R.drawable.wrong_option_background)
                        playSoundWrongAnswer()  //play the wrong answer sound

                    } else {
                        correctAnswer = question.id     //save the id of the correct asnwer
                        ids.add(correctAnswer)      //add the id into an array
                        globalCorrectAnswers++     //increase the correct answer counter
                        playSoundRightAnswer()      //play the right answer sound
                    }
                    //set the background to green when correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_background)
                    //if no more questions left
                    if (globalCurrentPosition == globalQuestionList!!.size) {
                        //change the submit button to finish
                        submit_button.text = "Finish"
                    } else {
                        //change the submit button to next question
                        submit_button.text = "Next Question"
                    }
                    globalSelectedOptionPosition = 0    //when no selected option set the selected option to 0

                }
            }
        }
    }
    //when selected option
    private fun selectedOption(tv: TextView, selectedOptionNum: Int) {
        //set the option to default for a moment
        defaultOption()
        globalSelectedOptionPosition = selectedOptionNum
        //set the background to selected option
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_background
        )
    }

    private fun answerView(answer: Int, drawableView: Int) {
        //show answer the correct answer
        when (answer) {
            1 -> {
                //when answer is one set background to option one
                question_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                //when answer is two set background to option one
                question_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                //when answer is three set background to option one
                question_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                //when answer is four set background to option one
                question_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    //play the wrong answer sound
    private fun playSoundWrongAnswer() {
        soundPoolWrong?.play(soundId, 1F, 1F, 0, 0, 1F)
    }

    //play the right answer sound
    private fun playSoundRightAnswer() {
        soundPoolRight?.play(soundId, 1F, 1F, 0, 0, 1F)
    }

    //play the quiz complete sound
    private fun playSoundComplete() {
        soundPoolComplete?.play(soundId, 1F, 1F, 0, 0, 1F)
    }

    override fun onDestroy() {
        super.onDestroy()
        //when activity is destroyed
        //release the data from sound variables
        soundPoolRight?.release()
        soundPoolRight = null   //set variable to null
        soundPoolWrong?.release()
        soundPoolWrong = null   //set variable to null
        soundPoolComplete?.release()
        soundPoolComplete = null    //set variable to null
    }


}