package com.example.onair.domain.book

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "BOOK_INFO")
@Entity
class Book (
    @Id
    @Column(name = "BookID")
    var BookId :  Long = 0,
    @Column(name = "CustomerID")
    var UserId : String = "",
    @Column(name = "FlightID")
    var FlightId : Long = 0,
    @Column(name = "Gender")
    var Gender : String = "",
    @Column(name = "FirstName")
    var FirstName : String = "",
    @Column(name = "LastName")
    var LastName : String = "",
    @Column(name = "BirthDate")
    var BirthDate : String = "",
    @Column(name = "AirLine")
    var AirLine : String = "",
    @Column(name = "SeatClass")
    var SeatClass : String = "",
)
