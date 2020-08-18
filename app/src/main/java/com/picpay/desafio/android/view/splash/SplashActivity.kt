package com.picpay.desafio.android.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.view.main.MainActivity

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val handle = Handler()
        handle.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}