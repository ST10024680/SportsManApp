package com.example.appswh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterSide : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_side)

        // Initialize SQLiteHelper and views
        dbHelper = SQLiteHelper(this)

        val editTextRegisterName = findViewById<EditText>(R.id.editTextRegisterName)
        val editTextRegisterSurname = findViewById<EditText>(R.id.editTextRegisterSurname)
        val editTextRegisterEmail = findViewById<EditText>(R.id.editTextRegisterEmail)
        val editTextRegisterPassword = findViewById<EditText>(R.id.editTextRegisterPassword)
        val editTextRegicConfirmPassword = findViewById<EditText>(R.id.editTextRegicConfirmPassword)
        val btnSaveRegister = findViewById<Button>(R.id.btnSaveRegister)
        val textViewLoginHere = findViewById<TextView>(R.id.textViewLoginHere)

        btnSaveRegister.setOnClickListener {
            val email = editTextRegisterEmail.text.toString()
            val password = editTextRegisterPassword.text.toString()
            val confirmPassword = editTextRegicConfirmPassword.text.toString()

            // Simple validation
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()

            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()

            } else {
                val name = editTextRegisterName.text.toString()
                val surname = editTextRegisterSurname.text.toString()
                val email = editTextRegisterEmail.text.toString()
                val password = editTextRegicConfirmPassword.text.toString()



                // Handle registration logic here
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                val entryText = editTextRegisterName.text.toString()
                dbHelper.insertData(name, surname, email, password)
                finish() // Go back to the login screen
            }
        }

        textViewLoginHere.setOnClickListener {
            startActivity(Intent(this, LoginSide::class.java))
        }
    }
}