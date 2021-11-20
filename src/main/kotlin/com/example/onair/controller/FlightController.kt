package com.example.onair.controller

import com.example.onair.domain.flight.Flight
import com.example.onair.domain.test.TestRepository
import com.example.onair.service.FlightService
import com.example.onair.service.TestService
import jdk.internal.org.jline.utils.InputStreamReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

@Controller
class FlightController (private val flightService:FlightService){
    @RequestMapping("/flightInfo")
    fun flightInfo(): String {
        val flight = flightService.getTasks()
        return "flightInfo";
    }

    @GetMapping("/getKobisData")
    @kotlin.Throws(IOException::class)
    fun apiFlight(args: Array<String>){
        val urlBuilder =
            StringBuilder("http://openapi.airport.co.kr/service/rest/FlightScheduleList/getDflightScheduleList") /*URL*/
        urlBuilder.append(
            "?" + URLEncoder.encode(
                "serviceKey",
                "UTF-8"
            ) + "=yte0lqvUBjmStje3Bv6YEA5dectrmAum%2BiBn%2FCK3vg3OZo1NTSaI%2BFpfJYfuA5%2FO3Q6VXBMMMUlmAbCZnIaBVA%3D%3D"
        ) /*Service Key*/
        urlBuilder.append(
            "&" + URLEncoder.encode("schDate", "UTF-8") + "=" + URLEncoder.encode(
                "20121010", // 검색날짜 입력 부분
                "UTF-8"
            )
        ) /*검색일자*/
        urlBuilder.append(
            "&" + URLEncoder.encode("schDeptCityCode", "UTF-8") + "=" + URLEncoder.encode(
                "GMP", // 도착 도시 코드 입력 부분
                "UTF-8"
            )
        ) /*도착 도시 코드*/
        urlBuilder.append(
            "&" + URLEncoder.encode("schArrvCityCode", "UTF-8") + "=" + URLEncoder.encode(
                "PUS", // 출항 도시 코드 입력 부분
                "UTF-8"
            )
        ) /*출항 도시 코드*/
        urlBuilder.append(
            "&" + URLEncoder.encode("schAirLine", "UTF-8") + "=" + URLEncoder.encode(
                "AB", // 항공편 코드 입력 부분
                "UTF-8"
            )
        ) /*항공편 코드*/
        urlBuilder.append(
            "&" + URLEncoder.encode("schFlightNum", "UTF-8") + "=" + URLEncoder.encode(
                "1", // 항공편 넘버 입력 부분
                "UTF-8"
            )
        ) /*항공편 넘버*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")) /*인증키*/
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
        var line: String? = ""
        while (rd.readLine().also { line = it } != null) {
            sb.append(line)
        }
        rd.close()
        conn.disconnect()
        println(sb.toString()) // 샘플 데이터에서 결과 문자열 출력하는 부분

    }

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
/*
    @GetMapping
    ("/getKobisData")private fun callAPI() : String {

    val result  = HashMap<String, Any>()
    var jsonInString = ""

    try {
        val factory = HttpComponentsClientHttpRequestFactory();

        factory.setConnectTimeout(5000)
        factory.setReadTimeout(5000)

        val restTemplate = RestTemplate(factory)
        //restTemplate은 Rest방식 api를 호출할 수 있는 spring 내장 클래스이다.
        //json, xml 응답을 모두 받을 수 있다.

        //header 클래스를 정의해 주고, url을 정의해 주고 exchange method로 api를 호출한다.
        val header = HttpHeaders()
        //header.contentType= MediaType.parseMediaType("application/json")

        val entity = HttpEntity<Map<String, Any>>(header)
        val url = "http://openapi.airport.co.kr/service/rest/AirportCodeList/getAirportCodeList"
        val uri : UriComponents
                = UriComponentsBuilder.fromHttpUrl(url + "?"
                + URLEncoder.encode("serviceKey", "UTF-8").toString() + "=yte0lqvUBjmStje3Bv6YEA5dectrmAum%2BiBn%2FCK3vg3OZo1NTSaI%2BFpfJYfuA5%2FO3Q6VXBMMMUlmAbCZnIaBVA%3D%3D" +"&"
                + URLEncoder.encode("ServiceKey", "UTF-8").toString() + "=" + URLEncoder.encode(
            "",
            "UTF-8"
        )).build()

        //api를 호출하여 데이터를 MAP타입으로 전달 받는다.
        val resultMap : ResponseEntity<Map<*, *>>
                = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map::class.java)

        result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
        result.put("header", resultMap.getHeaders()); //헤더 정보 확인
        resultMap.body?.let { result.put("body", it) };
        //result.put("body", resultMap.getBody())로 넣을 수 없었다. null 가능성 때문인 것 같다.

        //데이터를 string형태로 파싱해줌
        val mapper = ObjectMapper()
        jsonInString = mapper.writeValueAsString(resultMap.getBody());

    } catch (e: Exception){
        when(e) {
            is HttpClientErrorException, is HttpServerErrorException -> {
                result.put("statusCode", "e.getStatusCode()"); //여기랑
                result.put("body", "e.getStatusText()"); //여기는 kotlin에서 오류가 났다. 그래서 함수를 그냥 따옴표로 감싸버림.. 확인 필요
                System.out.println("error!");
                System.out.println(e.toString());
            }else -> {
            result.put("statusCode", "999");
            result.put("body", "excpetion오류");
            System.out.println(e.toString());
        }
        }
    }
    return jsonInString;
}
*/

