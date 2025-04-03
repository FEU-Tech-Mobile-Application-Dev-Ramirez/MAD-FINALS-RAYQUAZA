package com.example.chronichealthtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.chronichealthtracker.data.AppDatabase
import com.example.chronichealthtracker.data.Appointment
import com.example.chronichealthtracker.data.AppointmentRepository
import kotlinx.coroutines.launch

class AppointmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppointmentRepository
    val allAppointments: LiveData<List<Appointment>>

    init {
        val appointmentDao = AppDatabase.getDatabase(application).appointmentDao()
        repository = AppointmentRepository(appointmentDao)
        allAppointments = repository.allAppointments
    }


    fun getAppointmentById(appointmentId: Int): LiveData<Appointment> {
        return repository.getAppointmentById(appointmentId)
    }


    fun insert(appointment: Appointment) = viewModelScope.launch {
        repository.insert(appointment)
    }

    fun update(appointment: Appointment) = viewModelScope.launch {
        repository.update(appointment)
    }

    fun delete(appointment: Appointment) = viewModelScope.launch {
        repository.delete(appointment)
    }

}
