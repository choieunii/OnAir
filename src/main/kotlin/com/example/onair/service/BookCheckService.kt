package com.example.onair.service
import com.example.onair.domain.BookCheck.BookCheck
import com.example.onair.domain.BookCheck.BookCheckRepository
import com.example.onair.dto.BookCheckRequestDto
import org.springframework.stereotype.Service

@Service
class BookCheckService (private val bookCheckRepository: BookCheckRepository){
    fun isCustomerIdExistence(customerID: String): Boolean {
        val bookCheck: BookCheck? = bookCheckRepository.findByCustomerID(customerID)
        return bookCheck != null
    }

    fun isBookIdExistence(bookID: Int): Boolean {
        val bookCheck: BookCheck? = bookCheckRepository.getMaxId(bookID)
        return bookCheck != null
    }

    fun getDtoByCustomerID(customerID: String): BookCheckRequestDto {
        val bookCheck: BookCheck = bookCheckRepository.findByCustomerID(customerID)!!

        return BookCheckRequestDto(
            bookCheck.bookId,
            bookCheck.CustomerID,
            bookCheck.FlightNum,
            bookCheck.DepartmentDate,
            bookCheck.Gender,
            bookCheck.FirstName,
            bookCheck.LastName,
            bookCheck.BirthDate,
            bookCheck.AirLine,
            bookCheck.SeatClass,
            bookCheck.ArriveAirport,
            bookCheck.DepartmentAirport
        )
    }

    fun getDtoByBookID(bookID: Int): BookCheckRequestDto {
        val bookCheck: BookCheck = bookCheckRepository.getMaxId(bookID)!!

        return BookCheckRequestDto(
            bookCheck.bookId,
            bookCheck.CustomerID,
            bookCheck.FlightNum,
            bookCheck.DepartmentDate,
            bookCheck.Gender,
            bookCheck.FirstName,
            bookCheck.LastName,
            bookCheck.BirthDate,
            bookCheck.AirLine,
            bookCheck.SeatClass,
            bookCheck.ArriveAirport,
            bookCheck.DepartmentAirport
        )
    }

    fun getInformation(request: BookCheckRequestDto): Map<String, String> {
        return mapOf(
            "Name" to request.LastName + request.FirstName,
            "AirLine" to request.AirLine,
            "DepartmentAirport" to request.DepartmentAirport,
            "ArriveAirport" to request.ArriveAirport,
            "DepartmentDate" to request.DepartmentDate,
            "SeatClass" to request.SeatClass
        )
    }

    fun cancelByBookID(bookID: Int): Boolean{
        bookCheckRepository.deleteById(bookID)
        return !isBookIdExistence(bookID)
    }

}