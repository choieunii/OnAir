package com.example.onair.controller

import com.example.onair.service.FlightService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FlightController (private val flightService:FlightService){
    @RequestMapping("/flightInfo")
    fun flightInfo(): String {
        val flight = flightService.getTasks()
        return "flightInfo";
    }
}
