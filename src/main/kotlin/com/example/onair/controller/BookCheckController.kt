package com.example.onair.controller

import com.example.onair.dto.BookCheckRequestDto
import com.example.onair.service.BookCheckService
import com.sun.org.apache.xpath.internal.operations.Bool
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class BookCheckController (private val bookCheckService: BookCheckService){
    @RequestMapping("/bookCheck")
    fun bookCheck(customerID: String): Boolean {
        return bookCheckService.check(customerID) //존재 여부 확인
    }

    fun printMap(request: BookCheckRequestDto): Map<String, String>{
        //Name, AirLine, DepartmentAirport, ArriveAirport, DepartmentDate, SeatClass
        return bookCheckService.print(request)
    }

    fun printSelect(request: BookCheckRequestDto, select : String): String? {
        return printMap(request)[select]
    }

}
