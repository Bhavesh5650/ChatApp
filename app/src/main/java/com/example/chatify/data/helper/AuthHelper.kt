package com.example.chatify.data.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthHelper {

    companion object{
        val authHelper = AuthHelper()
    }

    var user: FirebaseUser? = null
    val auth = FirebaseAuth.getInstance()


    suspend fun chatSignUp(email:String,password:String): String {
        var message = ""

        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            message = "Success"
        }.addOnFailureListener{
            message = it.message.toString()
        }.await()

        return message
    }

    suspend fun chatSignIn(email: String,password: String) : String
    {
        var message = ""

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            message = "Success"
        }.addOnFailureListener{
            message = it.message.toString()
        }.await()

        return message
    }

    fun getCurrentUser()
    {
        user = auth.currentUser
    }

    fun logOut()
    {
        auth.signOut()
    }
}