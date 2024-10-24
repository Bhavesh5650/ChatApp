package com.example.chatify.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatify.data.model.UserModel
import com.example.chatify.domain.DataRepository.Companion.repository
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    val mutableUserData = MutableLiveData<UserModel>()
    val userData : LiveData<UserModel> = mutableUserData

    var userList = mutableListOf<UserModel>()

    suspend fun signUp(email:String,password:String) : String
    {
        val msg = repository.signUp(email,password)
        Log.d("TAG", "signUp: ========== $email $password")

        return msg
    }

    suspend fun signIn(email: String,password: String) : String {
        val msg = repository.signIn(email,password)
        return msg
    }

    fun insertUser(userModel: UserModel)
    {
        viewModelScope.launch {
            repository.insertUser(userModel)
        }
    }

    suspend fun readAllUser() : MutableList<UserModel> = repository.readUser()
}