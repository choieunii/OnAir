package com.example.onair.controller

import com.example.onair.config.FacebookOauth
import com.example.onair.config.GoogleOauth
import com.example.onair.config.KakaoOauth
import com.example.onair.domain.User.User
import com.example.onair.dto.MyPageUpdateDto
import com.example.onair.dto.SignUpRequestDto
import com.example.onair.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


@Controller
class UserController(private val userService: UserService) {
    @GetMapping("/login")
    fun login(): String {
        return "login";
    } // 세션 등록, 포인트 변수 넣기 = 0,

    @PostMapping("/login")
    fun login(id: String, password: String, model: Model, session: HttpSession): String {
        val (user, res) = userService.login(id, password)
        if (res.equals("Success")) {
            userService.setSessionUser(user, session); // Service 보면 코드 확인 가능
            // 세션에 사용자 정보가 추가된다.
            return "index"
        }
        model.addAttribute("response", res)
        println(res)
        return "login";
    }

    @RequestMapping("/logout")
    fun logout(session: HttpSession): String {
        userService.resetSessionUser(session); // Service 보면 코드 확인 가능
        return "index"
    }

    @GetMapping("/signUp")
    fun signUp(request: HttpServletRequest?): String {
        return "signUp";
    }

    @PostMapping("/signUp")
    fun signUp(req: SignUpRequestDto, model: Model, request: HttpServletRequest?): String {
        println(req)
        val response = userService.signUp(req)
        model.addAttribute("response", response)
        //위의 코드처럼 작성 시 아래의 return 되는 페이지에서 해당 변수의 값을 사용할수 있음.
        // jsp에서 사용하고 싶은 변수는 model에 담아 전달해주면 된다.
        if (response.equals("Success")) {
            if (req.socialType != "") {
                return "redirect:/login/${req.socialType}"
            } else {
                return "login" // 회원가입에 성공하면 로그인 페이지로 이동
            }
        }
        return "signUp";//실패하면 회원가입 페이지로 이동
    }

    @RequestMapping("/login/google")
    fun getGoogleAuthUrl(request: HttpServletRequest?): String {
        return "redirect:" + GoogleOauth().getOauthRedirectURL();
    }

    @RequestMapping("/login/facebook")
    fun getFacebookAuthUrl(request: HttpServletRequest?): String {
        return "redirect:" + FacebookOauth().getOauthRedirectURL();
    }

    @RequestMapping("/login/kakao")
    fun getKakaoAuthUrl(request: HttpServletRequest?): String {
        return "redirect:" + KakaoOauth().getOauthRedirectURL();
    }


    @RequestMapping("/login/oauth/google")
    fun googleLogin(code: String, redirectAttributes: RedirectAttributes, session: HttpSession): String {
        val token = userService.googleLogin(code);
        val (user, res) = userService.getGoogleUserInfo(token);
        if (res == "login") {
            //만약 소셜로그인으로 회원가입을 한적이 있을 경우 로그인 되어 세션에 값이 저장된다.
            userService.setSessionUser(user as User?, session);
            return "redirect:/index";
        } else {
            redirectAttributes.addFlashAttribute("user", user) // redirect 시 이렇게 해야 jsp에서 값을 읽을 수 있음
            //처음 소셜로그인으로 로그인 할 경우 회원가입이 필요하다.
            //추가적인 정보를 적어야 하기 때문에 소셜로그인에서 받아올 수 있는 값을 받아와 user에 넣어놨음.
            //jsp에서는 해당 값을 input에 넣어서 없는 값만 사용자에게 입력 받도록 한다.
            //카카오, 구글, 페북 각각 내용이 다르므로, 주석에 있는 양식을 참고하여 필요한 내용만 가져다 쓰기
            //(userId=123156, name=홍길동, email=123456@gmail.com)
            return "redirect:/signUp"
        }
    }

    @RequestMapping("/login/oauth/facebook")
    fun facebookLogin(code: String, redirectAttributes: RedirectAttributes, session: HttpSession): String {
        val token = userService.facebookLogin(code);
        val (user, res) = userService.getFacebookUserInfo(token);
        if (res == "login") {
            userService.setSessionUser(user as User?, session);
            return "redirect:/index";
        } else {
            //(userId=12323130, name=홍길동)
            redirectAttributes.addFlashAttribute("user", user)
            return "redirect:/signUp"
        }
    }

    @RequestMapping("/login/oauth/kakao")
    fun kakaoLogin(code: String, redirectAttributes: RedirectAttributes, session: HttpSession): String {
        val token = userService.kakaoLogin(code);
        val (user, res) = userService.getKakaoUserInfo(token);
        if (res == "login") {
            userService.setSessionUser(user as User?, session);
            return "redirect:/index";
        } else {
            redirectAttributes.addFlashAttribute("user", user)
            println(user)
            //(userId=11111, name=홍길동, age=20~29, email=123456@naver.com)
            return "redirect:/signUp"
        }
    }

    @RequestMapping("/myPage")
    fun myPageInfo(session: HttpSession, model: Model): String {
        val userId = session.getAttribute("user_id");
        val user = userService.getUserById(userId as String);
        userService.setSessionUser(user, session);

        return "myPage";
    }


    @PostMapping("/myPage")
    fun updateMyPage(req: MyPageUpdateDto,session: HttpSession, model: Model): String{
        val updateUser = userService.updateMyPageInfo(req);
        var res="회원 정보가 수정되었습니다."
        userService.setSessionUser(updateUser, session);
        model.addAttribute("response", res)
        return "myPage";
    }

    @PostMapping("/point")
    fun updateMyPoint(point: Int, session: HttpSession, model: Model): String{
        val userId:String = session.getAttribute("user_id") as String;
        val updatePoint = userService.updateMyPoint(point, userId);
        var res="포인트 충전이 완료되었습니다."
        session.setAttribute("point", updatePoint);
        model.addAttribute("response", res)
        return "myPage";
    }
}

