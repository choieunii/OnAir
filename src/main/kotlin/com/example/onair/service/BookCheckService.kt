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
                CustomerID=bookCheck.customerID,
                FlightNum=bookCheck.flightNum,
                DepartmentDate=bookCheck.departmentDate,
                Gender=bookCheck.gender,
                FirstName=bookCheck.firstName,
                LastName=bookCheck.lastName,
                BirthDate=bookCheck.birthDate,
                AirLine=bookCheck.airLine,
                SeatClass=bookCheck.seatClass,
                ArriveAirport=bookCheck.arriveAirport,
                DepartmentAirport=bookCheck.departmentAirport
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