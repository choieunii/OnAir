package com.example.onair.config

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

class GoogleOauth (
        val googleLoginUrl:String = "https://accounts.google.com",
        val googleClientId:String = "988322772158-f4vi9q2dkluucvhht4h2v6ao2tf7hs4c.apps.googleusercontent.com",
        val googleRedirectUrl:String   = "http://localhost:8080/login/oauth/google",
        val googleClientSecret:String = "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",
        val googleTokenBaseUrl:String = "https://oauth2.googleapis.com/token"
) {

    fun getOauthRedirectURL(): String {
        return (googleLoginUrl.toString() + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri=" + googleRedirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline")
    }
    fun requestAccessToken(code : String): String {
        val restTemplate = RestTemplate();
        val params = mutableMapOf<String, String>();
        params.put("code", code)
        params.put("client_id", googleClientId)
        params.put("client_secret", googleClientSecret)
        params.put("redirect_uri", googleRedirectUrl)
        params.put("grant_type", "authorization_code");

        val apiResponse = restTemplate.postForEntity(googleTokenBaseUrl, params, Map::class.java)
        val responseBody = apiResponse.getBody()
        println(responseBody)
        val token = responseBody?.get("access_token").toString();
        return token;
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