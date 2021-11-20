package com.example.onair.service

import com.example.onair.config.FacebookOauth
import com.example.onair.domain.User.UserRepository
import com.example.onair.config.GoogleOauth
import com.example.onair.config.KakaoOauth
import com.example.onair.domain.User.User
import com.example.onair.dto.SignUpRequestDto
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {
    fun login(user_id: String, password: String): String {
        val user = userRepository.findByUserId(user_id);
        if (user?.password.equals(password)) {
            return "Success"
        }
        return "Failed"
    }

    fun signUp(request: SignUpRequestDto): String {
        val newUser = User(
                userId = request.userId,
                password = request.password,
                name = request.name,
                age = request.age,
                phoneNum = request.phoneNum,
                email = request.email);

        val checkId = userRepository.findByUserId(request.userId);
        val checkEmail = userRepository.findByEmail(request.email);

        if(checkId != null) return "ID 가 이미 존재합니다."
        if(checkEmail!=null) return "Email이 이미 존재합니다."

        userRepository.save(newUser);

        return "Success";
    }

    fun googleLogin(code: String): String {
        val res = GoogleOauth().requestAccessToken(code);
        println(res)
        return res;
    }

    fun getGoogleUserInfo(token: String): String {
        val res = GoogleOauth().getGoogleUserInfo(token);
        println(res)
        return "";
    }

    fun facebookLogin(code: String): String {
        val res = FacebookOauth().requestAccessToken(code);
        println(res)
        return res;
    }

    fun getFacebookUserInfo(token: String): String {
        val res = FacebookOauth().getFacebookUserInfo(token);
        println(res)
        return "";
    }

    fun kakaoLogin(code: String): String {
        val res = KakaoOauth().requestAccessToken(code);
        println(res)
        return res;
    }
}