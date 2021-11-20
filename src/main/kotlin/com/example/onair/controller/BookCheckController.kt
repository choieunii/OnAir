package com.example.onair.controller

import com.example.onair.service.BookCheckService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class BookCheckController (private val bookCheckService: BookCheckService){
    @RequestMapping("/bookCheck")
    fun bookCheck(customerID: String): String {
        bookCheckService.check(customerID)
        return "bookCheck";
    }
}
