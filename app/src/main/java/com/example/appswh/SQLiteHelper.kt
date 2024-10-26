package com.example.appswh

import android.content.Context
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class SQLiteHelper(context: Context) {

    companion object {
        const val TABLE_NAME = "Users"
        const val COLUMN_ID = "UserID"
        const val COLUMN_NAME = "Name"
        const val COLUMN_EMAIL = "Email"
        const val COLUMN_PASSWORD = "PasswordHash"


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

    // Create the Users table in Azure SQL (if not exists)
    fun createTableIfNotExists() {
        val connection = getConnection()
        if (connection != null) {
            try {
                val createTableQuery = """
                    IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='$TABLE_NAME' AND xtype='U')
                    CREATE TABLE $TABLE_NAME (
                        $COLUMN_ID INT PRIMARY KEY IDENTITY,
                        $COLUMN_NAME VARCHAR(255),
                   
                        $COLUMN_EMAIL VARCHAR(255),
                        $COLUMN_PASSWORD VARCHAR(255),
                        
                    )
                """
                val statement: Statement = connection.createStatement()
                statement.executeUpdate(createTableQuery)
                connection.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            println("Connection failed!")
        }
    }

    // Insert user data into Azure SQL
    fun insertData(name: String, surname: String, email: String, password: String) {
        val connection = getConnection()
        if (connection != null) {
            try {
                val query = "INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_EMAIL, $COLUMN_PASSWORD) VALUES (?, ?, ?, ?)"
                val preparedStatement: PreparedStatement = connection.prepareStatement(query)
                preparedStatement.setString(1, name)
                preparedStatement.setString(2, email)
                preparedStatement.setString(3, password)
                preparedStatement.executeUpdate()
                connection.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            println("Connection failed!")
        }
    }



    // Check if a user exists in Azure SQL
    fun checkUser(email: String, password: String): Boolean {
        var userExists = false
        val connection = getConnection()
        if (connection != null) {
            try {
                val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
                val preparedStatement: PreparedStatement = connection.prepareStatement(query)
                preparedStatement.setString(1, email)
                preparedStatement.setString(2, password)
                val resultSet: ResultSet = preparedStatement.executeQuery()
                userExists = resultSet.next()  // Check if any user was found
                connection.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return userExists
    }

    // Get all user names from Azure SQL
    fun getAllData(): List<String> {
        val dataList = mutableListOf<String>()
        val connection = getConnection()
        if (connection != null) {
            try {
                val query = "SELECT $COLUMN_NAME FROM $TABLE_NAME"
                val statement: Statement = connection.createStatement()
                val resultSet: ResultSet = statement.executeQuery(query)
                while (resultSet.next()) {
                    val name = resultSet.getString(COLUMN_NAME)
                    dataList.add(name)
                }
                connection.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return dataList
    }
}
