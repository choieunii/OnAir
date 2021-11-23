package com.example.onair.domain.flight

import com.example.onair.domain.User.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, Int> {
    @Query("SELECT f FROM Flight f WHERE f.departmentAirport = :depAirport AND f.arriveAirport = :arrAirport AND f.departmentDate = :dDate")
    fun getFlightNum(depAirport : String, arrAirport : String, dDate : String) : List<Flight>?

    fun findInfoByFlightNum(flightNum: Int): Flight?
}