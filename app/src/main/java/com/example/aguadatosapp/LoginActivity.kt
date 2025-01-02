package com.example.aguadatosapp

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.emailField)
        val passwordEditText = findViewById<EditText>(R.id.passwordField)
        val logInButton = findViewById<Button>(R.id.logInButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        //sign out any current user before attempting to login
        lifecycleScope.launch {
            try {
                Amplify.Auth.signOut()
                Log.d("msg", "Successfully signed out")
            } catch (error: Exception) {
                Log.e("msg", "Failed to sign out: ${error.message}", error)
            }
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        logInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Launch a coroutine to handle the sign-in process
            lifecycleScope.launch {
                try {
                    val result = Amplify.Auth.signIn(email, password)

                    // Check the next step to determine if the sign-in is complete
                    if (result.nextStep.signInStep?.name == "DONE") {
                        Log.i("LoginActivity", "Login succeeded")
                        Toast.makeText(this@LoginActivity, getString(R.string.login_msg), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.i("LoginActivity", "Additional steps required: ${result.nextStep.signInStep}")
                        Toast.makeText(this@LoginActivity, getString(R.string.further_steps_required,result.nextStep.signInStep), Toast.LENGTH_LONG).show()
                    }
                } catch (error: Exception) {
                    Log.e("LoginActivity", "Login failed", error)
                    Toast.makeText(this@LoginActivity, getString(R.string.login_failed,error.localizedMessage), Toast.LENGTH_LONG).show()
                }
            }
        }

        val passwordField = findViewById<EditText>(R.id.passwordField)
        val togglePasswordVisibility = findViewById<ImageView>(R.id.togglePasswordVisibility)

        var isPasswordVisible = false

        togglePasswordVisibility.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                // Show password
                passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                togglePasswordVisibility.setImageResource(R.drawable.ic_eye_open) // Replace with your "eye open" icon
            } else {
                // Hide password
                passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                togglePasswordVisibility.setImageResource(R.drawable.ic_eye_closed) // Replace with your "eye closed" icon
            }
            // Keep cursor at the end of the text
            passwordField.setSelection(passwordField.text.length)
        }
    }
}