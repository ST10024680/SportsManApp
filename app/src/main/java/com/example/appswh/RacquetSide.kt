package com.example.appswh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RacquetSide : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_racquet_side)

        // Initialize SQLiteHelper and views
        dbHelper = SQLiteHelper(this)

        val btnRacquetConfirm = findViewById<Button>(R.id.btnRacquetConfirm)

        btnRacquetConfirm.setOnClickListener {
            val services = "RACQUET"
            dbHelper.addData(services)
            // Switching to booking activity
            val intent = Intent(this,ConfirmationSide::class.java)
            startActivity(intent)

        }
    }
}