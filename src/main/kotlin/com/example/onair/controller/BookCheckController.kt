package com.example.onair.controller

import com.example.onair.service.BookCheckService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class BookCheckController (private val bookCheckService: BookCheckService){
    @RequestMapping("/bookCheck")
    fun bookCheck(): String {
        val bookCheck = bookCheckService.getTasks()
        return "bookCheck";
    }
}
