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
        <!-- 오류났는지 탐색하는 부분 -->
        <c:set var="result" value="${result}" />
        <%
            String res = (String)pageContext.getAttribute("result");
            if (res != null && res.equals("failed")) {
        %>
            <script type = "text/javascript">
                alert("노선 정보를 정확하게 입력해주세요");
            </script>
        <%
            } else if (res != null && res.equals("duplicated")) {
        %>
            <script type = "text/javascript">
                 alert("노선이 두 개 이상 탐색됩니다");
            </script>
        <%
            }
            pageContext.removeAttribute("result");
        %>
        <c:remove var = "result"/>

        <div class="sub-frame">
            <form method="post" action="book2">
                <h2>여정/날짜 선택</h2>
                <br>
                <div class="journey" id="journey">
                    <input type="radio" class="radio-input" name="journey" id="roun
                    d-trip" autocomplete="off" checked required>
                    <label class="radio-label" for="round-trip">왕복</label>
                    <input type="radio" class="radio-input" name="journey" id="one-way" autocomplete="off" required>
                    <label class="radio-label" for="one-way">편도</label>
                </div>
                <div style="display: flex;">
                    <div class="txt_field" style="width:33%">
                        <input type="text" name="departmentAirport" id="departmentAirport" style="font-size: 22px;" required>
                        <span></span>
                        <label style="font-size: 20px;">출발지</label>
                    </div>
                    <div class="txt_field"style="width:33%">
                        <input type="text" name="arriveAirport" id="arriveAirport"  style="font-size: 22px;" required>
                        <span></span>
                        <label style="font-size: 20px;">도착지</label>
                    </div>
                    <div class="txt_field" style="width:33%">
                        <input type="text" name="departmentDate" id="departmentDate" onfocus="(this.type='date')" onblur="(this.type='text')" style="font-size: 22px;" required>
                        <span></span>
                        <label style="font-size: 20px;">탑승일</label>
                    </div>
                </div><br><br>
                <h2>탑승 인원 선택</h2><br>&nbsp;
                <div class="number-label" style="width:100%; display: flex;">
                    <div style="font-size: 22px; width:34%">성인 <font size="4px">(만 12세 이상)</font></div>
                    <div style="font-size: 22px; width:34%">유아 <font size="4px">(만 2세 이상 12세 미만)</font></div>
                    <div style="font-size: 22px; width:31%">영아 <font size="4px">(만 2세 미만)</font></div>
                </div>
                <div class="number-box">
                    <input type="button" id="sub-adult" value="-">
                    <input type="text" id="adult" name="adult" max="10" readonly="" value="0">
                    <input type="button" id="add-adult" value="+">
                </div>&nbsp;&nbsp;&nbsp;
                <div class="number-box">
                    <input type="button" id="sub-children" value="-">
                    <input type="text" id="children" name="children" max="10" readonly="" value="0">
                    <input type="button" id="add-children" value="+">
                </div>&nbsp;&nbsp;&nbsp;
                <div class="number-box">
                    <input type="button" id="sub-infant" value="-">
                    <input type="text" id="infant" name="infant" max="10" readonly="" value="0">
                    <input type="button" id="add-infant" value="+">
                </div>


                <script type="text/javascript">

                </script>
                <br><br><br><br><br><br>

                <h2>좌석 등급 선택</h2><br>
                <div class="seat" id="seat">
                    <input type="radio" class="radio-input" name="grade" id = "economy" value="economy" autocomplete="off" checked required>
                    <label class="radio-label" for="economy" style="height:60px; padding:15px; font-size: 20px;">이코노미</label>
                    <input type="radio" class="radio-input" name="grade" id = "business" value="business" autocomplete="off" required>
                    <label class="radio-label" for="business" style="height:60px; padding:15px; font-size: 20px;">비즈니스</label>
                    <input type="radio" class="radio-input" name="grade" id = "first" value="first" autocomplete="off" required>
                    <label class="radio-label" for="first" style="height:60px; padding:15px; font-size: 20px;">퍼스트</label>
                </div><br><br><br><br>

                <button class="button-1" type="submit" class="">다음</button>
            </form>
        </div>
    </div>
</div>
<script src="js/number.js"></script>
</body>
</html>