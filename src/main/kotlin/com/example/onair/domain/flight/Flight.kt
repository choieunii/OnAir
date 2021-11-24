package com.example.onair.domain.flight

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "flight")
data class Flight( // data class -> equals, copy, toString, componentN
        @Id
        @Column(name = "flight_num", nullable=false) //
        var flightNum: Int,
        @Column(name = "department_airport", nullable=false) //
        var departmentAirport: String,
        @Column(name = "arrive_airport", nullable=false) //
        var arriveAirport: String,
        @Column(name = "department_date", nullable = false) //
        var departmentDate: String,
        @Column(name = "arrive_date", nullable = false) //
        var arriveDate: String,
        @Column(name = "department_time", nullable = false) //
        var departmentTime: String,
        @Column(name = "arrive_time", nullable = false) //
        var arriveTime: String,
        @Column(name = "economy_charge", nullable=false) //
        var economyCharge: Int,
        @Column(name = "business_charge", nullable=false) //
        var businessCharge: Int,
        @Column(name = "first_charge", nullable=false) //
        var firstCharge: Int
){
        fun getFN():Int{
                return flightNum;
        }
        fun getDMA():String{
                return departmentAirport;
        }
        fun getAA():String{
                return arriveAirport;
        }
        fun getDD():String{
                return departmentDate;
        }
        fun getAD():String{
                return arriveDate;
        }
        fun getDT():String{
                return departmentTime;
        }
        fun getAT():String{
                return arriveTime;
        }
        fun getEC():Int{
                return economyCharge;
        }
        fun getBC():Int{
                return businessCharge;
        }
        fun getFC():Int{
                return firstCharge;
        }
}