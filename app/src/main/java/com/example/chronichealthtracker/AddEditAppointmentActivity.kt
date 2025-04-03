package com.example.chronichealthtracker

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.chronichealthtracker.data.Appointment
import com.example.chronichealthtracker.viewmodel.AppointmentViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class AddEditAppointmentActivity : ComponentActivity() {

    private lateinit var appointmentViewModel: AppointmentViewModel
    private var appointmentId: Int? = null

    private lateinit var titleEditText: EditText
    private lateinit var doctorEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var notesEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var saveButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_appointment)

        // Initialize ViewModel
        appointmentViewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)

        // Initialize UI elements
        titleEditText = findViewById(R.id.et_title)
        doctorEditText = findViewById(R.id.et_doctor)
        dateEditText = findViewById(R.id.et_date)
        timeEditText = findViewById(R.id.et_time)
        notesEditText = findViewById(R.id.et_notes)
        locationEditText = findViewById(R.id.et_location)
        saveButton = findViewById(R.id.btn_save)

        // Retrieve appointmentId from Intent, if available
        appointmentId = intent.getIntExtra("APPOINTMENT_ID", -1).takeIf { it != -1 }

        // If an appointment ID is passed, load its details
        appointmentId?.let { id ->
            appointmentViewModel.getAppointmentById(id).observe(this) { appointment ->
                appointment?.let {
                    titleEditText.setText(it.title ?: "")
                    doctorEditText.setText(it.doctor ?: "")
                    dateEditText.setText(it.date ?: "")
                    timeEditText.setText(it.time ?: "")
                    notesEditText.setText(it.notes ?: "")
                    locationEditText.setText(it.location ?: "")
                }
            }
        }

        // Save button click listener
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val doctor = doctorEditText.text.toString().trim()
            val date = dateEditText.text.toString().trim()
            val time = timeEditText.text.toString().trim()
            val notes = notesEditText.text.toString().trim()
            val location = locationEditText.text.toString().trim()

            // Ensure required fields are filled
            if (title.isNotEmpty() && doctor.isNotEmpty() && date.isNotEmpty()) {
                val notesValue = notes.takeIf { it.isNotEmpty() }
                val locationValue = location.takeIf { it.isNotEmpty() }

                // Check if updating an existing appointment
                if (appointmentId != null) {
                    val updatedAppointment = Appointment(appointmentId!!, title, doctor, date, time, notesValue, locationValue)
                    appointmentViewModel.update(updatedAppointment)
                    Toast.makeText(this, "Appointment updated", Toast.LENGTH_SHORT).show()

                    // Pass the updated appointment ID
                    val intent = Intent(this, AppointmentActivity::class.java)
                    intent.putExtra("appointment_id", appointmentId)  // Pass existing appointment ID
                    startActivity(intent)
                } else {
                    // Creating a new appointment
                    val newAppointment = Appointment(title = title, doctor = doctor, date = date, time = time, notes = notesValue, location = locationValue)

                    // Insert new appointment and get the generated ID
                    lifecycleScope.launch {
                        val newAppointmentId = appointmentViewModel.insert(newAppointment)  // Insert and get the new appointment ID
                        Toast.makeText(this@AddEditAppointmentActivity, "Appointment added", Toast.LENGTH_SHORT).show()

                        // Pass the entire new appointment object (Parcelable)
                        val intent = Intent(this@AddEditAppointmentActivity, AppointmentActivity::class.java)
                        intent.putExtra("appointment", newAppointment)  // Pass entire appointment object
                        startActivity(intent)
                        finish()  // Close the current activity after saving
                    }
                }
            } else {
                // If fields are not filled correctly
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
