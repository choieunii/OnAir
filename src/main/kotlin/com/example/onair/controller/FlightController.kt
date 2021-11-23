package com.example.onair.controller

import com.example.onair.service.FlightService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FlightController(private val flightService: FlightService) {
    @GetMapping("/flightInfo")
    fun flightInfo(model: Model): String {
        val flightInfoArr = flightService.getFlightInfo() // 비행 정보를 불러옴
        val airportInfoArr = flightService.getAirPortInfo() // 공항 정보를 불러옴
        val airlineInfoArr = flightService.getAirLineInfo() //  항공사 정보를 불러옴
        // 공항이랑 항공사는 프론트에 띄워야 할 것 같으니 model에 넣어 둔다.
        // 우선 항공사를 선택 airlineId를 넘겨준다.
        // 그리고 출발 공항 이랑 도착공항을 선택한다.
        // 출발일도 선택한다.
        // 이에 맞춰 비행 정보를 불러온다. (현재 요청한 내용은 아시아나와  일반 시간으로 고정되어 있음. 즉 프론트 엔드의 입력값에 따라 요청을 다르게 해야함 )
        // 비행정보를 화면에 표와 같이 나타내면, 사용자는 해당 비행 정보를 선택하고 비행정보의 id? Num? 을 가진 채 예약으로 넘어간다.
        for (i in flightInfoArr) {
            println(i)
        }
        return "flightInfo";
    }

    @PostMapping("/flightInfo")
    fun getFlightInfo(departAirportId: Int, arrAirportId: Int, model: Model): String {
        val flightInfo = flightService.getFlightOnAirInfo(departAirportId, arrAirportId);
        model.addAttribute("flight", flightInfo)
        return "flightInfo"
    }
}
