package com.example.onair.domain.flight

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "flight")
data class Flight(
        @Id
        @Column(name = "FlightNum") //
        var FlightNum: Int = -1,
        @Column(name = "DepartmentAirport") //
        var DepartmentAirport: String = "",
        @Column(name = "ArriveAirport") //
        var ArriveAirport: String = "",
        @Column(name = "EconomyCharge") //
        var EconomyCharge: Int = -1,
        @Column(name = "BusinessCharge") //
        var BusinessCharge: Int = -1,
        @Column(name = "FirstCharge") //
        var FirstCharge: Int = -1
)
