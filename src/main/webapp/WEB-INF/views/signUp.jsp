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
            <li><a href="#">예약안내</a><li>
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
            <li><a href="#">예약안내</a><li>
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
    <div class="signUp">
        <h1>Sign Up</h1>
        <c:set var="response" value="${response}" />
        <%
            String res = (String)pageContext.getAttribute("response") ;
        %>
        <%
            if (res!=null) {
        %>
        <script type="text/javascript">
            alert("<%=res%>");
        </script>
        <%
            } else {}
        %>
        <form method="post" action="signUp" onsubmit="return checkValue()">
            <div class="txt_field">
                <input type="text" id="name" name="name" required>
                <span></span>
                <label>이름</label>
            </div>
            <div class="txt_field">
                <input type="text" id="userId" name="userId" required>
                <span></span>
                <label>아이디</label>
            </div>
            <div class="txt_field">
                <input type="password" id="password" name="password" onchange="checkPassword()"  required>
                <span></span>
                <label>비밀번호</label>
            </div>
            <div class="txt_field">
                <input type="password" id="checkPassword" name="checkPassword" onchange="checkPassword()"  required>
                <span></span>
                <label>비밀번호 확인</label>
                <span id="check"></span>
            </div>
            <div class="txt_field">
                <input type="text" id="age" name="age" required>
                <span></span>
                <label>나이</label>
            </div>
            <div class="txt_field">
                <input type="text" id="phoneNum" name="phoneNum" required>
                <span></span>
                <label>전화번호</label>
            </div>
            <div class="txt_field">
                <input type="text" id="email" name="email" required>
                <span></span>
                <label>이메일</label>
            </div>
            <input type="submit" value="회원가입"><br>
        </form>
    </div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br>
</div>
<script src="js/checkSignUp.js"></script>
</body>
</html>