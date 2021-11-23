package com.example.onair.domain.flight

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, String>{
    @Query("SELECT f FROM Flight f WHERE f.departmentAirport = :departAirport and f.arriveAirport = :arrAirport")
    fun getFlightOnAirInfo(departAirport: String, arrAirport: String): List<Flight>?
}