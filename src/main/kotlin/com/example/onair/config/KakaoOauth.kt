package com.example.onair.config

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate


class KakaoOauth (
        val kakaoLoginUrl:String = "https://kauth.kakao.com",
        val kakaoClientId:String = "3c98fa396165c14a012856967b4b7b49",
        val kakaoRedirectUrl:String   = "http://localhost:8080/login/oauth/kakao",
        val kakaoClientSecret:String = "darEri5VGYNHwEAnnYJVCnjyZ7zdV3JB",
        val kakaoTokenBaseUrl:String = "https://kauth.kakao.com/oauth/token"
) {

    fun getOauthRedirectURL(): String {
        return (kakaoLoginUrl.toString() + "/oauth/authorize?response_type=code&client_id=" + kakaoClientId + "&redirect_uri=" + kakaoRedirectUrl)
    }
    fun requestAccessToken(code : String): String {
        val restTemplate = RestTemplate();

        val params = mutableMapOf<String, String>();
        params.put("grant_type", "authorization_code")
        params.put("client_id", kakaoClientId)
        params.put("redirect_uri", kakaoRedirectUrl)
        params.put("client_secret",kakaoClientSecret)
        params.put("code", code)
        println("확인 : "+code )

        val headers = HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded");
        headers.setContentType(MediaType.APPLICATION_JSON);

        val request = HttpEntity<Map<String,String>>(params);
        try{
            val apiResponse = restTemplate.postForEntity(kakaoTokenBaseUrl, request, Map::class.java)
            println(apiResponse)
        }catch(e: HttpClientErrorException){
            println(e.message)
            println(e)
        }
        return "";
    }
    fun getGoogleUserInfo(token : String): Map<*,*>? {
        val requestUrl = "https://www.googleapis.com/oauth2/v2/userinfo";
        val restTemplate = RestTemplate();
        val headers = HttpHeaders();
        headers.set("Authorization", "Bearer $token");
        val request = HttpEntity<String>(headers);
        val response = restTemplate.exchange(requestUrl, HttpMethod.GET, request, Map::class.java)
        val body = response.getBody()
        return body
    }
}