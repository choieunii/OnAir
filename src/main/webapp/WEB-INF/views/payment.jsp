<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" charset="width=device-width, initial-scale=1.0">
    <title>OnAir</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" href="css/logo.png">
    <style>
        label{
            font-size: 20px;
        }
        .checkPoint{
            width:120px;
            height:40px;
            top:50%;
            right:20%;
            font-size: 15px;
            border-radius: 5px;
            background-color: #535F68;
            color:#fff;
            box-shadow: none;
            border:none;
        }
        nav .dropdown:hover .dropdown-content {
            background-color: grey;
            display: block;
            z-index: 10;
        }
    </style>
</head>
<body>
<div class="header">
    <nav>
        <!--<img src="images/logo.png" class="logo">-->
        <a href="index" style="color:#fff; font-size: 30px;"><b>OnAir</b></a>
        <%
            String user_id = null;
            String name = null;
            if(session.getAttribute("user_id") != null) {
                user_id = (String) session.getAttribute("user_id");
                name = (String) session.getAttribute("name");
            }
            if(user_id == null) {
        %>
        <ul>
            <li><a href="guide">예약안내</a><li>
            <li><a href="flightInfo">운행정보</a><li>
            <li><a href="bookCheck">예약조회</a><li>
            <li><a href="login">로그인</a><li>
        </ul>
        <div class="dropdown">
            <img src="images/menu.png" class="menu-icon">
            <div class="dropdown-content">
                <a href="#">공지사항</a>
                <a href="#">이벤트</a>
                <a href="#">신고하기</a>
            </div>
        </div>
        <%
        } else {
        %>
        <ul>
            <li><a href="guide">예약안내</a><li>
            <li><a href="flightInfo">운행정보</a><li>
            <li><a href="bookCheck">예약조회</a><li>
            <li><a href="logout">로그아웃</a><li>
        </ul>
        <div class="dropdown">
            <img src="images/menu.png" class="menu-icon">
            <div class="dropdown-content">
                <p><%=name%>님,</p>
                <p style="padding-top: 0px">환영합니다!</p><hr>
                <a href="myPage">마이페이지</a>
                <a href="#">공지사항</a>
                <a href="#">이벤트</a>
                <a href="#">신고하기</a>
            </div>
        </div>
        <%
            }
        %>
    </nav>
    <div class="frame">
        <div class="sub-frame">
            <h1 style="text-align: center;">결제 정보</h1><br><hr><br><br><br>
            <c:set var="result" value="${result}" />
            <!-- model의 result가 "failed"면 결제에 실패했다는 메세지 출력 -->
            <%
                String result = (String)pageContext.getAttribute("result");
                if (result != null && result == "failed") {
            %>
                <script type = "text/javascript">
                    alert("결제 도중 에러가 발생하였습니다");
                </script>
            <%
                } else if (result != null && result == "noPassenger") {
            %>
                <script type = "text/javascript">
                    alert("탑승자 정보가 없습니다");
                </script>
            <%
                pageContext.removeAttribute("result");
                session.removeAttribute("result");
                }
            %>
            <c:remove var="result"/>
            <form method="post" action="payment_proceed">
                <table>
                    <tbody>
                    <%
                        int balance = (int)session.getAttribute("point");
                        int totalCharge = (int)session.getAttribute("totalCharge");
                        int afterPayment = balance - totalCharge;
                    %>
                    <tr>
                        <th style="width:35%;">결제 금액</th>
                        <td>
                            <label id="paymentAmount"><%=totalCharge%>원</label>
                        </td>
                    </tr>
                    <tr>
                        <th>보유 금액</th>
                        <td>
                            <label id="checkPoint"><%=balance%>원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="checkPoint"> <a href = "myPage"> 포인트 충전 </a></button>
                        </td>
                    </tr>
                    <tr>
                        <th>결제 후 잔액</th>
                        <td>
                            <label id="balance"><%=afterPayment%>원</label>
                        </td>
                    </tr>
                    </tbody>
                </table><br><br><br><br><br>
                <%
                    if (afterPayment < 0) {
                %>
                    <center>
                        <label id="paymentAmount">포인트를 먼저 충전해주세요</label>
                    </center>
                <%
                    } else {
                %>
                <input type="submit" value="결제" style="position:absolute; left:20%;width:60%; border-radius: 5px;">
                <%
                    }
                %>

            </form>
        </div>
    </div>
</div>
</body>
</html>