package com.example.onair.controller

import com.example.onair.domain.flight.Flight
import com.example.onair.domain.test.TestRepository
import com.example.onair.service.FlightService
import com.example.onair.service.TestService
import jdk.internal.org.jline.utils.InputStreamReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

@Controller
class FlightController (private val flightService:FlightService){
    @RequestMapping("/flightInfo")
    fun flightInfo(): String {
        val flight = flightService.getFlightInfo()
        return "flightInfo";
    }
}
