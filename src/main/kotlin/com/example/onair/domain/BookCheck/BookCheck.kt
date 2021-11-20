package com.example.onair.domain.BookCheck

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "bookCheck")
data class BookCheck( // data class -> equals, copy, toString, componentN
    @Id
    @Column(name = "CustomerID", nullable=false) //
    var CustomerID: String,
    @Column(name = "FlightNum", nullable=false) //
    var FlightNum: Int,
    @Column(name = "DepartmentDate", nullable=false) //
    var DepartmentDate: String,
    @Column(name = "Gender", nullable=false) //
    var Gender: String,
    @Column(name = "FirstName", nullable=false) //
    var FirstName: String,
    @Column(name = "LastName", nullable=false) //
    var LastName: String,
    @Column(name = "BirthDate", nullable=false) //
    var BirthDate: String,
    @Column(name = "AirLine", nullable=false) //
    var AirLine: String,
    @Column(name = "SeatClass", nullable=false) //
    var SeatClass: String
)
