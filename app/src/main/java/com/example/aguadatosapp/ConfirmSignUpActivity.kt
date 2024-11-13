package com.example.aguadatosapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConfirmSignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_confirm_sign_up)
//
//        val username = intent.getStringExtra("username") ?: return
//        val confirmationCodeEditText = findViewById<EditText>(R.id.confirmationCodeEditText)
//        val confirmButton = findViewById<Button>(R.id.confirmButton)
//
//        confirmButton.setOnClickListener {
//            val code = confirmationCodeEditText.text.toString().trim()
//
//            if (code.isNotEmpty()) {
//                confirmSignUp(username, code)
//            } else {
//                Toast.makeText(this, "Confirmation code is required", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun confirmSignUp(username: String, code: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val result = Amplify.Auth.confirmSignUp(username, code)
//                withContext(Dispatchers.Main) {
//                    if (result.isSignUpComplete) {
//                        Toast.makeText(this@ConfirmSignUpActivity, "Confirmation successful!", Toast.LENGTH_SHORT).show()
//                        finish() // Close the confirmation activity
//                    } else {
//                        Toast.makeText(this@ConfirmSignUpActivity, "Confirmation required", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } catch (error: Exception) {
//                Log.e("ConfirmSignUpActivity", "Confirmation failed", error)
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@ConfirmSignUpActivity, "Confirmation failed: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
    }
}
