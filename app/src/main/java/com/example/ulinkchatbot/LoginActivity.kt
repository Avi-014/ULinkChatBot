package com.example.ulinkchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login_btn : ImageView = findViewById(R.id.login_btn)
        val signup_btn : ImageView = findViewById(R.id.signup_btn)


        login_btn.setOnClickListener{
            startActivity(Intent(this@LoginActivity , ChatActivity::class.java))
            finish()
        }
        signup_btn.setOnClickListener{
            startActivity(Intent(this@LoginActivity , SignUp::class.java))
            finish()
        }
    }
}