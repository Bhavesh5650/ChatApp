package com.example.chatify

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatify.data.helper.AuthHelper.Companion.authHelper
import com.example.chatify.databinding.ActivityMainBinding
import com.example.chatify.view.activity.AllUserShowActivity
import com.example.chatify.view.activity.ChatSignInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initClickOfFAB()
        initLogOutClick()
    }

    private fun initClickOfFAB()
    {
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this,AllUserShowActivity::class.java))
        }
    }

    private fun initLogOutClick()
    {
        binding.logOutBtn.setOnClickListener {
            authHelper.logOut()
            startActivity(Intent(this,ChatSignInActivity::class.java))
            finish()
        }
    }
}