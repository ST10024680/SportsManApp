package com.example.appswh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BatSide : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bat_side)


        // Initialize SQLiteHelper and views
        dbHelper = SQLiteHelper(this)

        val btnBatConfirm = findViewById<Button>(R.id.btnBatConfirm)

        btnBatConfirm.setOnClickListener {
            val services = "BAT"
            val success = dbHelper.addData(services)

            if (success) {
                Toast.makeText(this, "Racquet service added successfully!", Toast.LENGTH_SHORT).show()

                // Switching to confirmation activity
                val intent = Intent(this, ConfirmationSide::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Failed to add racquet service", Toast.LENGTH_SHORT).show()
            }
        }
    }
}