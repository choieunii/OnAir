package com.example.onair.controller

import com.example.onair.config.FacebookOauth
import com.example.onair.config.GoogleOauth
import com.example.onair.config.KakaoOauth
import com.example.onair.dto.SignUpRequestDto
import com.example.onair.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest


@Controller
class UserController (private val userService: UserService){
    @RequestMapping("/login")
    fun login(id: String, password: String): String {
        val response = userService.login(id,password)
        println(response)
        if(response.equals("Success")){
            return "index"
        }else{
            return "login";
        }
    } // 세션 등록, 포인트 변수 넣기 = 0,
    @RequestMapping("/signup")
    fun signUp(request : SignUpRequestDto): String {
        val response = userService.signUp(request)
        if(response.equals("Success")){
            return "login"
        }else{
            return "signup";
        }
    }

    @RequestMapping("/login/google")
    fun getGoogleAuthUrl(request: HttpServletRequest?): String {
        return "redirect:"+GoogleOauth().getOauthRedirectURL();
    }

    @RequestMapping("/login/oauth/google")
    fun googleLogin(code: String): String {
        val token = userService.googleLogin(code);
        val googleUserInfo = userService.getGoogleUserInfo(token);
        return "redirect:/index";
    }

    @RequestMapping("/login/facebook")
    fun getFacebookAuthUrl(request: HttpServletRequest?): String {
        return "redirect:"+FacebookOauth().getOauthRedirectURL();
    }

    @RequestMapping("/login/oauth/facebook")
    fun facebookLogin(code:String): String {
        val token = userService.facebookLogin(code);
        val facebookUserInfo = userService.getFacebookUserInfo(token);
        return "redirect:/index";
    }
    @RequestMapping("/login/kakao")
    fun getKakaoAuthUrl(request: HttpServletRequest?): String {
        return "redirect:"+ KakaoOauth().getOauthRedirectURL();
    }

    @RequestMapping("/login/oauth/kakao")
    fun kakaoLogin(code:String): String {
        val token = userService.kakaoLogin(code);
        return "redirect:/index";
    }

}

