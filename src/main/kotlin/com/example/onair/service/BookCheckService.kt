package com.example.onair.service
import com.example.onair.domain.BookCheck.BookCheck
import com.example.onair.domain.BookCheck.BookCheckRepository
import org.springframework.stereotype.Service

@Service
class BookCheckService (private val bookCheckRepository: BookCheckRepository){
    fun check(customerID: String): BookCheck? {
        return bookCheckRepository.findByCustomerID(customerID)
    }

}