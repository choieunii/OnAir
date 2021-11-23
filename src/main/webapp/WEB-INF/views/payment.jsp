<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
                <a href="#">마이페이지</a>
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

            <!-- model의 result가 "failed"면 결제에 실패했다는 메세지 출력 -->
            <form method="post" action="payment_proceed">
                <table>
                    <tbody>
                    <tr>
                        <th style="width:35%;">결제 금액</th>
                        <td>
                            <label id="paymentAmount">1,000,000won</label>
                        </td>
                    </tr>
                    <tr>
                        <th>보유 금액</th>
                        <td>
                            <label id="checkPoint">1,200,000won</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="checkPoint">포인트 충전</button>
                        </td>
                    </tr>
                    <tr>
                        <th>결제 후 잔액</th>
                        <td>
                            <label id="balance">200,000won</label>
                        </td>
                    </tr>
                    </tbody>
                </table><br><br><br><br><br>
                <input type="submit" value="결제" style="position:absolute; left:20%;width:60%; border-radius: 5px;">
            </form>
        </div>
    </div>
</div>
</body>
</html>