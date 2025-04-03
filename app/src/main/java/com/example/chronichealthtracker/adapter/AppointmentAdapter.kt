package com.example.chronichealthtracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chronichealthtracker.R
import com.example.chronichealthtracker.data.Appointment

class AppointmentAdapter(
    private val appointments: List<Appointment>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    inner class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val doctor: TextView = itemView.findViewById(R.id.tvDoctor)
        val date: TextView = itemView.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.title.text = appointment.title
        holder.doctor.text = appointment.doctor
        holder.date.text = appointment.date


        holder.itemView.setOnClickListener {
            listener.onItemClick(appointment)
        }
    }

    override fun getItemCount() = appointments.size

    interface OnItemClickListener {
        fun onItemClick(appointment: Appointment)
    }
}