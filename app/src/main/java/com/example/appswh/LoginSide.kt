package com.example.appswh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginSide : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_side)

        // Initialize SQLiteHelper and UI elements
        dbHelper = SQLiteHelper(this)

        val textViewLoginEmail = findViewById<EditText>(R.id.editTextLoginEmail)
        val editLoginPassword = findViewById<EditText>(R.id.editLoginPassword)
        val btnSaveLogin = findViewById<Button>(R.id.btnSaveLogin)
        val textViewRegister = findViewById<TextView>(R.id.textViewRegister)

        btnSaveLogin.setOnClickListener {

            val email = textViewLoginEmail.text.toString().trim()
            val password = editLoginPassword.text.toString().trim()

            // Simple validation
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Perform the login check in the database
                loginDatabase(email, password)
            }
        }

        textViewRegister.setOnClickListener {
            startActivity(Intent(this, RegisterSide::class.java))
        }
    }

    private fun loginDatabase(username: String, userPassword: String) {
        val userExists = dbHelper.checkUser(username, userPassword)
        if (userExists) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

            // Switching to booking activity after successful login
            val intent = Intent(this, BookingSide::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
