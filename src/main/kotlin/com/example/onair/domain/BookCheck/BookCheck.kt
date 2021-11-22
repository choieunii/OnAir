package com.example.onair.domain.BookCheck

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "bookCheck")
data class BookCheck( // data class -> equals, copy, toString, componentN
    @Id
    @Column(name = "customer_id", nullable=false) //
    var customerID: String,
    @Column(name = "flight_num", nullable=false) //
    var flightNum: Int,
    @Column(name = "department_date", nullable=false) //
    var departmentDate: String,
    @Column(name = "gender", nullable=false) //
    var gender: String,
    @Column(name = "first_name", nullable=false) //
    var firstName: String,
    @Column(name = "last_name", nullable=false) //
    var lastName: String,
    @Column(name = "birth_date", nullable=false) //
    var birthDate: String,
    @Column(name = "air_line", nullable=false) //
    var airLine: String,
    @Column(name = "seat_class", nullable=false) //
    var seatClass: String,
    @Column(name = "arrive_airport", nullable=false) //
    val arriveAirport: String,
    @Column(name = "department_airport", nullable=false) //
    val departmentAirport: String
)
