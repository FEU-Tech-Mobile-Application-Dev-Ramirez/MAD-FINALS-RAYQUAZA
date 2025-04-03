package com.example.chronichealthtracker.data


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_USERS ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_USERNAME TEXT, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(username: String, password: String) {
        val db = writableDatabase
        val insertQuery = "INSERT INTO $TABLE_USERS ($COLUMN_USERNAME, $COLUMN_PASSWORD) VALUES ('$username', '$password')"
        db.execSQL(insertQuery)
        db.close()
    }

    fun checkUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = '$username' AND $COLUMN_PASSWORD = '$password'"
        val cursor = db.rawQuery(selectQuery, null)
        val userExists = cursor.count > 0
        cursor.close()
        db.close()
        return userExists
    }

    companion object {
        private const val DATABASE_NAME = "userDB"
        private const val DATABASE_VERSION = 1
        const val TABLE_USERS = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
    }
}
