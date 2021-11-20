package com.example.onair.controller

import jdk.internal.org.jline.utils.InputStreamReader
import org.springframework.web.bind.annotation.GetMapping
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class AirportCodeController {
    @GetMapping("/getAirportCodeInformation")
    @kotlin.Throws(IOException::class)
    fun apiCode(args: Array<String>) {
        val urlBuilder = StringBuilder("http://openapi.airport.co.kr/service/rest/AirportCodeList/getAirportCodeList") /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=yte0lqvUBjmStje3Bv6YEA5dectrmAum%2BiBn%2FCK3vg3OZo1NTSaI%2BFpfJYfuA5%2FO3Q6VXBMMMUlmAbCZnIaBVA%3D%3D") /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")) /*인증키*/
        val url = URL(urlBuilder.toString())
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Content-type", "application/json")
        println("Response code: " + conn.responseCode)
        val rd: BufferedReader = if (conn.responseCode in 200..300) {
            BufferedReader(InputStreamReader(conn.inputStream))
        } else {
            BufferedReader(InputStreamReader(conn.errorStream))
        }
        val sb = StringBuilder()
        var line: String?
        while (rd.readLine().also { line = it } != null) {
            sb.append(line)
        }
        rd.close()
        conn.disconnect()
        println(sb.toString())

        val numOfRowsArr = line?.split("<numOfRows>","</numOfRows>")
        val numOfRows: Int? = numOfRowsArr?.get(1)?.toInt()
        val codesArr = line?.split("<cityCode>","</cityCode><cityEng>","</cityEng>")

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