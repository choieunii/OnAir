package com.example.onair.domain.flight

import com.example.onair.domain.flight.Flight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, String>{
    @Query("select * from Flight")
    fun findFlight(): List<Flight>
    @Query("SELECT FlightNum FROM Flight WHERE DepartmentAirport = %?1% AND ArriveAirport = %?2% AND DepartmentDate = %?3%")
    fun getFlightNum(depAirport : String, arrAirport : String, dDate : String) : Int
    @Query("SELECT %?1% FROM Flight WHERE FlightNum = %?2%")
    fun getCharge(SeatType : String, FlightNum : Int) : Int
}
