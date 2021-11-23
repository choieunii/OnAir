package com.example.onair.controller

import com.example.onair.dto.BookCheckRequestDto
import com.example.onair.service.BookCheckService
import com.example.onair.service.FlightService
import com.example.onair.service.UserService
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

class BookController (private val bookService: BookCheckService, private val flightService: FlightService, private val userService: UserService) {
    @RequestMapping("/payment")
    fun payment(request: HttpServletRequest, session: HttpSession): String {
        var adultNum = Integer.parseInt(request.getParameter("adult"))
        var childrenNum = Integer.parseInt(request.getParameter("children"))
        var infantNum = Integer.parseInt(request.getParameter("infant"))
        var passengerList = arrayListOf<BookCheckRequestDto?>()

        var instance: BookCheckRequestDto? = null

        //adult의 수만큼 passengerList에 값 add
        var adultGender = request.getParameterValues("adultGender")
        var adultFirstName = request.getParameterValues("adultFirstName")
        var adultLastName = request.getParameterValues("adultLastName")
        var adultYear = request.getParameterValues("adultYear")
        var adultMonth = request.getParameterValues("adultMonth")
        var adultDay = request.getParameterValues("adultDay")
        var adultAirline = request.getParameterValues("adultAirLine")

        for (i: Int in 0 until adultNum) {
            instance?.Gender = adultGender[i]
            instance?.FirstName = adultFirstName[i]
            instance?.LastName = adultLastName[i]
            instance?.BirthDate = adultYear[i] + " - " + adultMonth[i] + " - " + adultDay[i]
            instance?.AirLine = adultAirline[i]
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
            instance?.Gender = childrenGender[i]
            instance?.FirstName = childrenFirstName[i]
            instance?.LastName = childrenLastName[i]
            instance?.BirthDate = childrenYear[i] + " - " + childrenMonth[i] + " - " + childrenDay[i]
            instance?.AirLine = childrenAirline[i]

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
            instance?.Gender = infantGender[i]
            instance?.FirstName = infantFirstName[i]
            instance?.LastName = infantLastName[i]
            instance?.BirthDate = infantYear[i] + " - " + infantMonth[i] + " - " + infantDay[i]
            instance?.AirLine = infantAirline[i]

            passengerList.add(instance)
        }

        //session에서 passengerInfo를 passengerList로 설정, arrayList임
        session.setAttribute("passengerInfo", passengerList)


        //가격 구하는 부분
        var totalNum = adultNum + childrenNum + infantNum
        var grade = session.getAttribute("grade") as String
        var flightNum = session.getAttribute("flightNum") as Int
        var price = flightService.getCharge(grade, flightNum)

        //session에 저장되어있는 grade, flightNum을 이용하여 totalPrice 구하기

        session.setAttribute("totalCharge", price * totalNum)

        return "payment"
    }

    @RequestMapping("/payment_proceed")
    fun payment_process(session: HttpSession, model: Model): String {
        var totalCharge = session.getAttribute("totalCharge") as Int
        var userId = session.getAttribute("userId") as String


        var result = userService.setBalance(totalCharge, userId)

        //성공 시 사람 수만큼 DB에 추가
        if (result.equals("success")) {
            var passengerList = session.getAttribute("PassengerList") as ArrayList<BookCheckRequestDto>
            var flightNum = session.getAttribute("flightNum") as Int
            var seatClass = session.getAttribute("seatClass") as String
            for (i: Int in 0 until passengerList.size) {
                var result = bookService.addToDB(passengerList.get(i), userId, flightNum, seatClass)

                //등록 실패 시 일단 돌아감. 근데 차감된 금액하고 중간에 끊어진 저장을 어떻게 해야할지 정하긴 해야 할듯
                if (result.equals("failed")) {
                    model.addAttribute("result", result)
                    return "payment"
                }
            }


            //사용했던 session의 attribute 초기화
            session.setAttribute("flightNum", null)
            session.setAttribute("grade", null)
            session.setAttribute("PassengerList", null)

            //예약 확인 페이지로
            return "bookCheck"
        } else {
            //값 업데이트 실패 시 실패했다는 값 model에 넣어서 결제 페이지로.
            model.addAttribute("result", "failed")
            return "payment"
        }

    }
}

