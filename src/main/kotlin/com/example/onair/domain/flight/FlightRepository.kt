package com.example.onair.domain.flight
import org.springframework.data.jpa.repository.JpaRepository

interface FlightRepository : JpaRepository<Flight, String> {
    fun findByCustomerID(customerID: String): Flight?
}