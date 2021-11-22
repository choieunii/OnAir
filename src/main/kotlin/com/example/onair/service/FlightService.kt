package com.example.onair.service

import com.example.onair.domain.Flight.FlightRepository
import org.springframework.stereotype.Service

@Service
class FlightService (private val flightRepository: FlightRepository){
    fun getTasks() =
        flightRepository.findAll()
        
    fun getFlightNum(dep : String, ari : String, date : String) : Int{
        return flightRepository.getFlightNum(dep, ari, date)
    }
    fun getCharge(type : String, flightNum : Int) : Int {
        return flightRepository.getCharge(type, flightNum)
    }
}
