package com.example.onair.controller

import com.example.onair.dto.PassengerDto
import com.example.onair.service.BookService
import com.example.onair.service.FlightService
import com.example.onair.service.UserService
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

class BookController (private val bookService: BookService, private val flightService: FlightService, private val userService: UserService) {
    @RequestMapping("/payment")
    fun payment(request : HttpServletRequest, session : HttpSession) : String {
        var adultNum = Integer.parseInt(request.getParameter("adult"))
        var childrenNum = Integer.parseInt(request.getParameter("children"))
        var infantNum = Integer.parseInt(request.getParameter("infant"))
        var passengerList = arrayListOf<PassengerDto?>()

        var instance : PassengerDto? = null

        //adult의 수만큼 passengerList에 값 add
        var adultGender = request.getParameterValues("adultGender")
        var adultFirstName = request.getParameterValues("adultFirstName")
        var adultLastName = request.getParameterValues("adultLastName")
        var adultYear = request.getParameterValues("adultYear")
        var adultMonth = request.getParameterValues("adultMonth")
        var adultDay = request.getParameterValues("adultDay")
        var adultAirline = request.getParameterValues("adultAirLine")

        for (i : Int in 0 until adultNum) {
            instance?.gender = adultGender[i]
            instance?.firstName = adultFirstName[i]
            instance?.lastName = adultLastName[i]
            instance?.year = Integer.parseInt(adultYear[i])
            instance?.month = Integer.parseInt(adultMonth[i])
            instance?.day = Integer.parseInt(adultDay[i])
            instance?.airline = adultAirline[i]

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

        for (i : Int in 0 until childrenNum) {
            instance?.gender = childrenGender[i]
            instance?.firstName = childrenFirstName[i]
            instance?.lastName = childrenLastName[i]
            instance?.year = Integer.parseInt(childrenYear[i])
            instance?.month = Integer.parseInt(childrenMonth[i])
            instance?.day = Integer.parseInt(childrenDay[i])
            instance?.airline =childrenAirline[i]

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

        for (i : Int in 0 until infantNum) {
            instance?.gender = infantGender[i]
            instance?.firstName = infantFirstName[i]
            instance?.lastName = infantLastName[i]
            instance?.year = Integer.parseInt(infantYear[i])
            instance?.month = Integer.parseInt(infantMonth[i])
            instance?.day = Integer.parseInt(infantDay[i])
            instance?.airline =infantAirline[i]

            passengerList.add(instance)
        }

        //session에서 passengerInfo를 passengerList로 설정, arrayList임
        session.setAttribute("passengerInfo", passengerList)


        //가격 구하는 부분
        var totalNum = adultNum + childrenNum + infantNum
        var grade = session.getAttribute("grade")
        var flightNum = session.getAttribute("flightNum")
        var price = 0

        //session에 저장되어있는 grade, flightNum을 이용하여 totalPrice 구하기
        if (grade.equals("economy"))
            price = flightService.getCharge("EconomyCharge", flightNum as Int) as Int
        else if (grade.equals("business"))
            price = flightService.getCharge("BusinessCharge", flightNum as Int) as Int
        else if (grade.equals("first"))
            price = flightService.getCharge("FirstCharge", flightNum as Int) as Int

        session.setAttribute("totalCharge", price * totalNum)

        return "payment"
    }

    @RequestMapping("/payment_proceed")
    fun payment_process(session: HttpSession, model : Model) : String {
        var totalCharge = session.getAttribute("totalCharge") as Int
        var userId = session.getAttribute("userId") as String


        var result = userService.setBalance(totalCharge, userId)

        //성공 시 사람 수만큼 DB에 추가
        if (result.equals("success")) {
            var passengerList = session.getAttribute("PassengerList") as ArrayList<PassengerDto>
            var flightNum = session.getAttribute("flightNum") as Int
            var seatClass = session.getAttribute("seatClass") as String
            for (i : Int in 0 until passengerList.size) {
                bookService.addToDB(passengerList.get(i), userId, flightNum, seatClass)
            }


            //사용했던 session의 attribute 초기화
            session.setAttribute("flightNum", null)
            session.setAttribute("grade", null)
            session.setAttribute("PassengerList", null)

            //예약 확인 페이지로
            return "bookCheck"
        }

        else {
            //값 업데이트 실패 시 실패했다는 값 model에 넣어서 결제 페이지로.
            model.addAttribute("result", "failed")
            return "payment"
        }

    }

}
