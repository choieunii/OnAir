package com.example.onair.controller

import com.example.onair.dto.BookCheckRequestDto
import com.example.onair.dto.PassengerDto
import com.example.onair.service.BookCheckService
import com.example.onair.service.FlightService
import com.example.onair.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class BookController (private val bookService: BookCheckService, private val flightService: FlightService, private val userService: UserService) {
    @RequestMapping("/book")
    fun book(session : HttpSession) : String {
        if (session.getAttribute("user_id") == null) {
            return "login"
        }
        else
            return "book"
    }

    @RequestMapping("/payment")
    fun payment(request: HttpServletRequest, session: HttpSession, model: Model): String {
        var adultNum = Integer.parseInt(request.getParameter("adult"))
        var childrenNum = Integer.parseInt(request.getParameter("children"))
        var infantNum = Integer.parseInt(request.getParameter("infant"))

        println("adultNum : $adultNum")
        println("childrenNum : $childrenNum")
        println("infantNum : $infantNum")

        var passengerList = arrayListOf<PassengerDto?>()

        //adult의 수만큼 passengerList에 값 add

        for (i: Int in 0 until adultNum) {
            var adultGender = request.getParameter("adultGender$i")
            var adultFirstName = request.getParameter("adultFirstName$i")
            var adultLastName = request.getParameter("adultLastName$i")
            var adultYear = request.getParameter("adultYear$i")
            var adultMonth = request.getParameter("adultMonth$i")
            var adultDay = request.getParameter("adultDay$i")
            var adultAirline = request.getParameter("adultAirLine$i")
            var instance = PassengerDto (
                Gender = adultGender,
                FirstName = adultFirstName,
                LastName = adultLastName,
                BirthDate = "$adultYear - $adultMonth - $adultDay",
                AirLine = adultAirline
            )

            passengerList.add(instance)
        }

        //chlidren의 수만큼 passengerList에 값 add
        for (i: Int in 0 until childrenNum) {
            var childrenGender = request.getParameter("childrenGender$i")
            var childrenFirstName = request.getParameter("childrenFirstName$i")
            var childrenLastName = request.getParameter("childrenLastName$i")
            var childrenYear = request.getParameter("childrenYear$i")
            var childrenMonth = request.getParameter("childrenMonth$i")
            var childrenDay = request.getParameter("childrenDay$i")
            var childrenAirline = request.getParameter("childrenAirLine$i")
            var instance = PassengerDto (
                Gender = childrenGender,
                FirstName = childrenFirstName,
                LastName = childrenLastName,
                BirthDate = "$childrenYear - $childrenMonth - $childrenDay",
                AirLine = childrenAirline
            )

            passengerList.add(instance)
        }


        //infant의 수만큼 passengerList에 값 add

        for (i: Int in 0 until infantNum) {
            var infantGender = request.getParameter("infantGender$i")
            var infantFirstName = request.getParameter("infantFirstName$i")
            var infantLastName = request.getParameter("infantLastName$i")
            var infantYear = request.getParameter("infantYear$i")
            var infantMonth = request.getParameter("infantMonth$i")
            var infantDay = request.getParameter("infantDay$i")
            var infantAirline = request.getParameter("infantAirLine$i")
            var instance = PassengerDto (
                Gender = infantGender,
                FirstName = infantFirstName,
                LastName = infantLastName,
                BirthDate = "$infantYear - $infantMonth - $infantDay",
                AirLine = infantAirline
            )

            passengerList.add(instance)
        }

        //session에서 passengerInfo를 passengerList로 설정, arrayList임
        session.setAttribute("passengerList", passengerList)

        //유저 잔액 구하고 세션값 수정(혹시 수정되었을수도 있으니..)
        session.setAttribute("point", userService.getBalance(session.getAttribute("user_id") as String))

        //가격 구하는 부분
        var totalNum = adultNum + childrenNum + infantNum
        var grade = session.getAttribute("grade") as String
        var flightNum = session.getAttribute("flightNum") as Int
        var price = flightService.getCharge(grade, flightNum)

        var totalCharge = price * totalNum

        //세션에 내용 저장
        session.setAttribute("totalCharge", totalCharge)
        return "payment"
    }

    @PostMapping("/payment_proceed")
    fun payment_process(session: HttpSession, model: Model, redirectAttributes: RedirectAttributes): String {
        var totalCharge = session.getAttribute("totalCharge") as Int
        var userId = session.getAttribute("user_id") as String

        var result = userService.setBalance(totalCharge, userId)
        var passengerList = session.getAttribute("passengerList") as ArrayList<PassengerDto>?

        //성공 시 사람 수만큼 DB에 추가
        if (result.equals("success")) {

            var flightNum = session.getAttribute("flightNum") as Int
            var seatClass = session.getAttribute("grade") as String
            if (passengerList != null) {
                for (i: Int in 0 until passengerList.size) {
                    var result = bookService.addToDB(passengerList.get(i), userId, flightNum, seatClass)

                    //등록 실패 시 일단 돌아감. 근데 차감된 금액하고 중간에 끊어진 저장을 어떻게 해야할지 정하긴 해야 할듯
                    if (result.equals("failed")) {
                        model.addAttribute("result", result)
                        return "payment"
                    }
                }
                //사용했던 session의 attribute 초기화
                session.removeAttribute("flightNum")
                session.removeAttribute("grade")
                session.removeAttribute("PassengerList")
                session.removeAttribute("totalCharge")

                //예약 확인 페이지로
                redirectAttributes.addFlashAttribute("result", "success")
                return "redirect:/bookCheck"
            } else {
                //값 업데이트 실패 시 실패했다는 값 model에 넣어서 결제 페이지로.
                model.addAttribute("result", "noPassenger")
                return "payment"
            }
        } else {
            model.addAttribute("result" , "failed")
            return "book"
        }
    }
}

