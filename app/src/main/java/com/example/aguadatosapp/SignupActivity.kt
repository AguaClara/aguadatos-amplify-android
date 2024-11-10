package com.example.aguadatosapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val logInButton = findViewById<Button>(R.id.logInButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        logInButton.setOnClickListener {
            Log.i("Test","Main Page")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            val code = "1234"
            makeVerificationPopup (code)
        }
    }

    private fun makeVerificationPopup (code : String) {
        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.verification_code_popup, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
        val codeInput = dialogView.findViewById<EditText>(R.id.verification_code_input)
        dialogView.findViewById<Button>(R.id.close_button).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.submit_button).setOnClickListener {
            if (codeInput.text.toString() == code) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
            }
            else {
                // TODO: Handle incorrect verification code
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}