package com.example.chatify.domain

import com.example.chatify.data.helper.AuthHelper.Companion.authHelper
import com.example.chatify.data.helper.DatabaseHelper.Companion.dbHelper
import com.example.chatify.data.model.UserModel
import com.google.firebase.firestore.auth.User

class DataRepository {

    companion object{
        val repository = DataRepository()
    }
    suspend fun signUp(email:String,password:String)  = authHelper.chatSignUp(email = email, password = password)
    suspend fun signIn(email: String,password: String) = authHelper.chatSignIn(email = email, password = password)

    fun insertUser(userModel: UserModel) = dbHelper.insertUser(userModel)
    suspend fun readUser() : MutableList<UserModel> = dbHelper.readAllUser()

}