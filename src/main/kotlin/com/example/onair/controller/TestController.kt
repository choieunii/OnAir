package com.example.onair.controller

import com.example.onair.domain.test.TestRepository
import com.example.onair.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class TestController (private val testService:TestService){
    @RequestMapping("/index")
    fun index(): String {
        val test = testService.getTasks()
        return "index";
    }
    @RequestMapping("/book")
    fun book(): String {
        val test = testService.getTasks()
        return "book";
    }
    @RequestMapping("/book2")
    fun book2(): String {
        val test = testService.getTasks()
        return "book2";
    }
    @RequestMapping("/bookCheck")
    fun bookCheck(): String {
        val test = testService.getTasks()
        return "bookCheck";
    }
    @RequestMapping("/flightInfo")
    fun flightInfo(): String {
        val test = testService.getTasks()
        return "flightInfo";
    }
    @RequestMapping("/signUp")
    fun signUp(): String {
        val test = testService.getTasks()
        return "signUp";
    }
    @RequestMapping("/payment")
    fun paymentInfo(): String {
        val test = testService.getTasks()
        return "payment";
    }
}

