package com.bagusmerta.pokemonss.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bagusmerta.pokemonss.databinding.ScreenSplashBinding
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private var activitySplashBinding: ScreenSplashBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ScreenSplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding?.root)

        CoroutineScope(Dispatchers.Default).launch {
            delay(2000L)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashScreen , MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}