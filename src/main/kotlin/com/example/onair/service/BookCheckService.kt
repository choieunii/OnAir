package com.example.onair.service
import com.example.onair.domain.BookCheck.BookCheck
import com.example.onair.domain.BookCheck.BookCheckRepository
import com.example.onair.domain.Flight.Flight
import com.example.onair.domain.test.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookCheckService (private val bookCheckRepository: BookCheckRepository){
    fun getTasks(): List<BookCheck> =
        bookCheckRepository.findAll()

    fun post(bookCheck: BookCheck){
        bookCheckRepository.save(bookCheck)
    }
}