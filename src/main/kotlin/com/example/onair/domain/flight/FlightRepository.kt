package com.example.onair.domain.flight

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, String>{
//    @Query("select * from Flight")
//    fun findFlight(): List<Flight>
//    @Query("SELECT * FROM Flight WHERE DepartmentAirport = :depAirport AND ArriveAirport = :arrAirport AND DepartmentDate = :dDate")
//    fun getFlightNum(depAirport : String, arrAirport : String, dDate : String) : Flight
//    @Query("SELECT * FROM Flight flight_num = :flightNum")
//    fun getCharge(type: String, flightNum : Int) : Flight
}
