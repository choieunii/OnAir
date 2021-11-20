package com.example.onair.service

import com.example.onair.domain.AirportCode.AirportCode
import com.example.onair.domain.AirportCode.AirportCodeRepository
import com.example.onair.domain.test.Test
import com.example.onair.domain.test.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AirportCodeService (private val airportCode: AirportCodeRepository){
    fun getTasks(): List<AirportCode> =
        airportCode.findAll()

    fun getCodeMap(sb : StringBuilder) {
        val numOfRowsArr = sb.split("<numOfRows>","</numOfRows>")
        val numOfRows: Int? = numOfRowsArr?.get(1)?.toInt()
        val codesArr = sb.split("<cityCode>","</cityCode><cityEng>","</cityEng>")

        var codeMap= mutableMapOf<String,String>()
        if (numOfRows != null) {
            if (codesArr != null) {
                for (i in 0 until numOfRows) {
                    codeMap.put(codesArr.get(i*3+1), codesArr.get(i*3+2)) // 도시 코드 : 도시 이름
                }
            }
        }
        println(codeMap)
    }
}