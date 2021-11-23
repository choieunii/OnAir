package com.example.onair.service

import com.example.onair.domain.BookCheck.BookCheck
import com.example.onair.domain.BookCheck.BookCheckRepository
import com.example.onair.domain.flight.FlightRepository
import com.example.onair.dto.BookCheckRequestDto
import org.springframework.stereotype.Service

@Service
class BookCheckService(private val bookRepository: BookCheckRepository, private val flightRepository: FlightRepository) {
    //Instance 만들어서 DB에 집어넣기
    fun addToDB(input : BookCheckRequestDto, user_id : String, Flight_Id : Int, seat_class : String) : String {
        var flightInfo = flightRepository.findInfoByFlightNum(Flight_Id)
        if (flightInfo != null) {
            var instance = BookCheck(
                bookId = bookRepository.getMaxId(),
                customerID = user_id,
                flightNum = Flight_Id,
                gender = input.Gender,
                firstName = input.FirstName,
                lastName = input.LastName,
                birthDate = input.BirthDate,
                airLine = input.AirLine,
                seatClass = seat_class,
                departmentDate = flightInfo.departmentDate,
                arriveAirport = flightInfo.arriveAirport,
                departmentAirport = flightInfo.departmentAirport
            )

            bookRepository.save(instance)

            return "success"
        } else {
            return "failed"
        }
    }
}