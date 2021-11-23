package com.example.onair.service

import com.example.onair.domain.flight.FlightRepository
import org.springframework.stereotype.Service

@Service
class FlightService (private var flightRepository : FlightRepository) {
    fun getFlightNum(dep : String, ari : String, date : String) : Int{
        var info = flightRepository.getFlightNum(dep, ari, date)

        //-1이 반횐되면 검색 결과 없음
        if (info == null)
            return -1
        //-2가 반환되면 둘 이상의 결과가 나온 것
        else if (info.size > 1)
            return -2
        else
            return info.get(0).flightNum
    }

    fun getCharge(type : String, flightNum : Int) : Int {
        var info = flightRepository.findInfoByFlightNum(flightNum)

        //-1이 반횐되면 검색 결과 없음
        if (info == null)
            return -1

        if (type.equals("economy"))
            return info.economyCharge
        else if (type.equals("business"))
            return info.businessCharge
        else if (type.equals("first"))
            return info.firstCharge

        //-2이 반환되면 seatType이 적절하지 못한 것
        else
            return -2
    }
}
