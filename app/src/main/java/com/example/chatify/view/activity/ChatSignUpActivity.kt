package com.example.chatify.view.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatify.R
import com.example.chatify.databinding.ActivityChatSignUpBinding
import com.example.chatify.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatSignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivityChatSignUpBinding
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initSignInTextClick()
        initSignUpClick()

    }

    private fun initSignUpClick()
    {
        binding.signUpBtn.setOnClickListener {

            val email = binding.edtEmailUp.text.toString()
            val password = binding.edtPasswordUp.text.toString()

            if(email.isEmpty())
            {
                binding.emailLayoutUp.error = "Email Is Required"
            }
            else if(password.isEmpty())
            {
                binding.passwordLayoutUp.error = "Password Is Required"
            }
            else
            {
                GlobalScope.launch {
                    withContext(Dispatchers.Main){
                        var message = viewModel.signUp(email = email, password = password)

                        if(message=="Success")
                        {
                            Log.i("TAG", "onCreate: ==========$email $password")
                            startActivity(Intent(this@ChatSignUpActivity,ChatSignInActivity::class.java))
                            finish()
                        }
                        Log.d("TAG", "onCreate: ======= $message")
                    }
                }
            }

        }
    }

    private fun initSignInTextClick()
    {
        binding.signInBtnTxt.setOnClickListener {
            startActivity(Intent(this,ChatSignInActivity::class.java))
            finish()
        }
    }
}