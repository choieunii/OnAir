package com.example.onair.domain.Flight

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, String>{
    @Query("select * from Flight")
    fun findFlight(): List<Flight>
    @Query("SELECT %?1% FROM Flight WHERE DepartmentAirport = %?2% AND ArriveAirport = %?3% AND DepartmentDate = %?4%")
    fun getCharge(seatType : String, depAirport : String, arrAirport : String, dDate : String) : Int
}