package com.example.onair.service
import com.example.onair.domain.BookCheck.BookCheck
import com.example.onair.domain.BookCheck.BookCheckRepository
import com.example.onair.dto.BookCheckRequestDto
import org.springframework.stereotype.Service

@Service
class BookCheckService (private val bookCheckRepository: BookCheckRepository){
    fun check(customerID: String): Boolean {
        val bookCheck: BookCheck? = bookCheckRepository.findByCustomerID(customerID)

        if (bookCheck != null) {
            BookCheckRequestDto(
                CustomerID=bookCheck.CustomerID,
                FlightNum=bookCheck.FlightNum,
                DepartmentDate=bookCheck.DepartmentDate,
                Gender=bookCheck.Gender,
                FirstName=bookCheck.FirstName,
                LastName=bookCheck.LastName,
                BirthDate=bookCheck.BirthDate,
                AirLine=bookCheck.AirLine,
                SeatClass=bookCheck.SeatClass,
                ArriveAirport=bookCheck.ArriveAirport,
                DepartmentAirport=bookCheck.DepartmentAirport
            )
            return true
        }
        return false
        }

    fun print(request: BookCheckRequestDto): Map<String, String> {
        return mapOf(
            "name" to request.LastName + request.FirstName,
            "AirLine" to request.AirLine,
            "DepartmentAirport" to request.DepartmentAirport,
            "ArriveAirport" to request.ArriveAirport,
            "DepartmentDate" to request.DepartmentDate,
            "SeatClass" to request.SeatClass
        )
    }

}