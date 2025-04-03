package com.example.chronichealthtracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chronichealthtracker.R
import com.example.chronichealthtracker.data.HealthFacility

class HealthFacilityAdapter(private val healthFacilities: List<HealthFacility>) :
    RecyclerView.Adapter<HealthFacilityAdapter.HealthFacilityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthFacilityViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_health_facility, parent, false)
        return HealthFacilityViewHolder(view)
    }


    override fun onBindViewHolder(holder: HealthFacilityViewHolder, position: Int) {
        val facility = healthFacilities[position]
        holder.bind(facility)
    }


    override fun getItemCount(): Int = healthFacilities.size


    class HealthFacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
        private val scheduleTextView: TextView = itemView.findViewById(R.id.tv_schedule)
        private val locationTextView: TextView = itemView.findViewById(R.id.tv_location)

        // Bind the HealthFacility object data to the views
        fun bind(facility: HealthFacility) {
            nameTextView.text = facility.name
            scheduleTextView.text = facility.schedule
            locationTextView.text = facility.location
        }
    }
}
