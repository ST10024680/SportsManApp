package com.example.appswh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BothSideActivity : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_both_side)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initialize SQLiteHelper and views
        dbHelper = SQLiteHelper(this)

        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        btnConfirm.setOnClickListener {
            val services = "BOTH"
            val success = dbHelper.addData(services)

            if (success) {
                Toast.makeText(this, "Both services added successfully!", Toast.LENGTH_SHORT).show()

                // Switching to confirmation activity
                val intent = Intent(this, ConfirmationSide::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Failed to add services", Toast.LENGTH_SHORT).show()
            }
        }
    }
}