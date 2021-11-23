package com.example.onair.domain.BookCheck

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookCheckRepository: JpaRepository<BookCheck, Long> {
    @Query("SELECT MAX(bookId) FROM BookCheck")
    fun getMaxId() : Int
    fun findByCustomerID(customerID: String): BookCheck?
}
