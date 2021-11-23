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
                email = request.email,
                point = 0); // User 테이블에 값을 저장하기 위해 생성

        val checkId = userRepository.findByUserId(request.userId);
        val checkEmail = userRepository.findByEmail(request.email);

        if (checkId != null) return "ID 가 이미 존재합니다."
        if (checkEmail != null) return "Email이 이미 존재합니다."

        userRepository.save(newUser);

        return "Success";
    }

    fun googleLogin(code: String): String {
        val res = GoogleOauth().requestAccessToken(code);
        return res;
    }

    fun getGoogleUserInfo(token: String): Pair<*, String> { //리턴을 User 객체, String 둘다 하고 싶어서 Pair로 리턴함
        val res = GoogleOauth().getGoogleUserInfo(token);// 토큰값을 받아와서 사용자 정보를 받아옴.
        val checkUserEmail = userRepository.findByEmail(res?.get("email").toString()); // 사용자 정보중 Email이 있을 시 디비에서 email을 검색

        if (checkUserEmail != null) return Pair(checkUserEmail, "login"); //없으면 필요한 정보와, 로그인이라는 값을 리턴함

        val newUser = GoogleSignUpRequestDto(
                userId = res?.get("id").toString(),
                name = res?.get("name").toString(),
                email = res?.get("email").toString());
        // User 가 아닌 더 적은 내용을 리턴하고 싶어서 data class를 하나 생성하였음.
        //위에 보면 리턴이 Pair<User,String> 가 아닌 Pair<*,String>이기에 다르게 리턴이 가능하다.
        return Pair(newUser, "signUp");
    }

    fun facebookLogin(code: String): String {
        val res = FacebookOauth().requestAccessToken(code);
        return res;
    }

    fun getFacebookUserInfo(token: String): Pair<*, String> {
        val res = FacebookOauth().getFacebookUserInfo(token);
        val checkUserId = userRepository.findByUserId(res?.get("id").toString());

        if (checkUserId != null) return Pair(checkUserId, "login");

        val newUser = FacebookSignUpRequestDto(
                userId = res?.get("id").toString(),
                name = res?.get("name").toString());

        return Pair(newUser, "signUp");
    }

    fun kakaoLogin(code: String): String {
        val res = KakaoOauth().requestAccessToken(code);
        return res;
    }

    fun getKakaoUserInfo(token: String): Pair<*, String> {
        val res = KakaoOauth().getKakaoUserInfo(token);
        val properties: Map<String, *> = res?.get("properties") as Map<String, *>
        val profile: Map<String, *> = res.get("kakao_account") as Map<String, *>

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

    fun setSessionUser(user: User?, session: HttpSession) {
        session.setAttribute("user_id", user?.userId);
        session.setAttribute("name", user?.name);
        session.setAttribute("age", user?.age);
        session.setAttribute("phone_num", user?.phoneNum);
        session.setAttribute("email", user?.email);
        session.setAttribute("point", user?.point);
    }

    fun resetSessionUser(session: HttpSession) {
        session.invalidate();
    }
    
//    fun setBalance(totalPrice : Int, userId : String) : String{
//        //기존 잔고 저장
//        var oldBalance = userRepository.getBalance(userId)
//
//        //차감 후 값 계산
//        var calculatedBalance = oldBalance - totalPrice
//
//        //수행
//        userRepository.setBalance(calculatedBalance, userId)
//
//        //수행 후 잔고 저장
//        var newBalance = userRepository.getBalance(userId)
//
//        //멀쩡히 잘 차감되었으면 success 반환
//        if (newBalance == calculatedBalance)
//            return "success"
//
//        //아니면 원래 값으로 되돌리고 failed 반환
//        else {
//            userRepository.setBalance(oldBalance, userId)
//            return "failed"
//        }
//    }
    
}
