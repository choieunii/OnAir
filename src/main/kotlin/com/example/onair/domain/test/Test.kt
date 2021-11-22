package com.example.onair.domain.test

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
@Entity
@Table(name = "FLIGHT_INFORMATION")
data class Test (
        @Id
        @Column(name = "FlightNum")
        var FlightNum : Number = 0,
        @Column(name = "DepartmentAirport")
        var DepartmentAirport : String = "",
        @Column(name = "ArriveAirport")
        var ArriveAirport : String = "",
        @Column(name = "DepartmentTime")
        var DepartmentTime : Number = 0,
        @Column(name = "ArriveTime")
        var ArriveTime : Number = 0,
        @Column(name = "EconomyCharge")
        var EconomyCharge : Number = 0,
        @Column(name = "PrestigeCharge")
        var PrestigeCharge : Number = 0
)

@Entity
@Table(name = "CUSTOMER")
data class CUSTOMER (
        @Id
        @Column(name = "CustomerID")
        var CustomerID : Number = 0,
        @Column(name = "LoginID")
        var LoginID : String = "",
        @Column(name = "Password")
        var Password : String = "",
        @Column(name = "Name")
        var Name : String = "",
        @Column(name = "Age")
        var Age : Number = 0,
        @Column(name = "Balance")
        var Balance : Number = 0,
        @Column(name = "Phone")
        var Phone : String = "",
        @Column(name = "Email")
        var Email : String = "",
        @Column(name = "Address")
        var Address : String = ""
)

@Entity
@Table(name = "BOOK_INFO")
data class BOOK_INFO (
        @Id
        @Column(name = "BookID")
        var BookID : Number = 0,
        @Column(name = "CustomerID")
        var CustomerID : Number = 0,
        @Column(name = "FlightNum")
        var FlightNum : Number = 0,
        @Column(name = "Headcount")
        var Headcount : Number = 0,
        @Column(name = "SeatClass")
        var SeatClass : String = ""
)