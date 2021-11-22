package com.example.onair.controller

import com.example.onair.service.FlightService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class FlightController (private val flightService:FlightService){
    @RequestMapping("/flightInfo")
    fun flightInfo(): String {
        val flight = flightService.getTasks()
        return "flightInfo";
    }

    @RequestMapping("/book2")
    fun book2(request : HttpServletRequest, session: HttpSession): String {
        val departmentAirport = request.getParameter("departmentAirport")
        val arriveAirport = request.getParameter("arriveAirport")
        val departmentDate = request.getParameter("departmentDate")
        val flightNum = flightService.getFlightNum(departmentAirport, arriveAirport, departmentDate)
        session.setAttribute("flightNum", flightNum)
        session.setAttribute("grade", request.getParameter("grade"))
        return "book2"
    }

}
