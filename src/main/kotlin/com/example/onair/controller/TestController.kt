package com.example.onair.controller

import com.example.onair.service.TestService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder

@Controller
class TestController (private val testService:TestService){
    @RequestMapping("/index")
    fun index(): String {
        val test = testService.getTasks()
        return "index";
    }
    @RequestMapping("/login")
    fun login(): String {
        val test = testService.getTasks()
        return "login";
    }
    @RequestMapping("/book")
    fun book(): String {
        val test = testService.getTasks()
        return "book";
    }
    @RequestMapping("/book2")
    fun book2(): String {
        val test = testService.getTasks()
        return "book2";
    }
    @RequestMapping("/bookCheck")
    fun bookCheck(): String {
        val test = testService.getTasks()
        return "bookCheck";
    }
    @RequestMapping("/flightInfo")
    fun flightInfo(): String {
        val test = testService.getTasks()
        return "flightInfo";
    }
    @GetMapping("/getKobisData")
    private fun callAPI() : String {

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
                    + URLEncoder.encode("serviceKey", "UTF-8").toString() + "=gXDDYKSblKYs7tHsqlU35oMYuyw1dU0Bz1fgv25w1waAsl9zAm6vDdipldEdCQAuGBaY593b%2FCMcvZTojZa4Cg%3D%3D" +"&"
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

}

