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
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <style>
        nav .dropdown:hover .dropdown-content {
            background-color: grey;
            display: block;
            z-index : 10;
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
            <h1 style="text-align: center;">마이페이지</h1><br><hr><br><br><br>
            <div class="tabs">
                <input type="radio" class="tabs__radio" name="tabs-example" id="tab1" checked>
                <label for="tab1" class="tabs__label">회원정보 수정</label>
                <div class="tabs__content">
                    <br><br>
                    <form action="" method="post">
                        <div class="user-details">
                            <div class="input-box">
                                <span class="details">아이디</span>
                                <input type="text" value="<%=session.getAttribute("user_id")%>" required>
                            </div>
                            <div class="input-box">
                                <span class="details">비밀번호</span>
                                <input type="password" value="<%=session.getAttribute("password")%>" required>
                            </div>
                            <div class="input-box">
                                <span class="details">이름</span>
                                <input type="text" value="<%=session.getAttribute("name")%>" required>
                            </div>
                            <div class="input-box">
                                <span class="details">나이</span>
                                <input type="text" value="<%=session.getAttribute("age")%>" required>
                            </div>
                            <div class="input-box">
                                <span class="details">전화번호</span>
                                <input type="text" value="<%=session.getAttribute("phone_num")%>" required>
                            </div>
                            <div class="input-box">
                                <span class="details">email</span>
                                <input type="text" value="<%=session.getAttribute("email")%>" required>
                            </div>
                        </div><br><br>

                        <input type="submit" value="수정" style="position:absolute; left:40%;width:20%; border-radius: 5px;">
                    </form>
                </div>
                <input type="radio" class="tabs__radio" name="tabs-example" id="tab2">
                <label for="tab2" class="tabs__label">포인트 충전</label>
                <div class="tabs__content">
                    <br><br>
                    <form action="#" method="post">
                        <div class="">
                            <div style="display: flex; font-size: 20px">
                                <img src="images/money.png" width="30px">&nbsp;&nbsp;
                                <label for="point">충전할 금액</label>
                            </div><br>
                            <div class="seat" id="point" style="display: flex; font-size: 20px">
                                <input type="radio" class="radio-input" name="charge" id="10000" value="10000" autocomplete="off" required>
                                <label class="radio-label" for="10000" style="height:60px; padding:15px; font-size: 20px;">+10000</label>
                                <input type="radio" class="radio-input" name="charge" id="50000" value="50000" autocomplete="off" required>
                                <label class="radio-label" for="50000" style="height:60px; padding:15px; font-size: 20px;">+50000</label>
                                <input type="radio" class="radio-input" name="charge" id="100000" value="100000" autocomplete="off" required>
                                <label class="radio-label" for="100000" style="height:60px; padding:15px; font-size: 20px;">+100000</label>
                                <input type="radio" class="radio-input" name="charge" id="200000" value="200000" autocomplete="off" required>
                                <label class="radio-label" for="200000" style="height:60px; padding:15px; font-size: 20px;">+200000</label>
                                <input type="radio" class="radio-input" name="charge" id="300000" value="300000" autocomplete="off" required>
                                <label class="radio-label" for="300000" style="height:60px; padding:15px; font-size: 20px;">+300000</label>
                            </div><br><br>
                            <script>
                                $(document).ready(function() {
                                    $("input[name='charge']:radio").change(function () {
                                        //라디오 버튼 값을 가져온다.
                                        var charge = this.value;
                                        var point = <%=session.getAttribute("point")%>;
                                        var result = parseInt(charge) + parseInt(point);
                                        $("span[id='afterCharge']").html(result);
                                    });
                                });
                            </script>
                            <div style="display: flex; font-size: 20px">
                                <div style="width: 50%">
                                    <label>충전 전</label>
                                    <span id="beforeCharge"><%=session.getAttribute("point")%></span>원
                                </div>
                                <div style="width: 50%">
                                    <label>충전 후</label>
                                    <span id="afterCharge"></span>원
                                </div>
                            </div>
                        </div><br><br><br><br>
                        <input type="submit" value="충전" style="position:absolute; left:40%;width:20%; border-radius: 5px;">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>