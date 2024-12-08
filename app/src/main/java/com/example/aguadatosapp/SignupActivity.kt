package com.example.aguadatosapp

import android.widget.EditText
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.amplifyframework.auth.AuthUserAttribute
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val nameEditText = findViewById<EditText>(R.id.nameField)
        val emailEditText = findViewById<EditText>(R.id.emailField)
        val passwordEditText = findViewById<EditText>(R.id.passwordField)
        val logInButton = findViewById<Button>(R.id.logInButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        logInButton.setOnClickListener {
            Log.i("Test","Main Page")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            if (name.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                signUpUser(name, password, email)
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUpUser(name: String, password: String, email: String) {
        // Set up user attributes with email
        val attributes = listOf(
            AuthUserAttribute(AuthUserAttributeKey.email(), email),
            AuthUserAttribute(AuthUserAttributeKey.name(), name)
        )
        val options = AuthSignUpOptions.builder()
            .userAttributes(attributes)
            .build()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = Amplify.Auth.signUp(email, password, options)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignupActivity, "Sign-up successful! Please check your email for a confirmation code.", Toast.LENGTH_LONG).show()

                    // Navigate to confirmation activity if necessary
                    val intent = Intent(this@SignupActivity, ConfirmSignUpActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    if (result.isSignUpComplete) {
                        Log.i("SignupActivity", "Sign-up succeeded")
                    } else {
                        Log.i("SignupActivity", "Sign-up requires confirmation")
                    }
                }
            } catch (error: Exception) {
                Log.e("SignupActivity", "Sign-up failed", error)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignupActivity, "Sign-up failed: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}