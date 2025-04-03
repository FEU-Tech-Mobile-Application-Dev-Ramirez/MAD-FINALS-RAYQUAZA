package com.example.chronichealthtracker.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Appointment::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                val cursor = database.query("PRAGMA table_info(appointment)")
                var columnExists = false


                while (cursor.moveToNext()) {
                    val columnNameIndex = cursor.getColumnIndex("name")
                    if (columnNameIndex != -1) {
                        val columnName = cursor.getString(columnNameIndex)
                        if (columnName == "location") {
                            columnExists = true
                            break
                        }
                    } else {
                        Log.e("Migration", "Column 'name' not found in PRAGMA result")
                    }
                }
                cursor.close()


                if (!columnExists) {
                    Log.d("Migration", "Adding 'location' column to 'appointment' table")
                    database.execSQL("ALTER TABLE appointment ADD COLUMN location TEXT")
                } else {
                    Log.d("Migration", "'location' column already exists")
                }
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "chronic_health_tracker_db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
