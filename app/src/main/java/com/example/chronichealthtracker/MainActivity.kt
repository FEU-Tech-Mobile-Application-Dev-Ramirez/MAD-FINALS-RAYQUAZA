package com.example.chronichealthtracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chronichealthtracker.adapter.AppointmentAdapter
import com.example.chronichealthtracker.adapter.HealthFacilityAdapter
import com.example.chronichealthtracker.data.Appointment
import com.example.chronichealthtracker.data.HealthFacilitiesRepository
import com.example.chronichealthtracker.viewmodel.AppointmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AppointmentAdapter.OnItemClickListener {

    private lateinit var appointmentViewModel: AppointmentViewModel
    private lateinit var appointmentRecyclerView: RecyclerView
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var healthFacilityRecyclerView: RecyclerView
    private lateinit var healthFacilityAdapter: HealthFacilityAdapter
    private lateinit var healthFacilitiesRepository: HealthFacilitiesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        appointmentRecyclerView = findViewById(R.id.appointmentsRecyclerView)
        appointmentRecyclerView.layoutManager = LinearLayoutManager(this)

        appointmentAdapter = AppointmentAdapter(emptyList(), this)
        appointmentRecyclerView.adapter = appointmentAdapter


        appointmentViewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)


        appointmentViewModel.allAppointments.observe(this, { appointments ->
            appointmentAdapter = AppointmentAdapter(appointments, this)
            appointmentRecyclerView.adapter = appointmentAdapter
        })

        // Health Facility RecyclerView setup
        healthFacilityRecyclerView = findViewById(R.id.healthFacilityRecyclerView)
        healthFacilityRecyclerView.layoutManager = LinearLayoutManager(this)


        healthFacilitiesRepository = HealthFacilitiesRepository()
        val healthFacilities = healthFacilitiesRepository.getHealthFacilities()



        Log.d("HealthFacilityData", "Health Facilities: $healthFacilities")
        Log.d("HealthFacilityData", "Health Facilities Count: ${healthFacilities.size}")


        healthFacilityAdapter = HealthFacilityAdapter(healthFacilities)
        healthFacilityRecyclerView.adapter = healthFacilityAdapter



        val buttonNavigateToHealthFacilities: Button = findViewById(R.id.buttonNavigateToHealthFacilities)


        buttonNavigateToHealthFacilities.setOnClickListener {

            val intent = Intent(this, HealthFacilitiesActivity::class.java)
            startActivity(intent)
        }


        val buttonGoToAppointments: Button = findViewById(R.id.buttonGoToAppointments)


        buttonGoToAppointments.setOnClickListener {
            appointmentRecyclerView.smoothScrollToPosition(0) // Scroll to the top of the list
            val intent = Intent(this, AppointmentActivity ::class.java)
            startActivity(intent)
        }


        val fabAddAppointment: FloatingActionButton = findViewById(R.id.fab_add)


        fabAddAppointment.setOnClickListener {
            val intent = Intent(this, AddEditAppointmentActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onItemClick(appointment: Appointment) {
        val intent = Intent(this, AddEditAppointmentActivity::class.java)
        intent.putExtra("APPOINTMENT_ID", appointment.id)
        startActivity(intent)
    }
}
