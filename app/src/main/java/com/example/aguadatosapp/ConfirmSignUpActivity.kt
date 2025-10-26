package com.example.aguadatosapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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

    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verification_code_popup)

        email = intent.getStringExtra("email") ?: return

        val confirmButton = findViewById<Button>(R.id.submit_button)
        confirmButton.setOnClickListener {
            val codeInput = findViewById<EditText>(R.id.verification_code_input).text.toString().trim()
            if (codeInput.isNotEmpty()) {
                confirmSignUp(email, codeInput)
            } else {
                Toast.makeText(this, getString(R.string.enter_confirmation_code), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun confirmSignUp(username: String, code: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = Amplify.Auth.confirmSignUp(username, code)
                withContext(Dispatchers.Main) {
                    if (result.isSignUpComplete) {
                        Toast.makeText(this@ConfirmSignUpActivity, getString(R.string.confirm_success), Toast.LENGTH_SHORT).show()
                        // Navigate to MainActivity after successful confirmation
                        val intent = Intent(this@ConfirmSignUpActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@ConfirmSignUpActivity, getString(R.string.confirm_required), Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (error: Exception) {
                Log.e("ConfirmSignUpActivity", "Confirmation failed", error)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ConfirmSignUpActivity, getString(R.string.confirm_fail,error.localizedMessage), Toast.LENGTH_LONG).show()
                    // Show the verification popup again if needed
                    makeVerificationPopup(code)
                }
            }
        }
    }

    private fun makeVerificationPopup(expectedCode: String) {
        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.verification_code_popup, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val codeInput = dialogView.findViewById<EditText>(R.id.verification_code_input)

        dialogView.findViewById<Button>(R.id.close_button).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.submit_button).setOnClickListener {
            val enteredCode = codeInput.text.toString()
            if (enteredCode == expectedCode) {
                Toast.makeText(this, getString(R.string.verification_success), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
                finish()
            } else {
                Toast.makeText(this, getString(R.string.verification_wrong), Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}