package com.example.onair.service

import com.example.onair.domain.Flight.Flight
import com.example.onair.domain.Flight.FlightRepository
import org.springframework.stereotype.Service

@Service
class FlightService (private val flightRepository: FlightRepository) {
    fun getTasks(): List<Flight> =
        flightRepository.findAll()

    fun post(flight: Flight){
        flightRepository.save(flight)
    }
}
