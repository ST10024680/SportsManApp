package com.example.appswh

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class RacquetSide : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper1
    private lateinit var selectedDate: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_racquet_side)

        // Initialize SQLiteHelper and views
        dbHelper = SQLiteHelper1(this)

        val btnRacquetConfirm = findViewById<Button>(R.id.btnRacquetConfirm)
        val btnChooseDate = findViewById<Button>(R.id.btnChooseDate)
        val textViewSelectedDate = findViewById<TextView>(R.id.textViewSelectedDate)

        // Set up the DatePicker
        btnChooseDate.setOnClickListener {
            showDatePickerDialog()
        }

        btnRacquetConfirm.setOnClickListener {
            if (::selectedDate.isInitialized) {  // Check if a date was selected
                val restringing = "Racquet Restring"
                val batKnocking = ""
                val userId = 1  // Replace with actual user ID

                val success = dbHelper.addData(selectedDate, restringing, batKnocking, userId)

                if (success) {
                    Toast.makeText(this, "Racquet services added successfully!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ConfirmationSide::class.java))
                } else {
                    Toast.makeText(this, "Failed to add services", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please select a date first", Toast.LENGTH_SHORT).show()
            }
           
        }
    }
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val textViewSelectedDate = findViewById<TextView>(R.id.textViewSelectedDate)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                textViewSelectedDate.text = selectedDate  // Update TextView with the selected date
            },
            year, month, day
        )
        datePickerDialog.show()
    }
}
