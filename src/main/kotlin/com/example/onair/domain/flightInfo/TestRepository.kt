package com.example.onair.domain.test

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightInformationRepository : JpaRepository<FlightInformation, String>