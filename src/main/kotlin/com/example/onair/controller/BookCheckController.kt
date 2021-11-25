package com.example.onair.controller

import com.example.onair.dto.BookCheckRequestDto
import com.example.onair.service.BookCheckService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
class BookCheckController(private val bookCheckService: BookCheckService) {
    @GetMapping("/bookCheck")
    fun getBookCheck(session: HttpSession, model: Model): String {
        val customerID = session.getAttribute("user_id");
        if (customerID !== null) {
            val bookCheckList = bookCheckService.getBookCheck(customerID = customerID as String)
            model.addAttribute("bookList", bookCheckList);
            println(bookCheckList);
            return "bookCheck" //존재 여부 확인
        } else {
            return "login"
        }
    }

    @PostMapping("/bookCheck")
    fun deleteBookCheck(bookId: Int, session: HttpSession, model: Model): String {
        val customerID = session.getAttribute("user_id");
        val refund = bookCheckService.refundUserPoint(userId = customerID as String, bookId = bookId);
        println(refund);
        val res = bookCheckService.cancelByBookID(bookId = bookId);
        val bookCheckList = bookCheckService.getBookCheck(customerID = customerID as String)
        model.addAttribute("bookList", bookCheckList);
        return "bookCheck";
    }

    fun bookCheck(customerID: String): Boolean {
        return bookCheckService.check(customerID) //존재 여부 확인
    }

    fun printMap(request: BookCheckRequestDto): Map<String, String> {
        //Name, AirLine, DepartmentAirport, ArriveAirport, DepartmentDate, SeatClass
        return bookCheckService.print(request)
    }

    fun printSelect(request: BookCheckRequestDto, select: String): String? {
        return printMap(request)[select]
    }

    //존재 여부 확인
    fun isExistence(customerID: String): Boolean {
        return bookCheckService.isExistence(customerID)
    }

//    //다른 함수에서 사용할 request(dto) 가져오기
//    fun getDto(customerID: String): BookCheckRequestDto {
//        return bookCheckService.getDto(customerID)
//    }

    //예약 확인 페이지에서 출력할 정보 전체 map 가져오기
    fun getInformationMap(request: BookCheckRequestDto): Map<String, String> {
        //Name, AirLine, DepartmentAirport, ArriveAirport, DepartmentDate, SeatClass
        return bookCheckService.getInformation(request)
    }

    //map에서 특정 조건에 맞는 정보 하나만 가져오기
    fun getInformationSelected(request: BookCheckRequestDto, select: String): String? {
        return bookCheckService.getInformation(request)[select]
    }

    fun cancelByBookID(bookID: Int): String {
        return bookCheckService.cancelByBookID(bookID)
    }
}
