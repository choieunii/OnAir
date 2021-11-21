package com.example.onair.service

import com.example.onair.config.FacebookOauth
import com.example.onair.domain.User.UserRepository
import com.example.onair.config.GoogleOauth
import com.example.onair.config.KakaoOauth
import com.example.onair.domain.User.User
import com.example.onair.dto.FacebookSignUpRequestDto
import com.example.onair.dto.GoogleSignUpRequestDto
import com.example.onair.dto.KakaoSignUpRequestDto
import com.example.onair.dto.SignUpRequestDto
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession


@Service
class UserService(private val userRepository: UserRepository) {
    fun login(user_id: String, password: String): Pair<User?, String> {
        val user = userRepository.findByUserId(user_id);
        if (user?.password.equals(password)) {
            return Pair(user, "Success");
        }
        return Pair(null, "Failed");
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

        if (checkId != null) return "ID 가 이미 존재합니다."
        if (checkEmail != null) return "Email이 이미 존재합니다."

        userRepository.save(newUser);

        return "Success";
    }

    fun googleLogin(code: String): String {
        val res = GoogleOauth().requestAccessToken(code);
        println(res)
        return res;
    }

    fun getGoogleUserInfo(token: String): Pair<*, String> {
        val res = GoogleOauth().getGoogleUserInfo(token);
        println(res)
        val checkUserEmail = userRepository.findByEmail(res?.get("email").toString());

        if (checkUserEmail != null) return Pair(checkUserEmail, "login");

        val newUser = GoogleSignUpRequestDto(
                userId = res?.get("id").toString(),
                name = res?.get("name").toString(),
                email = res?.get("email").toString());
        return Pair(newUser, "signUp");
    }

    fun facebookLogin(code: String): String {
        val res = FacebookOauth().requestAccessToken(code);
        return res;
    }

    fun getFacebookUserInfo(token: String): Pair<*, String> {
        val res = FacebookOauth().getFacebookUserInfo(token);
        println(res)
        val checkUserId = userRepository.findByUserId(res?.get("id").toString());

        if (checkUserId != null) return Pair(checkUserId, "login");

        val newUser = FacebookSignUpRequestDto(
                userId = res?.get("id").toString(),
                name = res?.get("name").toString());

        return Pair(newUser, "signUp");
    }

    fun kakaoLogin(code: String): String {
        val res = KakaoOauth().requestAccessToken(code);
        println(res)
        return res;
    }

    fun getKakaoUserInfo(token: String): Pair<*, String> {
        val res = KakaoOauth().getKakaoUserInfo(token);
        val properties:Map<String,*> = res?.get("properties") as Map<String, *>
        val profile:Map<String,*> = res?.get("kakao_account") as Map<String, *>

        val checkUserEmail = userRepository.findByEmail(profile.get("email").toString());

        if (checkUserEmail != null) return Pair(checkUserEmail, "login");

        val newUser = KakaoSignUpRequestDto(
                userId = res.get("id").toString(),
                name = properties.get("nickname").toString(),
                age = profile.get("age_range").toString(),
                email = profile.get("email").toString(),
                );
        return Pair(newUser, "signUp");
    }
}