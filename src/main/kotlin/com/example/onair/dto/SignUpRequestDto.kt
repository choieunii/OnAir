package com.example.onair.dto

import javax.persistence.Column

data class SignUpRequestDto (
        var userId: String,
        var password: String,
        var name: String,
        var age: String,
        var phoneNum: String,
        var email: String
)

data class GoogleSignUpRequestDto (
        var userId: String,
        var name: String,
        var email: String
)

data class KakaoSignUpRequestDto (
        var userId: String,
        var name: String,
        var age: String,
        var email: String
)

data class FacebookSignUpRequestDto (
        var userId: String,
        var name: String,
)