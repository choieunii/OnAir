package com.example.onair.service

import com.example.onair.domain.flight.Flight
import com.example.onair.domain.flight.FlightRepository
import com.example.onair.domain.test.Test
import com.example.onair.domain.test.TestRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jdk.internal.org.jline.utils.InputStreamReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

@Service
class FlightService (private val flightRepository: FlightRepository) {
    fun getTasks(): List<Flight> =
        flightRepository.findAll()

    fun post(flight: Flight){
        flightRepository.save(flight)
    }
}
