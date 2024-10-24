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
import com.example.chatify.data.helper.DatabaseHelper.Companion.dbHelper
import com.example.chatify.data.model.UserModel
import com.example.chatify.databinding.ActivityProfileDataBinding
import com.example.chatify.viewmodel.UserViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileDataActivity : AppCompatActivity() {

    lateinit var binding:ActivityProfileDataBinding
    val viewModel by viewModels<UserViewModel>()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.submitBtn.setOnClickListener{
            authHelper.getCurrentUser()

            val name = binding.edtName.text.toString()
            val mobile = binding.edtMobile.text.toString()
            val uid = authHelper.user!!.uid

            if(name.isEmpty())
            {
                binding.nameLayout.error = "Name Is Required"
            }
            else if(mobile.isEmpty())
            {
                binding.mobileLayout.error = "Mobile Is Required"
            }
            else
            {
                GlobalScope.launch {
                    withContext(Dispatchers.Main)
                    {
                        Log.d("TAG", "Profile Screen ======== UID : ${authHelper.user!!.uid} ")

                        val userModel = UserModel(name = name, mobile = mobile, uid = uid)

                        dbHelper.insertUser(userModel)

                        Log.i("TAG", "onCreate -----------$userModel ")
                    }
                }

                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }

        }
    }
}