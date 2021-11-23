package com.example.onair.domain.flight

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, String>{
    @Query("SELECT flight_num FROM Flight WHERE department_airport = :dep AND ArriveAirport = :arr AND department_date= :dDate")
    fun getFlightNum(@Param("dep")depAirport : String, @Param("arr")arrAirport : String, @Param("dData")dDate : String) : Int

    @Query("SELECT :seatType FROM Flight WHERE flight_num = :FlightNum")
    fun getCharge(@Param("seatType")seatType : String, @Param("FlightNum")FlightNum : Int) : Long
}
