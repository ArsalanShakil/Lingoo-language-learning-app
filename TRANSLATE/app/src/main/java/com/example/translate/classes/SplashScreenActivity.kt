package com.example.translate.classes
//ARSALAN SHAKIL
//1910097

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.translate.R

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME: Long = 4000        //set the time for activity to be open for
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            //open Main activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME) //display this activity for some amount of time
    }
}