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

class ConfirmationSide : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper
    private lateinit var btnClickConfirm : Button
    private lateinit var textViewConfirm : TextView

            @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation_side)

        // Initialize SQLiteHelper and views
        dbHelper = SQLiteHelper(this)

        btnClickConfirm = findViewById(R.id.btnClickConfirm)
        textViewConfirm = findViewById(R.id.textViewConfirm)

        btnClickConfirm.setOnClickListener {
            // Fetch entries from the database and display them
            val entries = dbHelper.getAllData()
            if (entries.isNotEmpty()) {
                display(entries)
            } else {
                textViewConfirm.text = "No entries found."
            }


            Toast.makeText(this, "You have placed an appointment for your item to me serviced at Sportsman's Warehouse", Toast.LENGTH_SHORT).show()

        }
    }
    private fun display(entries: List<String>) {
        val stringBuilder = StringBuilder()
        for (entry in entries) {
            stringBuilder.append(entry).append("\n\n") // Adds space between entries
        }
        textViewConfirm.text = stringBuilder.toString()
    }

}