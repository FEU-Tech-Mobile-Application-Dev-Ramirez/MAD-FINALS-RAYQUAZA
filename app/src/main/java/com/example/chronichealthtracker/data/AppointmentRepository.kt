package com.example.chronichealthtracker.data

import androidx.lifecycle.LiveData

class AppointmentRepository(private val appointmentDao: AppointmentDao) {

    val allAppointments: LiveData<List<Appointment>> = appointmentDao.getAllAppointments()

    fun getAppointmentById(appointmentId: Int): LiveData<Appointment> {
        return appointmentDao.getAppointmentById(appointmentId)
    }

    suspend fun insert(appointment: Appointment) {
        val newId = appointmentDao.insert(appointment)

    }


    suspend fun update(appointment: Appointment) {
        appointmentDao.update(appointment)
    }

    suspend fun delete(appointment: Appointment) {
        appointmentDao.delete(appointment)
    }

}
