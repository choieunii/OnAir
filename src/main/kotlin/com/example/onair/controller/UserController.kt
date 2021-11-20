package com.example.onair.controller

import com.example.onair.config.FacebookOauth
import com.example.onair.config.GoogleOauth
import com.example.onair.config.KakaoOauth
import com.example.onair.dto.SignUpRequestDto
import com.example.onair.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


@Controller
class UserController (private val userService: UserService){
    @GetMapping("/login")
    fun login(): String {
        return "login";
    } // 세션 등록, 포인트 변수 넣기 = 0,
    @PostMapping("/login")
    fun login(id: String, password: String, session: HttpSession): String {
        val (user, res) = userService.login(id,password)
        println(res)

        if(res.equals("Success")){
            return "index"
        }
        return "login";
    } // 세션 등록, 포인트 변수 넣기 = 0,

    @GetMapping("/signUp")
    fun signUp(): String {
        return "signUp";
    }

    @PostMapping("/signUp")
    fun signUp(request : SignUpRequestDto, model:Model): String {
        println(request)
        val response = userService.signUp(request)
        model.addAttribute("response",response)
        if(response.equals("Success")){
            return "login"
        }
        return "signUp";
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

