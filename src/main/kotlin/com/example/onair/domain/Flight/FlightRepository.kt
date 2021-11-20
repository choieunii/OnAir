package com.example.onair.domain.Flight

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, String>{
    @Query("select * from Flight")
    fun findFlight(): List<Flight>
}