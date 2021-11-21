package com.example.onair.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


class KakaoOauth(
        val kakaoLoginUrl: String = "",
        val kakaoClientId: String = "",
        val kakaoRedirectUrl: String = "",
        val kakaoClientSecret: String = "",
        val kakaoTokenBaseUrl: String = ""
) {

    fun getOauthRedirectURL(): String {
        return (kakaoLoginUrl.toString() + "/oauth/authorize?response_type=code&client_id=" + kakaoClientId + "&redirect_uri=" + kakaoRedirectUrl)
    }

    fun requestAccessToken(code: String): String {
        val restTemplate = RestTemplate();
        val data = LinkedMultiValueMap<String, String>();
        data["grant_type"] = "authorization_code"
        data["client_id"] = kakaoClientId
        data["redirect_uri"] = kakaoRedirectUrl
        data["client_secret"] = kakaoClientSecret
        data["code"] = code

        val headers = HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED;

        val request = HttpEntity<MultiValueMap<String, String>>(data, headers);

        try {
            val apiResponse = restTemplate.postForEntity(kakaoTokenBaseUrl, request, Map::class.java)
            val responseBody = apiResponse.getBody()
            val token = responseBody?.get("access_token").toString();
            return token;
        } catch (e: HttpClientErrorException) {
            println(e.message)
            println(e)
        }
        return "";
    }

    fun getKakaoUserInfo(token: String): Map<*, *>? {
        val requestUrl = "https://kapi.kakao.com/v2/user/me";
        val restTemplate = RestTemplate();
        val headers = HttpHeaders();
        println(token)
        headers.set("Authorization", "Bearer $token");
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        val request = HttpEntity<MultiValueMap<String,String>>(headers);
        val response = restTemplate.exchange(requestUrl, HttpMethod.GET, request, Map::class.java)
        val body = response.getBody()
        return body
    }
}