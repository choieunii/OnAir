package com.example.onair.dto

import javax.persistence.Column

data class BookCheckRequestDto ( // data class -> equals, copy, toString, componentN
    var CustomerID: String,
    var FlightNum: Int,
    var Gender: String,
    var FirstName: String,
    var LastName: String,
    var BirthDate: String,
    var AirLine: String,
    var SeatClass: String
)