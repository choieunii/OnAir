package com.example.onair.controller

import com.example.onair.config.FacebookOauth
import com.example.onair.config.GoogleOauth
import com.example.onair.config.KakaoOauth
import com.example.onair.domain.User.User
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
    fun login(id: String, password: String, session:HttpSession): String {
        val (user, res) = userService.login(id,password)

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

    @RequestMapping("/login/facebook")
    fun getFacebookAuthUrl(request: HttpServletRequest?): String {
        return "redirect:"+FacebookOauth().getOauthRedirectURL();
    }

    @RequestMapping("/login/kakao")
    fun getKakaoAuthUrl(request: HttpServletRequest?): String {
        return "redirect:"+ KakaoOauth().getOauthRedirectURL();
    }


    @RequestMapping("/login/oauth/google")
    fun googleLogin(code: String,model:Model): String {
        val token = userService.googleLogin(code);
        val (user, res)  = userService.getGoogleUserInfo(token);
        println(user)
        if(res == "login"){
            return "redirect:/index";
        }else{
            model.addAttribute("user",user)
            //(userId=123156, name=홍길동, email=123456@gmail.com)
            return "redirect:/signUp"
        }
    }

    @RequestMapping("/login/oauth/facebook")
    fun facebookLogin(code:String,model:Model): String {
        val token = userService.facebookLogin(code);
        val (user, res)  = userService.getFacebookUserInfo(token);
        println(user)
        if(res == "login"){
            return "redirect:/index";
        }else{
            //(userId=12323130, name=홍길동)
            model.addAttribute("user",user)
            return "redirect:/signUp"
        }
    }

    @RequestMapping("/login/oauth/kakao")
    fun kakaoLogin(code:String,model:Model): String {
        val token = userService.kakaoLogin(code);
        val (user, res)  = userService.getKakaoUserInfo(token);
        println(user)
        if(res == "login"){
            return "redirect:/index";
        }else{
            model.addAttribute("user",user)
            //(userId=11111, name=홍길동, age=20~29, email=123456@naver.com)
            return "redirect:/signUp"
        }
    }

}

