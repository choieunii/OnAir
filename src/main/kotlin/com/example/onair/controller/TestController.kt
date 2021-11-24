package com.example.onair.controller

import com.example.onair.service.TestService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class TestController (private val testService:TestService){
    @RequestMapping("/index")
    fun index(): String {
        return "index";
    }
    @RequestMapping("/book")
    fun book(): String {
        return "book";
    }
//    @RequestMapping("/book2")
//    fun book2(): String {
//        return "book2";
//    }

    @RequestMapping("/signUp")
    fun signUp(): String {
        return "signUp";
    }
    @RequestMapping("/payment")
    fun paymentInfo(): String {
        val test = testService.getTasks()
        return "payment";
    }
    @RequestMapping("/guide")
    fun guideInfo(): String {
        val test = testService.getTasks()
        return "guide";
    }
    @RequestMapping("/myPage")
    fun myPageInfo(): String {
        val test = testService.getTasks()
        return "myPage";
    }
}

