package com.example.onair.service
import com.example.onair.domain.BookCheck.BookCheck
import com.example.onair.domain.BookCheck.BookCheckRepository
import org.springframework.stereotype.Service

@Service
class BookCheckService (private val bookCheckRepository: BookCheckRepository){
    fun getTasks(): List<BookCheck> =
        bookCheckRepository.findAll()

    fun post(bookCheck: BookCheck){
        bookCheckRepository.save(bookCheck)
    }

    fun check(customerID: String): BookCheck? {
        return bookCheckRepository.findByCustomerID(customerID)
    }
}