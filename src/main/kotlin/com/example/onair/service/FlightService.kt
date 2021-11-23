package com.example.onair.service

import com.example.onair.domain.flight.FlightRepository
import org.springframework.stereotype.Service

@Service
class FlightService (private var flightRepository : FlightRepository) {
    fun getFlightNum(dep : String, ari : String, date : String) : Int{
        return flightRepository.getFlightNum(dep, ari, date)
    }

    fun getCharge(type : String, flightNum : Int) : Long {
        return flightRepository.getCharge(type, flightNum)
    }
}
