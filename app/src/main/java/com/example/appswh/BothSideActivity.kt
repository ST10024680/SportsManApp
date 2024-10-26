package com.example.appswh

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BothSideActivity : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper1
    private lateinit var selectedDate: String  // To store selected date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_both_side)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = SQLiteHelper1(this)

        val btnChooseDate = findViewById<Button>(R.id.btnChooseDate)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        val textViewSelectedDate = findViewById<TextView>(R.id.textViewSelectedDate)

        // Set up the DatePicker
        btnChooseDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Save data on confirmation
        btnConfirm.setOnClickListener {
            if (::selectedDate.isInitialized) {  // Check if a date was selected
                val restringing = "Racquet Restring"
                val batKnocking = "Bat Knocking"
                val userId = 1  // Replace with actual user ID

                val success = dbHelper.addData(selectedDate, restringing, batKnocking, userId)

                if (success) {
                    Toast.makeText(this, "Both services added successfully!", Toast.LENGTH_SHORT).show()
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
