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
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class BookController (private val bookService: BookCheckService, private val flightService: FlightService, private val userService: UserService) {
    @RequestMapping("/book")
    fun book(session : HttpSession) : String {
        println("유저ID : " + session.getAttribute("user_id"))
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
        var adultGender = request.getParameterValues("adultGender")
        var adultFirstName = request.getParameterValues("adultFirstName")
        var adultLastName = request.getParameterValues("adultLastName")
        var adultYear = request.getParameterValues("adultYear")
        var adultMonth = request.getParameterValues("adultMonth")
        var adultDay = request.getParameterValues("adultDay")
        var adultAirline = request.getParameterValues("adultAirLine")

        for (i: Int in 0 until adultNum) {
            println("adultNum 삽입 " + (i + 1) + "번째")
            var instance = PassengerDto (
                Gender = adultGender[i],
                FirstName = adultFirstName[i],
                LastName = adultLastName[i],
                BirthDate = adultYear[i] + " - " + adultMonth[i] + " - " + adultDay[i],
                AirLine = adultAirline[i]
            )

            if (instance != null) {
                println("Gender : " + instance.Gender)
                println("FirstName : " + instance.FirstName)
                println("LastName : " + instance.LastName)
                println("BirthDate : " + instance.BirthDate)
                println("AirLine : " + instance.AirLine)
            }
            passengerList.add(instance)
        }

        //chlidren의 수만큼 passengerList에 값 add
        var childrenGender = request.getParameterValues("childrenGender")
        var childrenFirstName = request.getParameterValues("childrenFirstName")
        var childrenLastName = request.getParameterValues("childrenLastName")
        var childrenYear = request.getParameterValues("childrenYear")
        var childrenMonth = request.getParameterValues("childrenMonth")
        var childrenDay = request.getParameterValues("childrenDay")
        var childrenAirline = request.getParameterValues("childrenAirLine")

        for (i: Int in 0 until childrenNum) {
            var instance = PassengerDto (
                Gender = childrenGender[i],
                FirstName = childrenFirstName[i],
                LastName = childrenLastName[i],
                BirthDate = childrenYear[i] + " - " + childrenMonth[i] + " - " + childrenDay[i],
                AirLine = childrenAirline[i]
            )

            passengerList.add(instance)
        }


        //infant의 수만큼 passengerList에 값 add
        var infantGender = request.getParameterValues("infantGender")
        var infantFirstName = request.getParameterValues("infantFirstName")
        var infantLastName = request.getParameterValues("infantLastName")
        var infantYear = request.getParameterValues("infantYear")
        var infantMonth = request.getParameterValues("infantMonth")
        var infantDay = request.getParameterValues("infantDay")
        var infantAirline = request.getParameterValues("infantAirline")

        for (i: Int in 0 until infantNum) {
            var instance = PassengerDto (
                Gender = infantGender[i],
                FirstName = infantFirstName[i],
                LastName = infantLastName[i],
                BirthDate = infantYear[i] + " - " + infantMonth[i] + " - " + infantDay[i],
                AirLine = infantAirline[i]
            )

            passengerList.add(instance)
        }

        //session에서 passengerInfo를 passengerList로 설정, arrayList임
        session.setAttribute("passengerList", passengerList)
        println("PassengerList의 길이 : " + passengerList.size)

        //유저 잔액 구하고 세션값 수정(혹시 수정되었을수도 있으니..)
        session.setAttribute("point", userService.getBalance(session.getAttribute("user_id") as String))

        //가격 구하는 부분
        var totalNum = adultNum + childrenNum + infantNum
        var grade = session.getAttribute("grade") as String
        var flightNum = session.getAttribute("flightNum") as Int
        var price = flightService.getCharge(grade, flightNum)
        if (price == -1)
            println("grade 잘못 입력됨")

        var totalCharge = price * totalNum

        //세션에 내용 저장
        session.setAttribute("totalCharge", totalCharge)
        return "payment"
    }

    @PostMapping("/payment_proceed")
    fun payment_process(session: HttpSession, model: Model): String {
        var totalCharge = session.getAttribute("totalCharge") as Int
        var userId = session.getAttribute("user_id") as String
        println("totalCharge : $totalCharge, userId : $userId")

        var result = userService.setBalance(totalCharge, userId)
        var passengerList = session.getAttribute("passengerList") as ArrayList<PassengerDto>?

        if (passengerList != null) {
            println("결제 진행 과정에서의 passengerList의 길이 : " + passengerList.size)
            println("passengerList : $passengerList")
        }

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
                return "bookCheck"
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

