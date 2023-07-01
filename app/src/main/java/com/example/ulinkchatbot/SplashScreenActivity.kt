package com.example.ulinkchatbot

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)


        val uLinkImageView : ImageView = findViewById(R.id.ULINK)
        val uLinkTextView : TextView = findViewById(R.id.UlinkTextView)
        val loadAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        loadAnimation.duration = 2000
        uLinkImageView.startAnimation(loadAnimation)
        uLinkTextView.startAnimation(loadAnimation)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}