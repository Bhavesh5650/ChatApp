package com.example.chatify.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatify.MainActivity
import com.example.chatify.R
import com.example.chatify.data.helper.AuthHelper.Companion.authHelper
import com.example.chatify.databinding.ActivityChatSignInBinding
import com.example.chatify.viewmodel.UserViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ChatSignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivityChatSignInBinding
    val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initSignUpTextClick()
        initSignInClick()

    }

    private fun initSignInClick()
    {
        binding.signInBtn.setOnClickListener {
            authHelper.getCurrentUser()
            Log.d("TAG", "SignIn Screen:======== UID ${authHelper.user?.uid}")
            val email = binding.edtEmailIn.text.toString()
            val password = binding.edtPasswordIn.text.toString()

            if(email.isEmpty())
            {
                binding.emailLayoutIn.error = "Email Is Required"
            }
            else if(password.isEmpty())
            {
                binding.passwordLayoutIn.error = "Password Is Required"
            }
            else
            {
                GlobalScope.launch {
                    val message = viewModel.signIn(email,password)

                    if(message=="Success")
                    {
                        startActivity(Intent(this@ChatSignInActivity, ProfileDataActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    private fun initSignUpTextClick()
    {
        binding.signUpBtnTxt.setOnClickListener {
            startActivity(Intent(this,ChatSignUpActivity::class.java))
            finish()
        }
    }
}