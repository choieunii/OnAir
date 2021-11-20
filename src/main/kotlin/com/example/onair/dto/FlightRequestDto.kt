package com.example.onair.dto

import javax.persistence.Column

data class FlightRequestDto (
    var FlightNum: Int,
    var DepartmentAirport: String,
    var ArriveAirport: String,
    var EconomyCharge: Int,
    var BusinessCharge: Int,
    var FirstCharge: Int
)