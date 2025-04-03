package com.example.chronichealthtracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface AppointmentDao {

    @Insert
    suspend fun insert(appointment: Appointment): Long

    @Update
    suspend fun update(appointment: Appointment)

    @Delete
    suspend fun delete(appointment: Appointment)

    @Query("SELECT * FROM appointment WHERE id = :appointmentId LIMIT 1")
    fun getAppointmentById(appointmentId: Int): LiveData<Appointment>

    @Query("SELECT * FROM appointment")
    fun getAllAppointments(): LiveData<List<Appointment>>
}