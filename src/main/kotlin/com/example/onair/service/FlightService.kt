package com.example.onair.service

import com.example.onair.domain.flight.Flight
import com.example.onair.domain.flight.FlightRepository
import com.example.onair.dto.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.mysql.cj.x.protobuf.MysqlxExpr
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@Service
class FlightService (private val flightRepository: FlightRepository) {
    private val serviceKey = "d6frr1VR9Z69RvkhtYcL5xsIIFrMPhBxN6ttcqtOf6mDsbUytAqspof7/z2gWqpJULdQISLA8MuG/cU0rlR9QQ=="

    fun getFlightInfo() :Array<FlightInfoApiResponseDto>{
        val restTemplate = RestTemplate();
        val openApiUrl = "http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList";
        val uri = UriComponentsBuilder.fromHttpUrl(openApiUrl)
                .queryParam("serviceKey",serviceKey)
                .queryParam("numOfRows",10)
                .queryParam("pageNo",1)
                .queryParam("depAirportId","NAARKJJ")
                .queryParam("arrAirportId","NAARKPC")
                .queryParam("depPlandTime","20201201")
                .queryParam("airlineId","AAR")

        val res = restTemplate.getForEntity(uri.build().toUri(), Map::class.java)
        val response:Map<String, *> = res.body?.get("response") as Map<String, *>;
        val body:Map<String, *> = response?.get("body") as Map<String, *>;
        val items:Map<String, *> = body?.get("items") as Map<String, *>;
        val itemArr = items?.get("item");
        val flightInfoArr = Gson().fromJson(itemArr.toString(), Array<FlightInfoApiResponseDto>::class.java);
        return flightInfoArr
    }

    fun getAirPortInfo(): Array<AirPortApiResponseDto>{
        val restTemplate = RestTemplate();
        val openApiUrl = "http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getArprtList"
        val uri = UriComponentsBuilder.fromHttpUrl(openApiUrl)
                .queryParam("serviceKey",serviceKey)
        val res = restTemplate.getForEntity(uri.build().toUri(), Map::class.java)
        val response:Map<String, *> = res.body?.get("response") as Map<String, *>;
        val body:Map<String, *> = response?.get("body") as Map<String, *>;
        val items:Map<String, *> = body?.get("items") as Map<String, *>;
        val itemArr = items?.get("item");
        val airportArr = Gson().fromJson(itemArr.toString(), Array<AirPortApiResponseDto>::class.java);

        return airportArr;
    }

    fun getAirLineInfo(): Array<AirLineApiResponseDto>{
        val restTemplate = RestTemplate();
        val openApiUrl = "http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getAirmanList"
        val uri = UriComponentsBuilder.fromHttpUrl(openApiUrl)
                .queryParam("serviceKey",serviceKey)
        val res = restTemplate.getForEntity(uri.build().toUri(), Map::class.java)
        val response:Map<String, *> = res.body?.get("response") as Map<String, *>;
        val body:Map<String, *> = response?.get("body") as Map<String, *>;
        val items:Map<String, *> = body?.get("items") as Map<String, *>;
        val itemArr = items?.get("item");
        val airlineArr = Gson().fromJson(itemArr.toString(), Array<AirLineApiResponseDto>::class.java)

        return airlineArr;
    }

    fun post(flight: Flight){
        flightRepository.save(flight)
    }
}
