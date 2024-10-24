package com.example.chatify.data.helper

import android.util.Log
import com.example.chatify.data.helper.AuthHelper.Companion.authHelper
import com.example.chatify.data.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.tasks.await

class DatabaseHelper {

    companion object{
        val dbHelper = DatabaseHelper()
    }

    private val db = FirebaseFirestore.getInstance()

    fun insertUser(userModel: UserModel)
    {
        authHelper.getCurrentUser()
            db.collection("Users").document(authHelper.user!!.uid)
            .set(userModel)
        Log.d("TAG", "insertUser:================================$userModel ")
    }

    suspend fun readAllUser() : MutableList<UserModel>
    {

        var userList = mutableListOf<UserModel>()

        val snapshot=db.collection("Users")
            .whereNotEqualTo("uid",authHelper.user!!.uid)
            .get().await()

        for(x in snapshot.documents)
        {
            val userModel = UserModel(name = x.data!!["name"].toString(), mobile = x.data!!["mobile"].toString(), uid = x.id)
            userList.add(userModel)
            Log.d("TAG", "readAllUser: ===================================$userList")
        }

        return userList
    }

    fun checkChat(clientId:String)
    {

    }



}