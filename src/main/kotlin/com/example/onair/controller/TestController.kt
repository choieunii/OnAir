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
    @RequestMapping("/guide")
    fun guideInfo(): String {
        val test = testService.getTasks()
        return "guide";
    }
}

