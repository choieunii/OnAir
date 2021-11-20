package com.example.onair.domain.BookCheck
import com.example.onair.domain.Flight.Flight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookCheckRepository : JpaRepository<BookCheck, String> {
    @Query("select * from Flight")
    fun findBookCheck(): List<BookCheck>
}