package com.example.onair.service
import com.example.onair.domain.BookCheck.BookCheck
import com.example.onair.domain.BookCheck.BookCheckRepository
import com.example.onair.dto.BookCheckRequestDto
import org.springframework.stereotype.Service

@Service
class BookCheckService (private val bookCheckRepository: BookCheckRepository){
    fun isExistence(customerID: String): Boolean {
        val bookCheck: BookCheck? = bookCheckRepository.findByCustomerID(customerID)

        return bookCheck != null
    }

    fun getDto(customerID: String): BookCheckRequestDto {
        val bookCheck: BookCheck = bookCheckRepository.findByCustomerID(customerID)!!

        return BookCheckRequestDto(
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

}