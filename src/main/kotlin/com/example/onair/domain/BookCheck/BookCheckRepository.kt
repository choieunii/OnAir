package com.example.onair.domain.BookCheck
import com.example.onair.domain.Flight.Flight
import com.example.onair.domain.User.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookCheckRepository : JpaRepository<BookCheck, String> {
    fun findByCustomerID(customerID: String): BookCheck?
    @Query("SELECT MAX(bookId) FROM BookCheck")
    fun getMaxId(bookId: Int) : BookCheck?

    fun deleteById(bookID: Int)
}