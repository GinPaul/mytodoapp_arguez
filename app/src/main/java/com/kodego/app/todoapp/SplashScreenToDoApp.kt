package com.kodego.app.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenToDoApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_to_do_app)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3500L)
            startActivity(Intent(this@SplashScreenToDoApp, MainActivity::class.java))
            finish()

        }

    }
}