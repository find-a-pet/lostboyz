package com.mtotowamkwe.lostboyz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val SPLASH_SCREEN_TIME_OUT = 4000L

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = SplashScreenFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

        val worker = Executors.newSingleThreadScheduledExecutor()

        val runnable = Runnable {
            val intent: Intent = Intent(this, PetsActivity::class.java)
            startActivity(intent)
            finish()
        }
        worker.schedule(runnable, SPLASH_SCREEN_TIME_OUT, TimeUnit.MILLISECONDS)
    }
}