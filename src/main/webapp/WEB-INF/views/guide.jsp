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
            <h1 style="text-align: center;">예약 안내</h1><br><hr><br><br><br>
            <div class="tabs">
                <input type="radio" class="tabs__radio" name="tabs-example" id="tab1" checked>
                <label for="tab1" class="tabs__label">항공권 변경</label>
                <div class="tabs__content">
                    <br><br><hr><br>
                    <div style="display: flex">
                        <div style="font-weight: bold; font-size: 20px; width:30%; padding-top:40px; text-align: center">
                            국제선 항공권 변경
                        </div>
                        <div style="width:70%">
                            <ul>
                                <li>항공권의 변경은 구매 항공권의 운임 규정 및 구입처에 따라 적용 규정이 다를 수 있으므로 해당 구입처로 문의하여 주시기 바랍니다.</li>
                                <li>해외발 항공권의 경우 판매 지역과의 운임계산 내역 확인이 필요하므로 2~3일의 소요기간(시차 감안)이 필요합니다.</li>
                                <li>항공권의 변경이 가능한 경우에는 운임 규정에 따라 운임 차액 및 세금차액, 수수료가 발생될 수 있습니다. 정확한 안내를 위하여 해당 구입처로 문의하여 주시기 바랍니다.</li>
                            </ul>
                        </div>
                    </div><br><hr><br>
                    <div style="display: flex">
                        <div style="font-weight: bold; font-size: 20px; width:30%; padding-top:40px; text-align: center">
                            국내선 항공권 변경
                        </div>
                        <div style="width:70%">
                            <ul>
                                <li>항공권의 변경은 구매 항공권의 운임 규정 및 구입처에 따라 적용 규정이 다를 수 있으므로 해당 구입처로 문의하여 주시기 바랍니다.</li>
                                <li>해외발 항공권의 경우 판매 지역과의 운임계산 내역 확인이 필요하므로 2~3일의 소요기간(시차 감안)이 필요합니다.</li>
                                <li>항공권의 변경이 가능한 경우에는 운임 규정에 따라 운임 차액 및 세금차액, 수수료가 발생될 수 있습니다. 정확한 안내를 위하여 해당 구입처로 문의하여 주시기 바랍니다.</li>
                            </ul>
                        </div>
                    </div><br><hr><br><br>
                    <h2>좌석승급</h2><br><hr><br>
                    <div style="display: flex">
                        <div style="font-weight: bold; font-size: 20px; width:30%; padding-top:2px; text-align: center">
                            마일리지 공제로 <br> 좌석승급 하기
                        </div>
                        <div style="width:70%">
                            <ul>
                                <li>고객께서 보유하신 아시아나클럽 적립 마일리지를 공제하여 상위등급의 좌석으로 승급하여 드립니다.</li>
                                <li>단, 한번에 두 단계 차상급으로의 좌석승급은 가능하지 않습니다. 또한 아래의 경우에는 차상급 좌석 승급이 불가하오니 이용에 유의하시기 바랍니다. </li>
                            </ul>
                        </div>
                    </div><br><hr><br>
                    <div style="display: flex">
                        <div style="font-weight: bold; font-size: 20px; width:30%; padding-top:2px; text-align: center">
                            운임 차액 지불로 <br> 좌석승급 하기
                        </div>
                        <div style="width:70%">
                            <ul>
                                <li>항공권상의 기재된 좌석등급에 해당하는 운임과 상위 좌석등급에 해당하는 운임과의 차액을 재계산한 후 그 차액과 재발행 수수료를 지불하시고 좌석승급을 할 수 있습니다. 단 변경 제한 조건이 있는 항공권의 경우는 제외됩니다.</li>
                            </ul>
                        </div>
                    </div><br><hr><br><br><br>
                </div>

                <input type="radio" class="tabs__radio" name="tabs-example" id="tab2">
                <label for="tab2" class="tabs__label">항공권 환불/취소</label>
                <div class="tabs__content">
                    <br><br><hr><br>
                    <div style="display: flex">
                        <div style="font-weight: bold; font-size: 20px; width:30%; padding-top:45px; text-align: center">
                            항공권 환불/취소 안내
                        </div>
                        <div style="width:70%">
                            <ul>
                                <li>항공권 환불/취소는 구입처에 따라 규정이 다를 수 있으므로 구입하신 구입처로 문의하시기 바랍니다.</li>
                                <li>국내선 환불 신청 기한은 항공권 유효기간 만료일 이내입니다. (2016년 8월 1일 발권분부터 적용)</li>
                                <li>국제선 환불 신청 기한은 항공권 유효기간 만료일로부터 30일 이내입니다. (2016년 5월 20일 발권분부터 적용)</li>
                                <li>환불 신청 시, 항공권 명의인이 신청해야 하며 대리인이 신청할 경우에는 추가 서류가 발생 할 수 있으니 구입하신 발행처로 문의하시기 바랍니다.</li>
                                <li>한국발 국제선 전체미사용 항공권의 경우 환불 접수시점에 따라 환불 위약금이 차등 부과됩니다.</li>
                            </ul>
                        </div>
                    </div><br><hr><br>
                </div>

                <input type="radio" class="tabs__radio" name="tabs-example" id="tab3">
                <label for="tab3" class="tabs__label">항공권 수령 규정</label>
                <div class="tabs__content">
                    <br><br><hr><br>
                    <div style="display: flex">
                        <div style="font-weight: bold; font-size: 20px; width:30%; padding-top:30px; text-align: center">
                            환불금 수령 규정 안내
                        </div>
                        <div style="width:70%">
                            <ul>
                                <li>현금 구매 항공권은 항공권 명의인의 계좌로 입금 받으실 수 있습니다.</li>
                                <li>신용카드 구매 항공권은 현금으로 수령 할 수 없으며, 해당 카드사로 환불 정보가 이관되어 카드사 규정에 의해 지급됩니다. </li>
                                <li>신용카드로 구매한 항공권의 환불금은 고객의 결제일/카드사의 처리주기 등에 따라 수령시기가 각각 다르므로 정확한 환불금의 처리 절차는 환불금 승인 이후 영업일 기준 2~5일 경과 후, 해당 카드사로 문의하시기 바랍니다.</li>
                            </ul>
                        </div>
                    </div><br><hr><br>
                </div>
            </div>
    </div>
</div>
<script src="js/number.js"></script>
</body>
</html>