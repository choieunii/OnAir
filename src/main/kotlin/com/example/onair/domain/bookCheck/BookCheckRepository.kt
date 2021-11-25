package com.example.onair.domain.bookCheck

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookCheckRepository: JpaRepository<BookCheck, Int> {
    fun findByBookId(bookId: Int) : BookCheck;
    @Query("SELECT MAX(bookId) FROM BookCheck")
    fun getMaxId() : Int
    fun findByCustomerID(customerID: String): List<BookCheck>?
}
