package com.example.chatify.view.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatify.R
import com.example.chatify.data.helper.DatabaseHelper.Companion.dbHelper
import com.example.chatify.data.model.UserModel
import com.example.chatify.databinding.ActivityAllUserShowBinding
import com.example.chatify.view.adapter.UsersAdapter
import com.example.chatify.viewmodel.UserViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class AllUserShowActivity : AppCompatActivity() {

    private lateinit var adapter: UsersAdapter
    private lateinit var binding:ActivityAllUserShowBinding
    var userList = mutableListOf<UserModel>()
    val viewModel by viewModels<UserViewModel>()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAllUserShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        adapter = UsersAdapter(userList)
        binding.newChatRV.adapter = adapter

        GlobalScope.launch {
            withContext(Dispatchers.Main)
            {
                userList = viewModel.readAllUser()
                adapter.changeData(userList)
                Log.i("TAG", "onCreate:====== UserList =  $userList ")
            }
        }
        initBackClick()
    }

    private fun initBackClick()
    {
        binding.newChatBackBtn.setOnClickListener {
            finish()
        }
    }

}