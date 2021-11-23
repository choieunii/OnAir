package com.example.onair.service

import com.example.onair.domain.flight.FlightRepository
import org.springframework.stereotype.Service

@Service
class FlightService (private val flightRepository: FlightRepository){
    fun getTasks() =
        flightRepository.findAll()
        
//    fun getFlightNum(dep : String, ari : String, date : String) : Int{
//        return flightRepository.getFlightNum(dep, ari, date)!!.FlightNum;
//    }
//    fun getCharge(type : String, flightNum : Int) : Int {
//        val a  =flightRepository.getCharge(type, flightNum);
//        return  a.EconomyCharge;
//    }
}
