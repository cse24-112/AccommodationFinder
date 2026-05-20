package com.example.accommodationfinder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.accommodationfinder.utils.SessionManager
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Initialize sample data on first app launch
        lifecycleScope.launch {
            SampleDataGenerator.populateDatabase(this@SplashActivity)
            
            Handler(Looper.getMainLooper()).postDelayed({
                if (SessionManager.isLoggedIn(this@SplashActivity)) {
                    // User is logged in, go to home
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } else {
                    // User not logged in, go to welcome screen
                    startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
                }
                finish()
            }, 2000) // 2 second delay
        }
    }
}
