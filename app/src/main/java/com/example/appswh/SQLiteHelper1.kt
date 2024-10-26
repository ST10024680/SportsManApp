package com.example.appswh

import android.content.Context
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class SQLiteHelper1(context: Context) {

    companion object {
        const val TABLE_NAME = "Booking"
        const val COLUMN_ID = "BookingID"
        const val COLUMN_DATE = "BookingDate"
        const val COLUMN_RESTRINGING = "Restringing"
        const val COLUMN_BAT = "BatKnocking"
        const val COLUMN_UserID = "UserID"


        // Connection string to Azure SQL
        const val connectionUrl = "Server=tcp:st10024680.database.windows.net,1433;Initial Catalog=WEBAPP;Persist Security Info=False;User ID=st10024680;Password=Harpsgeir15;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;"
    }

    // Establish the connection to Azure SQL
    private fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(connectionUrl)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    fun addData(bookingDate: String, restringing: String, batKnocking: String, userId: Int): Boolean {
        var success = false
        val connection = getConnection()
        if (connection != null) {
            try {
                val query = "INSERT INTO $TABLE_NAME ($COLUMN_DATE, $COLUMN_RESTRINGING, $COLUMN_BAT, $COLUMN_UserID) VALUES (?, ?, ?, ?)"
                val preparedStatement: PreparedStatement = connection.prepareStatement(query)
                preparedStatement.setString(1, bookingDate)
                preparedStatement.setString(2, restringing)
                preparedStatement.setString(3, batKnocking)
                preparedStatement.setInt(4, userId)

                val rowsAffected = preparedStatement.executeUpdate()
                success = rowsAffected > 0
                connection.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return success
    }

}
