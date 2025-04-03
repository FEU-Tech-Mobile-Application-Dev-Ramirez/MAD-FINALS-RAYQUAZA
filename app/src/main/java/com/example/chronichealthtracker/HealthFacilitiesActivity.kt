package com.example.chronichealthtracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chronichealthtracker.data.HealthFacilitiesRepository
import com.example.chronichealthtracker.adapter.HealthFacilityAdapter

class HealthFacilitiesActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var healthFacilityAdapter: HealthFacilityAdapter
    private lateinit var repository: HealthFacilitiesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_facilities)

        recyclerView = findViewById(R.id.healthFacilityRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        repository = HealthFacilitiesRepository()
        val healthFacilities = repository.getHealthFacilities()

        healthFacilityAdapter = HealthFacilityAdapter(healthFacilities)
        recyclerView.adapter = healthFacilityAdapter
    }
}
