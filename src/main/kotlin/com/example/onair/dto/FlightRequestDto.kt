package com.example.onair.dto

import javax.persistence.Column

data class FlightRequestDto ( // data class -> equals, copy, toString, componentN
    var FlightNum: Int,
    var DepartmentAirport: String,
    var ArriveAirport: String,
    var EconomyCharge: Int,
    var BusinessCharge: Int,
    var FirstCharge: Int
)