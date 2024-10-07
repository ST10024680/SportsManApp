package com.example.appswh

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "my_database_AppSW"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Users"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "UserName"
        const val COLUMN_SURNAME = "UserSurName"
        const val COLUMN_EMAIL = "UserEmail"
        const val COLUMN_PASSWORD = "UserPassword"
        const val COLUMN_SELECTION = "UserSelection"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_SURNAME TEXT, $COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT, $COLUMN_SELECTION TEXT")
        db.execSQL(createTable)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }



    fun insertData(name: String, surname: String, email: String, password: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_SURNAME, surname)
        values.put(COLUMN_SURNAME, email)
        values.put(COLUMN_SURNAME, password)


        db.insert(TABLE_NAME, null, values)
        db.close()

    }
    fun addData(services: String){
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_SELECTION, services)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }



    fun  checkUser(username: String, userPassword: String): Boolean{
        val db = readableDatabase
        val selection = "$COLUMN_SURNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, userPassword)
        val cursor  = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)

        val userExits = cursor.count > 0
        cursor.close()
        return userExits
    }
    fun getAllData(): List<String> {
        val dataList = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME ))
                dataList.add(name)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return dataList
    }
}
