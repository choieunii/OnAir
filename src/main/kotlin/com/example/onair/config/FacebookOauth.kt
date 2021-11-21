package com.example.onair.config

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class FacebookOauth(
        val facebookLoginUrl: String = "",
        val facebookClientId: String = "",
        val facebookRedirectUrl: String = "",
        val facebookClientSecret: String = "",
        val facebookTokenBaseUrl: String = ""
) {

    fun getOauthRedirectURL(): String {
        return facebookLoginUrl.toString() + "?client_id=" + facebookClientId + "&redirect_uri=" + facebookRedirectUrl
    }

    fun requestAccessToken(code: String): String {
        val restTemplate = RestTemplate();
        val uri = UriComponentsBuilder.fromHttpUrl(facebookTokenBaseUrl)
                .queryParam("client_id", facebookClientId)
                .queryParam("redirect_uri", facebookRedirectUrl)
                .queryParam("client_secret", facebookClientSecret)
                .queryParam("code", code);

        val apiResponse = restTemplate.getForEntity(uri.build().toUri(), Map::class.java)
        val responseBody = apiResponse.getBody()
        println(responseBody)
        val token = responseBody?.get("access_token").toString();

        return token;
    }

    fun getFacebookUserInfo(token: String):  Map<*,*>? {
        val restTemplate = RestTemplate();
        val requestUrl = "https://graph.facebook.com/me";
        val uri = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("fields", "id,name,email,picture,birthday")
                .queryParam("access_token", token)
        val response = restTemplate.getForEntity(uri.build().toUri(), Map::class.java)
        val body = response.getBody()
        println(body)
        return body;
    }
}