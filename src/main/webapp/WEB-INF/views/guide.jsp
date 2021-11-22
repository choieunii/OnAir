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
            background-color: rgb(0, 0, 0, 0.6);
            display: block;
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
    <div class="frame">
        <div class="sub-frame">
            <h4 class="fl">항공권 변경 및 환불</h4> </div>
        <p>고객의 요청에 따라 항공권에 기재되어 있는 여정, 운송 항공사, 운임의 등급, 유효기간 등의 변경을 하실 수 있습니다.</p>
        <p>판매운임과 구입처에 따라 정확한 차액 및 취소 규정이 다를 수 있으므로, 해당 구입처나 항공사로 문의하여 주시기 바랍니다.</p><!-- 활성화되었을 때 txt_line class 추가 -->
        <div class="tab_area pc_type03 mar_to20 txt_line">
            <div class="tab_indicator">
                <ul>
                    <li class="on"><a href="#none">항공권 변경</a></li>
                    <li><a href="#none">항공권 환불/취소</a></li>
                    <li><a href="#none">환불금 수령 규정 안내</a></li>
                    <li><a href="#none">예약부도 위약금</a></li>
                    <li><a href="#none">항공권 발권수수료</a></li>
                </ul> </div>
            <div class="tab_container"> <!-- 항공권 변경 탭 -->
                <div class="tab_cate on">
                    <h4 class="hidden">항공권 변경</h4>
                    <div class="line_row_wrap"> <dl class="line_row"> <dt> <span class="tit">국제선 항공권 변경</span> </dt> <dd>
                        <ul class="list_type2">
                            <li>항공권의 변경은 구매 항공권의 운임 규정 및 구입처에 따라 적용 규정이 다를 수 있으므로 해당 구입처로 문의하여 주시기 바랍니다.</li>
                            <li>해외발 항공권의 경우 판매 지역과의 운임계산 내역 확인이 필요하므로 2~3일의 소요기간(시차 감안)이 필요합니다.</li>
                            <li>항공권의 변경이 가능한 경우에는 운임 규정에 따라 운임 차액 및 세금차액, 수수료가 발생될 수 있습니다. 정확한 안내를 위하여 해당 구입처로 문의하여 주시기 바랍니다.</li>
                        </ul> </dd> </dl> <dl class="line_row"> <dt> <span class="tit">국내선 항공권 변경</span> </dt> <dd>
                        <ul class="list_type2">
                            <li>국내선 항공운임은 탑승일자, 시간, 구간에 따라 상이하므로 새로운 여정과 이전 여정의 운임차액을 지불하실 수 있습니다. 또한 환불처리 후 새로운 항공권을 발행가능 합니다.</li>
                            <li>국내선 항공권의 유효기간은 최초 구매일로부터 1년이며, 추가 연장은 불가하므로 기간 내에 사용하셔야 합니다.</li>
                            <li>항공권의 종류에 따라 유효기간이 1년 미만인 경우 등 변경에 관련된 제한사항이 있을 수 있으므로 자세한 내용은 구입처 또는 영업소로 문의하여 주시기 바랍니다. </li>
                        </ul> </dd> </dl> </div>
                    <div class="title_wrap_type3">
                        <h4 class="fl">좌석승급</h4> </div>
                    <p class="mar_b20">구입하신 항공권의 좌석등급에서 상위의 좌석등급으로 변경이 가능합니다.<br> 상위의 좌석등급으로 변경하실 때에는 마일리지 공제, 상위 좌석등급과 운임 차액 재계산을 통하여 좌석승급을 해드립니다.</p>
                    <div class="line_row_wrap"> <dl class="line_row"> <dt> <span class="tit">마일리지 공제로 <br> 좌석승급 하기</span> <button type="button" class="btn_XS white mar_to10" data-cms-url="CM201802220000728466">나의 마일리지 확인</button> </dt> <dd>
                        <ul class="list_type2">
                            <li>고객께서 보유하신 아시아나클럽 적립 마일리지를 공제하여 상위등급의 좌석으로 승급하여 드립니다.</li>
                            <li>단, 한번에 두 단계 차상급으로의 좌석승급은 가능하지 않습니다. 또한 아래의 경우에는 차상급 좌석 승급이 불가하오니 이용에 유의하시기 바랍니다. <dl> <dt>[좌석승급 불가 항공권]</dt> <dd>
                                <ul class="list_type2">
                                    <li>항공권 fare basis란에 50% 초과하여 할인율이 표기된 항공권</li>
                                    <li>차상급 좌석승급 불가 조건으로 판매된 특별 할인 항공권</li>
                                    <li>마일리지 항공권</li>
                                    <li>무료 항공권 또는 IT 운임 항공권</li>
                                    <li>단체요금 항공권(예약 클래스 G)</li>
                                    <li>스타얼라이언스 및 그 외의 항공사에서 판매된 항공권 (탑승 구간이 U,Q,K,S,V,L,W,T,G 클래스)</li>
                                </ul> </dd> </dl> </li>
                        </ul> </dd> </dl> <dl class="line_row"> <dt> <span class="tit">운임 차액 지불로 <br> 좌석승급 하기</span> </dt> <dd>
                        <ul class="list_type2">
                            <li>항공권상의 기재된 좌석등급에 해당하는 운임과 상위 좌석등급에 해당하는 운임과의 차액을 재계산한 후 그 차액과 재발행 수수료를 지불하시고 좌석승급을 할 수 있습니다. 단 변경 제한 조건이 있는 항공권의 경우는 제외됩니다.</li>
                        </ul> </dd> </dl> </div> </div> <!-- //항공권 변경 탭 --> <!-- 항공권 환불/취소 탭 -->
                <div class="tab_cate">
                    <h4 class="hidden">항공권 환불/취소</h4>
                    <div class="line_row_wrap"> <dl class="line_row"> <dt> <span class="tit">항공권 환불/취소 안내</span> </dt> <dd>
                        <ul class="list_type2">
                            <li>항공권 환불/취소는 구입처에 따라 규정이 다를 수 있으므로 구입하신 구입처로 문의하시기 바랍니다.</li>
                            <li>국내선 환불 신청 기한은 항공권 유효기간 만료일 이내입니다. (2016년 8월 1일 발권분부터 적용)</li>
                            <li>국제선 환불 신청 기한은 항공권 유효기간 만료일로부터 30일 이내입니다. (2016년 5월 20일 발권분부터 적용)</li>
                            <li>환불 신청 시, 항공권 명의인이 신청해야 하며 대리인이 신청할 경우에는 추가 서류가 발생 할 수 있으니 구입하신 발행처로 문의하시기 바랍니다.</li>
                            <li>한국발 국제선 전체미사용 항공권의 경우 환불 접수시점에 따라 환불 위약금이 차등 부과됩니다.</li>
                        </ul> <button type="button" class="btn_S mar_to15 white" onclick="openLayerPopup('refundpopup')">한국출발 항공권 환불 접수 시 시점별 환불 위약금 안내</button> </dd> </dl> </div> </div> <!-- //항공권 환불/취소 탭 --> <!-- 환불금 수령 규정 안내 탭 -->
                <div class="tab_cate">
                    <h4 class="hidden">환불금 수령 규정 안내</h4>
                    <div class="line_row_wrap"> <dl class="line_row"> <dt> <span class="tit">환불금 수령 규정 안내</span> </dt> <dd>
                        <ul class="list_type2">
                            <li>현금 구매 항공권은 항공권 명의인의 계좌로 입금 받으실 수 있습니다.</li>
                            <li>신용카드 구매 항공권은 현금으로 수령 할 수 없으며, 해당 카드사로 환불 정보가 이관되어 카드사 규정에 의해 지급됩니다. </li>
                            <li>신용카드로 구매한 항공권의 환불금은 고객의 결제일/카드사의 처리주기 등에 따라 수령시기가 각각 다르므로 정확한 환불금의 처리 절차는 환불금 승인 이후 영업일 기준 2~5일 경과 후, 해당 카드사로 문의하시기 바랍니다.</li>
                        </ul> </dd> </dl> </div> </div> <!-- //환불금 수령 규정 안내 탭 --> <!-- 예약부도 위약금 탭 -->
            </div>
    </div>
</div>
<script src="js/number.js"></script>
</body>
</html>