package com.example.onair.controller

import com.example.onair.dto.BookCheckRequestDto
import com.example.onair.service.BookCheckService
import com.sun.org.apache.xpath.internal.operations.Bool
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class BookCheckController (private val bookCheckService: BookCheckService){
    @RequestMapping("/bookCheck")
    //존재 여부 확인
    fun isExistence(customerID: String): Boolean {
        return bookCheckService.isExistence(customerID)
    }

    //다른 함수에서 사용할 request(dto) 가져오기
    fun getDto(customerID: String): BookCheckRequestDto {
        return bookCheckService.getDto(customerID)
    }

    //예약 확인 페이지에서 출력할 정보 전체 map 가져오기
    fun getInformationMap(request: BookCheckRequestDto): Map<String, String>{
        //Name, AirLine, DepartmentAirport, ArriveAirport, DepartmentDate, SeatClass
        return bookCheckService.getInformation(request)
    }

    //map에서 특정 조건에 맞는 정보 하나만 가져오기
    fun getInformationSelected(request: BookCheckRequestDto, select : String): String? {
        return getInformationMap(request)[select]
    }

}
