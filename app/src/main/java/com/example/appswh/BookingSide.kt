package com.example.appswh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingSide : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_side)

        val checkBoxRac = findViewById<CheckBox>(R.id.checkBoxRac)
        val checkBoxBat = findViewById<CheckBox>(R.id.checkBoxBat)
        val checkBoxBoth = findViewById<CheckBox>(R.id.checkBoxBoth)
        val btnBooking = findViewById<Button>(R.id.btnBooking)


        btnBooking.setOnClickListener {
                when {
                    checkBoxBoth.isChecked -> {
                        Toast.makeText(this, "Processing both Racquet and Bat Knocking", Toast.LENGTH_SHORT).show()

                        // Switching to booking activity
                        val intent = Intent(this,BothSideActivity::class.java)
                        startActivity(intent)
                    }
                    checkBoxRac.isChecked -> {
                        Toast.makeText(this, "Processing to Racquet Restringing Page", Toast.LENGTH_SHORT).show()

                        // Switching to booking activity
                        val intent = Intent(this,RacquetSide::class.java)
                        startActivity(intent)
                    }
                    checkBoxBat.isChecked -> {
                        Toast.makeText(this, "Processing to Bat Knocking Page", Toast.LENGTH_SHORT).show()

                        // Switching to booking activity
                        val intent = Intent(this,BatSide::class.java)
                        startActivity(intent)
                    }
                    else -> {
                        Toast.makeText(this, "No options are selected", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}