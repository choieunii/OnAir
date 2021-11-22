package com.example.onair.service

import com.example.onair.domain.book.Book
import com.example.onair.domain.book.BookRepository
import com.example.onair.dto.PassengerDto
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {
    //Instance 만들어서 DB에 집어넣기
    fun addToDB(input : PassengerDto, user_id : String, Flight_Id : Int, seat_class : String) {
        var instance = Book(
            BookId = bookRepository.getMaxId() + 1,
            UserId = user_id,
            FlightId = Flight_Id as Long,
            Gender = input.gender,
            FirstName = input.firstName,
            LastName = input.lastName,
            BirthDate = input.year as String + input.month as String + input.day as String,
            AirLine = input.airline,
            SeatClass = seat_class
        )
        bookRepository.save(instance)
    }
}
