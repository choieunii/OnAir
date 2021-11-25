package com.example.onair.dto

import javax.persistence.Column


data class BookCheckRequestDto ( // data class -> equals, copy, toString, componentN
    var bookId : Int,
    var CustomerID: String,
    var FlightNum: Int,
    var DepartmentDate: String,
    var Gender: String,
    var FirstName: String,
    var LastName: String,
    var BirthDate: String,
    var AirLine: String,
    var SeatClass: String,
    val ArriveAirport: String,
    val DepartmentAirport: String
)

data class BookCheckResponseDto(
    var Name: String,
    var AirLine: String,
    var DepartmentAirport: String,
    var ArriveAirport : String,
    var DepartmentDate: String,
    var SeatClass: String
)