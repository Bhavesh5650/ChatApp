package com.example.chatify.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatify.MainActivity
import com.example.chatify.R
import com.example.chatify.data.helper.AuthHelper.Companion.authHelper
import com.example.chatify.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            authHelper.getCurrentUser()
            if(authHelper.user==null)
            {
                startActivity(Intent(this,OnboardingActivity::class.java))
                finish()
            }
            else
            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
        },3000)
    }
}