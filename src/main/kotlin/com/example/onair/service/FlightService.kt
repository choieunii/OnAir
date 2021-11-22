package com.example.onair.service

import com.example.onair.domain.flight.FlightRepository
import com.example.onair.domain.test.Test
import com.example.onair.domain.test.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

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
