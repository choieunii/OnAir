package com.example.onair.domain.BookCheck
import org.springframework.data.jpa.repository.JpaRepository

interface BookCheckRepository : JpaRepository<BookCheck, String> {
    fun findByCustomerID(customerID: String): BookCheck?
}