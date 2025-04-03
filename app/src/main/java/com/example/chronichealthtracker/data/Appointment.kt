package com.example.chronichealthtracker.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "appointment")
@Parcelize
data class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val doctor: String,
    val date: String,
    val time: String?,
    val notes: String?,
    val location: String?
) : Parcelable

