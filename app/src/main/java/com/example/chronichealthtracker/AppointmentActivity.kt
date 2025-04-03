package com.example.chronichealthtracker

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chronichealthtracker.viewmodel.AppointmentViewModel

class AppointmentActivity : AppCompatActivity() {

    private lateinit var appointmentViewModel: AppointmentViewModel
    private var appointmentId: Int = -1  // Default value if not passed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_appointment)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDoctor = findViewById<TextView>(R.id.tvDoctor)
        val tvDate = findViewById<TextView>(R.id.tvDate)
        val tvLocation = findViewById<TextView>(R.id.tv_location)

        appointmentViewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)


        appointmentId = intent.getIntExtra("appointment_id", -1)

        if (appointmentId != -1) {

            appointmentViewModel.getAppointmentById(appointmentId).observe(this, Observer { appointment ->
                appointment?.let {
                    tvTitle.text = it.title
                    tvDoctor.text = it.doctor
                    tvDate.text = it.date
                    tvLocation.text = it.location
                } ?: run {

                    Toast.makeText(this, "Appointment not found", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Invalid Appointment ID", Toast.LENGTH_SHORT).show()
        }
    }
}

