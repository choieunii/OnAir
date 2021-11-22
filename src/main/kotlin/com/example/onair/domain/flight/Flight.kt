package com.example.onair.domain.flight

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "flight")
data class Flight( // data class -> equals, copy, toString, componentN
        @Id
        @Column(name = "FlightNum", nullable=false) //
        var FlightNum: Int,
        @Column(name = "DepartmentAirport", nullable=false) //
        var DepartmentAirport: String,
        @Column(name = "ArriveAirport", nullable=false) //
        var ArriveAirport: String,
        @Column(name = "DepartmentDate", nullable = false) //
        var DepartmentDate: String,
        @Column(name = "EconomyCharge", nullable=false) //
        var EconomyCharge: Int,
        @Column(name = "BusinessCharge", nullable=false) //
        var BusinessCharge: Int,
        @Column(name = "FirstCharge", nullable=false) //
        var FirstCharge: Int
)
