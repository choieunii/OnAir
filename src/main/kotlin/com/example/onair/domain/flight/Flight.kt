package com.example.onair.domain.flight

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Flight")
data class Flight( // data class -> equals, copy, toString, componentN
        @Id
        @Column(name = "flightNum", nullable=false) //
        var flightNum: Int,
        @Column(name = "departmentAirport", nullable=false) //
        var departmentAirport: String,
        @Column(name = "arriveAirport", nullable=false) //
        var arriveAirport: String,
        @Column(name = "departmentDate", nullable = false) //
        var departmentDate: String,
        @Column(name = "economyCharge", nullable=false) //
        var economyCharge: Int,
        @Column(name = "businessCharge", nullable=false) //
        var BusinessCharge: Int,
        @Column(name = "FirstCharge", nullable=false) //
        var FirstCharge: Int
)
