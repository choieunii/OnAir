package com.example.onair.service

import com.example.onair.domain.AirportCode.AirportCode
import com.example.onair.domain.AirportCode.AirportCodeRepository
import com.example.onair.domain.test.Test
import com.example.onair.domain.test.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AirportCodeService (private val airportCode: AirportCodeRepository){
    fun getTasks(): List<AirportCode> =
        airportCode.findAll()
}