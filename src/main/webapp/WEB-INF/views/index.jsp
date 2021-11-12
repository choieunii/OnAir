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
</head>
<body>
<div class="header">
    <nav>
        <!--<img src="images/logo.png" class="logo">-->
        <a href="index" style="color:#fff; font-size: 30px;"><b>OnAir</b></a>
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
    </nav>
    <div class="social-media">
        <a href="#">FACEBOOK</a>
        <a href="#">TWITTER</a>
        <a href="#">INSTAGRAM</a>
    </div>
    <div class="line"></div>
    <div class="row">
        <div>
            <div class="content">
                <span><img src="images/location.png">한국</span>
                <h1>OnAir</h1>
                <p>OnAir에 오신 여려분들을 환영합니다.</p>
            </div>
        </div>
    </div>
    <div class="arrow">
        <img src="images/back.png">
        <img src="images/next.png">
    </div>
</div>
</body>
</html>