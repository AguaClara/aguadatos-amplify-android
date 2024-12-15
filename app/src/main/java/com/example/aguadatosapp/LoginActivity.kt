package com.example.aguadatosapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
                        Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.i("LoginActivity", "Additional steps required: ${result.nextStep.signInStep}")
                        Toast.makeText(this@LoginActivity, "Further steps required: ${result.nextStep.signInStep}", Toast.LENGTH_LONG).show()
                    }
                } catch (error: Exception) {
                    Log.e("LoginActivity", "Login failed", error)
                    Toast.makeText(this@LoginActivity, "Login failed: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}