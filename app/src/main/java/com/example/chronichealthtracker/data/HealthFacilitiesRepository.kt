

package com.example.chronichealthtracker.data

class HealthFacilitiesRepository {

    fun getHealthFacilities(): List<HealthFacility> {
        return listOf(
            HealthFacility(
                name = "St. Luke's Medical Center",
                type = "Hospital",
                schedule = "24/7",
                location = "Bonifacio Global City, Taguig",
                contactNumber = "(02) 8789 7700"
            ),
            HealthFacility(
                name = "Makati Medical Center",
                type = "Hospital",
                schedule = "24/7",
                location = "Makati, Metro Manila",
                contactNumber = "(02) 8888 8000"
            ),
            HealthFacility(
                name = "The Medical City",
                type = "Hospital",
                schedule = "24/7",
                location = "Ortigas, Pasig City",
                contactNumber = "(02) 8988 1000"
            ),
            HealthFacility(
                name = "QC General Hospital",
                type = "Hospital",
                schedule = "8:00 AM - 5:00 PM",
                location = "Quezon City, Metro Manila",
                contactNumber = "(02) 8888 8000"
            ),
            HealthFacility(
                name = "Lourdes Clinic",
                type = "Clinic",
                schedule = "9:00 AM - 6:00 PM",
                location = "Mandaluyong, Metro Manila",
                contactNumber = "(02) 8723 1234"
            ),
            HealthFacility(
                name = "Healthway Medical",
                type = "Clinic",
                schedule = "10:00 AM - 7:00 PM",
                location = "Alabang, Muntinlupa",
                contactNumber = "(02) 8582 0123"
            )
        )
    }
}