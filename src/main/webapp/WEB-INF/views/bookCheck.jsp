<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.onair.domain.bookCheck.BookCheck" %>
<%@ page import="java.util.List" %>
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
    <script>
        function selectAll(selectAll)  {
            const checkboxes = document.getElementsByName('bookId');

            checkboxes.forEach((checkbox) => {
                checkbox.checked = selectAll.checked;
            })
        }
    </script>
</head>
<body>
<div class="header">
    <nav>
        <!--<img src="images/logo.png" class="logo">-->
        <a href="index" style="color:#fff; font-size: 30px;"><b>OnAir</b></a>
        <c:set var="result" value="${result}" />
        <%
            String user_id = null;
            String name = null;
            String result = (String)pageContext.getAttribute("result");

            if (result != null && result.equals("success")) {
        %>
            <script type = "text/javascript">
                alert("예약이 완료되었습니다");
            </script>
        <%
                pageContext.removeAttribute("result");
            } //end if

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
            <h1 style="text-align: center;">예약 내역</h1><br><hr><br><br><br>
            <form method="post" action="bookCheck">
                <div style="display: flex;">
                    <button type="submit" class="button-1" style=" background-color: #bcbcbc; border-radius: 3px;">예약 취소/환불</button>
                </div><br><br><br>

                <table style="border-left:2px solid #fff; border-top:2px #fff;">
                    <tbody>
                    <tr>
                        <th style="text-align:center; width:50px;"><input type="checkbox" name="bookId" value="selectall" onclick="selectAll(this)"></th>
                        <th>성명</th>
                        <th>항공사</th>
                        <th>출발공항</th>
                        <th>도착공항</th>
                        <th>출발날짜</th>
                        <th>좌석등급</th>
                    </tr>
                    <c:set var="bookList" value="${bookList}" />
                    <%
                        List<BookCheck> res = (List<BookCheck>) pageContext.getAttribute("bookList");
                        System.out.println(res);
                        if(res!=null){
                            for (BookCheck f : res) {
                    %>
                    <tr>
                        <td style="text-align:center; width:50px;"><input type="checkbox" name="bookId" value="<%=f.getId()%>"></td>
                        <td><%=f.getNM()%></td>
                        <td><%=f.getFN()%></td>
                        <td><%=f.getDA()%></td>
                        <td><%=f.getAA()%></td>
                        <td><%=f.getDD()%></td>
                        <td><%=f.getSC()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table><br><br><br>

            </form>
        </div>
    </div>
</div>
<script src="js/number.js"></script>
</body>
</html>