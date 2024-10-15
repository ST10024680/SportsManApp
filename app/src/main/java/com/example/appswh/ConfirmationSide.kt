package com.example.appswh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfirmationSide : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper
    private lateinit var btnClickConfirm: Button
    private lateinit var btnback: Button
    private lateinit var textViewConfirm: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_side)

        // Initialize SQLiteHelper and views
        dbHelper = SQLiteHelper(this)

        btnClickConfirm = findViewById(R.id.btnClickConfirm)
        btnback = findViewById(R.id.btnback)
        textViewConfirm = findViewById(R.id.textViewConfirm)

        btnClickConfirm.setOnClickListener {

                Toast.makeText(
                    this,
                    "You have placed an appointment for your item to be serviced at Sportsman's Warehouse",
                    Toast.LENGTH_SHORT
                ).show()


        }

        btnback.setOnClickListener {
            val intent = Intent(this, BookingSide::class.java)
            startActivity(intent)
        }
    }


}
