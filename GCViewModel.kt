package com.example.gochat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.credentials.exceptions.ClearCredentialCustomException
import androidx.lifecycle.ViewModel
import com.example.gochat.data.Event
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import java.lang.Exception

@HiltViewModel
class GCViewModel @Inject constructor(
    val auth: FirebaseAuth
): ViewModel() {
    init {

    }

    var inProcess = mutableStateOf(false)
    val eventMutableState = mutableStateOf<Event<String>?>(null)

    fun signUp(name: String, number: String, email: String, password: String) {
        inProcess.value = true
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {

                Log.d("TAG", "signUp: User Logged In")
            } else {

            }
        }
    }


    fun handleException(exception: Exception? = null, customMessage: String = "") {
        Log.e("GoChatApp", "GoChat exception:", exception)
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isNullOrEmpty()) errorMsg else customMessage

        eventMutableState.value = Event(message)
        inProcess.value = false
    }
}