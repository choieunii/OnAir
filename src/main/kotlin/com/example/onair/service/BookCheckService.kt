package com.example.onair.service
import com.example.onair.domain.bookCheck.BookCheck
import com.example.onair.domain.bookCheck.BookCheckRepository
import com.example.onair.domain.flight.FlightRepository
import com.example.onair.dto.BookCheckRequestDto
import org.springframework.stereotype.Service

@Service
class BookCheckService (private val bookCheckRepository: BookCheckRepository, private val flightRepository: FlightRepository){
    fun isExistence(customerID: String): Boolean {
        val bookCheck: List<BookCheck>? = bookCheckRepository.findByCustomerID(customerID)
        return bookCheck !==null
    }
    fun check(customerID: String): Boolean {
        val bookCheck: List<BookCheck>? = bookCheckRepository.findByCustomerID(customerID)

        if (bookCheck != null) {
//            BookCheckRequestDto(
//                CustomerID=bookCheck.customerID,
//                FlightNum=bookCheck.flightNum,
//                DepartmentDate=bookCheck.departmentDate,
//                Gender=bookCheck.gender,
//                FirstName=bookCheck.firstName,
//                LastName=bookCheck.lastName,
//                BirthDate=bookCheck.birthDate,
//                AirLine=bookCheck.airLine,
//                SeatClass=bookCheck.seatClass,
//                ArriveAirport=bookCheck.arriveAirport,
//                DepartmentAirport=bookCheck.departmentAirport
//            )
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
    fun addToDB(input : BookCheckRequestDto, user_id : String, Flight_Id : Int, seat_class : String) : String {
        var flightInfo = flightRepository.findInfoByFlightNum(Flight_Id)
        if (flightInfo != null) {
            var instance = BookCheck(
                bookId = bookCheckRepository.getMaxId(),
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

            bookCheckRepository.save(instance)

            return "success"
        } else {
            return "failed"
        }
    }
    fun getBookCheck(customerID: String): List<BookCheck>? {
        val bookCheck: List<BookCheck>? = bookCheckRepository.findByCustomerID(customerID);
        // Customer Id로 찾는 bookchech 정보는 List일 것. 정보가 하나가 아니기 때문에
        return bookCheck;
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

    fun deleteBookCheck(bookId: Int): String{
        val res = bookCheckRepository.deleteById(bookId);
        println(res);
        return "Success";
    }

}