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
    <link rel="icon" href="logo.png">
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
        <div class="sub-frame">
            <h1 style="text-align: center;">운항 정보</h1><br><hr><br>
            <form method="post" action="book">
                <div style="display: flex;">
                    <h2>출발지</h2>
                    <button type="submit" class="button-1" style=" background-color: #bcbcbc;  width: 100px; height: 40px; margin-bottom:15px; border-radius: 3px;">검색</button>
                </div><br>
                <div class="seat" id="departmentInfo">
                    <input type="radio" class="radio-input" name="department" id="incheonDpt" value="1" autocomplete="off" required>
                    <label class="radio-label" for="incheonDpt" style="height:60px; padding:15px; font-size: 20px;">인천</label>
                    <input type="radio" class="radio-input" name="department" id="busanDpt" value="2" autocomplete="off" required>
                    <label class="radio-label" for="busanDpt" style="height:60px; padding:15px; font-size: 20px;">부산</label>
                    <input type="radio" class="radio-input" name="department" id="jejuDpt" value="3" autocomplete="off" required>
                    <label class="radio-label" for="jejuDpt" style="height:60px; padding:15px; font-size: 20px;">제주</label>
                    <input type="radio" class="radio-input" name="department" id="gimpoDpt" value="4" autocomplete="off" required>
                    <label class="radio-label" for="gimpoDpt" style="height:60px; padding:15px; font-size: 20px;">김포</label>
                    <input type="radio" class="radio-input" name="department" id="gimhaeDpt" value="5" autocomplete="off" required>
                    <label class="radio-label" for="gimhaeDpt" style="height:60px; padding:15px; font-size: 20px;">김해</label>
                </div><br><br>
                <h2>도착지</h2><br>
                <div class="seat" id="arriveInfo">
                    <input type="radio" class="radio-input" name="arrive" id="incheonArr" value="1" autocomplete="off" required>
                    <label class="radio-label" for="incheonArr" style="height:60px; padding:15px; font-size: 20px;">서울</label>
                    <input type="radio" class="radio-input" name="arrive" id="busanArr" value="2" autocomplete="off" required>
                    <label class="radio-label" for="busanArr" style="height:60px; padding:15px; font-size: 20px;">부산</label>
                    <input type="radio" class="radio-input" name="arrive" id="jejuArr" value="3" autocomplete="off" required>
                    <label class="radio-label" for="jejuArr" style="height:60px; padding:15px; font-size: 20px;">제주</label>
                    <input type="radio" class="radio-input" name="arrive" id="dokyoArr" value="4" autocomplete="off" required>
                    <label class="radio-label" for="dokyoArr" style="height:60px; padding:15px; font-size: 20px;">도쿄</label>
                    <input type="radio" class="radio-input" name="arrive" id="beijingArr" value="5" autocomplete="off" required>
                    <label class="radio-label" for="beijingArr" style="height:60px; padding:15px; font-size: 20px;">베이징</label>
                    <input type="radio" class="radio-input" name="arrive" id="danangArr" value="6" autocomplete="off" required>
                    <label class="radio-label" for="danangArr" style="height:60px; padding:15px; font-size: 20px;">다낭</label>
                    <input type="radio" class="radio-input" name="arrive" id="kyotoArr" value="7" autocomplete="off" required>
                    <label class="radio-label" for="kyotoArr" style="height:60px; padding:15px; font-size: 20px;">쿄토</label>
                </div>
            </form>
            <br><br><br>
                <table style="border-left:2px solid #fff; border-top:2px #fff;">
                    <tbody>
                    <tr>
                        <th>항공편명</th>
                        <th>항공사명</th>
                        <th>출발시간</th>
                        <th>도착시간</th>
                        <th>출발공항</th>
                        <th>도착공항</th>
                    </tr>
                    <%

                    %>
                    <tr>
                        <td>46513</td>
                        <td>아시아나항공</td>
                        <td>08:00</td>
                        <td>10:00</td>
                        <td>인천공항</td>
                        <td>제주공항</td>
                    </tr>
                    <%

                    %>
                    </tbody>
                </table>
            <br><br><br><br><br><br><br><br><br><br><br><br><br>
            <br><br><br><br><br><br><br><br><br><br><br>
            <button class="button-1" onclick="location.href='book'">예약하기</button>
        </div>
    </div>
</div>
</body>
</html>