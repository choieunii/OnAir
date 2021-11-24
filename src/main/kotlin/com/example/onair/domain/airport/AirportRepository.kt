package com.example.onair.domain.airport

import com.example.onair.domain.User.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AirportRepository : JpaRepository<Airport, String> {
    fun findById(id: Int): Airport?
}