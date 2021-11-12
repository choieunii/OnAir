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
    @RequestMapping("/hello")
    fun index(): String {
        val test = testService.getTasks()
        return "hello";
    }
}