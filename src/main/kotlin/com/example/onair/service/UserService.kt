package com.example.onair.service

import com.example.onair.config.FacebookOauth
import com.example.onair.domain.User.UserRepository
import com.example.onair.config.GoogleOauth
import com.example.onair.config.KakaoOauth
import com.example.onair.domain.User.User
import com.example.onair.dto.*
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession


@Service
class UserService(private val userRepository: UserRepository) {
    fun login(user_id: String, password: String): Pair<User?, String> {
        val user = userRepository.findByUserId(user_id);
        if (user == null) {
            return Pair(null, "ID가 존재하지 않습니다.")
        } else {
            if (user?.password.equals(password)) {
                return Pair(user, "Success");
            } else {
                return Pair(null, "비밀번호가 일치하지 않습니다.");
            }
        }
    }

    fun signUp(request: SignUpRequestDto): String {
        val newUser = User(
            userId = request.userId,
            password = request.password,
            name = request.name,
            age = request.age,
            phoneNum = request.phoneNum,
            email = request.email,
            point = 0
        ); // User 테이블에 값을 저장하기 위해 생성

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
        val checkUserEmail =
            userRepository.findByEmail(res?.get("email").toString()); // 사용자 정보중 Email이 있을 시 디비에서 email을 검색

        if (checkUserEmail != null) return Pair(checkUserEmail, "login"); //없으면 필요한 정보와, 로그인이라는 값을 리턴함

        val newUser = SocialLoginSignUpRequestDto(
            userId = res?.get("id").toString(),
            name = res?.get("name").toString(),
            email = res?.get("email").toString(),
            age = "",
            type = "google"
        );
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

        val newUser = SocialLoginSignUpRequestDto(
            userId = res?.get("id").toString(),
            name = res?.get("name").toString(),
            age = "",
            email = "",
            type = "facebook"
        );

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

        val newUser = SocialLoginSignUpRequestDto(
            userId = res.get("id").toString(),
            name = properties.get("nickname").toString(),
            age = profile.get("age_range").toString(),
            email = profile.get("email").toString(),
            type = "kakao"
        );
        return Pair(newUser, "signUp");
    }

    fun updateMyPageInfo(req: MyPageUpdateDto): User {
        val user = userRepository.findByUserId(req.userId);
        val updateUser = User(
            id = user!!.id,
            userId = req.userId,
            password = req.password,
            age = req.age,
            email = req.email,
            name = req.name,
            phoneNum = req.phoneNum,
            point = user.point
        )
        userRepository.save(updateUser);
        return updateUser;
    }

    fun updateMyPoint(point: Int, userId: String): Int? {
        val user = userRepository.findByUserId(userId);
        val newPoint = user?.point!!.plus(point);
        val update = userRepository.updateUserPoint(newPoint, userId);
        return newPoint;
    }

    fun setSessionUser(user: User?, session: HttpSession) {
        session.setAttribute("id", user?.id);
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

    fun setBalance(totalPrice: Int, userId: String): String {
        //기존 잔고 저장
        var oldInfo = userRepository.findByUserId(userId)

        //유저 정보를 찾지 못했으면 NotFound반환
        if (oldInfo == null)
            return "NotFound"

        //차감 후 값 계산
        var calculatedBalance = oldInfo.point - totalPrice

        //수행
        userRepository.setBalance(calculatedBalance, userId)

        //수행 후 잔고 저장
        var newInfo = userRepository.findByUserId(userId)

        if (newInfo != null) {
            //멀쩡히 잘 차감되었으면 success 반환
            if (newInfo.point == calculatedBalance)
                return "success"

            //아니면 원래 값으로 되돌리고 failed 반환
            else {
                userRepository.setBalance(oldInfo.point, userId)
                return "failed"
            }
        }
        else
            return "NotFound"
    }
}