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
    <script type="text/javascript">
        function openPayment(){
            window.name = "parentForm";
            window.open("payment",
                "chkForm", "width=600, height=500, resizable = no, scrollbars = no");
        }
    </script>
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
        <div class="dropdown"  style="z-index: 10">
            <img src="images/menu.png" class="menu-icon">
            <div class="dropdown-content">
                <a href="#">공지사항</a>
                <a href="#">이벤트</a>
                <a href="#">신고하기</a>
            </div>
        </div>
    </nav>
    <div class="frame">
        <div class="sub-frame">
            <h1 style="text-align: center;">탑승자 정보</h1><br><hr><br><br><br>

            <%
                int adult = Integer.parseInt(request.getParameter("adult"));
                int children = Integer.parseInt(request.getParameter("children"));
                int infant = Integer.parseInt(request.getParameter("infant"));
                int k=1, i=1, j=1;
                while(k <= adult) {

            %>
            <h2>성인<%=k %></h2>
            <table>
                <tbody>
                <tr>
                    <th>성별</th>
                    <td style="font-size: 18px;">
                        <input type="radio"	id="adultMale"	name="adultGender<%=k %>" value="M"><label for="adultMale">&nbsp;남자</label>&nbsp;&nbsp;&nbsp;
                        <input type="radio"	id="adultFemale"	name="adultGender<%=k %>" value="F"><label for="adultFemale">&nbsp;여자</label>
                    </td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>
                        <input type="text"	id="adultLastName" name="adultLastName" value="" placeholder="성(영문)" title="성"	style="width:35%; text-transform:none; ime-mode:disabled; font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;" />
                        <input type="text" id="adultFirstName" name="adultFirstName" value="" placeholder="이름" title="이름" style="width:55%; text-transform:none; ime-mode:disabled; font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;" />
                    </td>
                </tr>
                <tr>
                    <th>생년월일</th>
                    <td>
                        <select id="adultYear" name="adultYear" title="생년월일 연도" style="width: 30%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">년</option>
                            <option value="2021"> 2021</option>
                            <option value="2020"> 2020</option>
                            <option value="2019"> 2019</option>
                            <option value="2018"> 2018</option>
                            <option value="2017"> 2017</option>
                            <option value="2016"> 2016</option>
                            <option value="2015"> 2015</option>
                            <option value="2014"> 2014</option>
                            <option value="2013"> 2013</option>
                            <option value="2012"> 2012</option>
                            <option value="2011"> 2011</option>
                            <option value="2010"> 2010</option>
                            <option value="2009"> 2009</option>
                            <option value="2008"> 2008</option>
                            <option value="2007"> 2007</option>
                            <option value="2006"> 2006</option>
                            <option value="2005"> 2005</option>
                            <option value="2004"> 2004</option>
                            <option value="2003"> 2003</option>
                            <option value="2002"> 2002</option>
                            <option value="2001"> 2001</option>
                            <option value="2000"> 2000</option>
                            <option value="1999"> 1999</option>
                            <option value="1998"> 1998</option>
                            <option value="1997"> 1997</option>
                            <option value="1996"> 1996</option>
                            <option value="1995"> 1995</option>
                            <option value="1994"> 1994</option>
                            <option value="1993"> 1993</option>
                            <option value="1992"> 1992</option>
                            <option value="1991"> 1991</option>
                            <option value="1990"> 1990</option>
                            <option value="1989"> 1989</option>
                            <option value="1988"> 1988</option>
                            <option value="1987"> 1987</option>
                            <option value="1986"> 1986</option>
                            <option value="1985"> 1985</option>
                            <option value="1984"> 1984</option>
                            <option value="1983"> 1983</option>
                            <option value="1982"> 1982</option>
                            <option value="1981"> 1981</option>
                            <option value="1980"> 1980</option>
                            <option value="1979"> 1979</option>
                            <option value="1978"> 1978</option>
                            <option value="1977"> 1977</option>
                            <option value="1976"> 1976</option>
                            <option value="1975"> 1975</option>
                            <option value="1974"> 1974</option>
                            <option value="1973"> 1973</option>
                            <option value="1972"> 1972</option>
                            <option value="1971"> 1971</option>
                            <option value="1970"> 1970</option>
                            <option value="1969"> 1969</option>
                            <option value="1968"> 1968</option>
                            <option value="1967"> 1967</option>
                            <option value="1966"> 1966</option>
                            <option value="1965"> 1965</option>
                            <option value="1964"> 1964</option>
                            <option value="1963"> 1963</option>
                            <option value="1962"> 1962</option>
                            <option value="1961"> 1961</option>
                            <option value="1960"> 1960</option>
                        </select>
                        <select id="adultMonth" name="adultMonth" title="생년월일 월" style="width: 30%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">월</option>
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                        <select id="adultDay" name="adultDay" title="생년월일 일" style="width: 29.5%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">일</option>
                            <option value="01"> 01</option>
                            <option value="02"> 02</option>
                            <option value="03"> 03</option>
                            <option value="04"> 04</option>
                            <option value="05"> 05</option>
                            <option value="06"> 06</option>
                            <option value="07"> 07</option>
                            <option value="08"> 08</option>
                            <option value="09"> 09</option>
                            <option value="10"> 10</option>
                            <option value="11"> 11</option>
                            <option value="12"> 12</option>
                            <option value="13"> 13</option>
                            <option value="14"> 14</option>
                            <option value="15"> 15</option>
                            <option value="16"> 16</option>
                            <option value="17"> 17</option>
                            <option value="18"> 18</option>
                            <option value="19"> 19</option>
                            <option value="20"> 20</option>
                            <option value="21"> 21</option>
                            <option value="22"> 22</option>
                            <option value="23"> 23</option>
                            <option value="24"> 24</option>
                            <option value="25"> 25</option>
                            <option value="26"> 26</option>
                            <option value="27"> 27</option>
                            <option value="28"> 28</option>
                            <option value="29"> 29</option>
                            <option value="30"> 30</option>
                            <option value="31"> 31</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>회원번호<br><p style="font-size: 15px;">(탑승객)</p></th>
                    <td>
                        <select id="adultAirLine" name="adultAirLine" title="항공사" style="width: 40%;   font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;"">
                        <option value="SA" >남아프리카항공 - Voyager</option>
                        <option value="LH" >루프트한자 - Miles &amp; More</option>
                        <option value="SN" >브뤼셀항공 - Miles &amp; More</option>
                        <option value="LX" >스위스항공 - Miles&amp;More</option>
                        <option value="SK" >스칸디나비아항공 - EuroBonus</option>
                        <option value="ZH" >심천항공 - PhoenixMiles</option>
                        <option value="SQ" >싱가포르항공 - KrisFlyer</option>
                        <option value="AV" >아비앙카항공 - LifeMiles</option>
                        <option value="OZ" selected="selected">아시아나항공 - Asiana Club</option>
                        <option value="A3" >에게안항공 - Miles+Bonus</option>
                        <option value="BR" >에바항공 - Infinity Mileagelands</option>
                        <option value="CA" >에어 차이나 - PhoenixMiles</option>
                        <option value="NZ" >에어뉴질랜드 - Airpoints</option>
                        <option value="AI" >에어인디아 - FlyingReturns</option>
                        <option value="AC" >에어캐나다 - Aeroplan</option>
                        <option value="ET" >에티오피아항공 - Sheba Miles</option>
                        <option value="OS" >오스트리아항공 - Miles &amp; More</option>
                        <option value="UA" >유나이티드항공 - MileagePlus</option>
                        <option value="MS" >이집트항공 - EGYPTAIR Plus</option>
                        <option value="NH" >전일본공수(ANA) - ANA Mileage Club</option>
                        <option value="CM" >코파항공 - ConnectMiles</option>
                        <option value="OU" >크로아티아항공 - Miles &amp; More</option>
                        <option value="TG" >타이항공 - Royal Orchid Plus</option>
                        <option value="TP" >탑포르투갈 - TAP Miles &amp; Go</option>
                        <option value="TK" >터키항공 - Miles &amp; Smiles</option>
                        <option value="LO" >폴란드항공 - Miles &amp; More</option>
                        </select>
                        <input type="text" id="adultUserNum" name="adultUserNum" maxlength="9" title="" value="645246448" style="width:50%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;"" />
                    </td>
                </tr>
                </tbody>
            </table><br><br><br>
            <%
                    k++;
                }
            %>
            <%
                while(i <= children) {
            %>
            <h2>유아<%=i %></h2>
            <table>
                <tbody>
                <tr>
                    <th>성별</th>
                    <td style="font-size: 18px;">
                        <input type="radio"	id="childrenMale"	name="childrenMale" value="M"><label for="childrenMale">&nbsp;남자</label>&nbsp;&nbsp;&nbsp;
                        <input type="radio"	id="childrenFemale"	name="childrenFemale" value="F"><label for="childrenFemale">&nbsp;여자</label>
                    </td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>
                        <input type="text"	id="childrenLastName" name="childrenLastName" value="" placeholder="성(영문)" title="성"	style="width:35%; text-transform:none; ime-mode:disabled; font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;" />
                        <input type="text" id="childrenFirstName" name="childrenFirstName" value="" placeholder="이름" title="이름" style="width:55%; text-transform:none; ime-mode:disabled; font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;" />
                    </td>
                </tr>
                <tr>
                    <th>생년월일</th>
                    <td>
                        <select id="childrenYear" name="childrenYear" title="생년월일 연도" style="width: 30%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">년</option>
                            <option value="2021"> 2021</option>
                            <option value="2020"> 2020</option>
                            <option value="2019"> 2019</option>
                            <option value="2018"> 2018</option>
                            <option value="2017"> 2017</option>
                            <option value="2016"> 2016</option>
                            <option value="2015"> 2015</option>
                            <option value="2014"> 2014</option>
                            <option value="2013"> 2013</option>
                            <option value="2012"> 2012</option>
                            <option value="2011"> 2011</option>
                            <option value="2010"> 2010</option>
                            <option value="2009"> 2009</option>
                            <option value="2008"> 2008</option>
                            <option value="2007"> 2007</option>
                            <option value="2006"> 2006</option>
                            <option value="2005"> 2005</option>
                            <option value="2004"> 2004</option>
                            <option value="2003"> 2003</option>
                            <option value="2002"> 2002</option>
                            <option value="2001"> 2001</option>
                            <option value="2000"> 2000</option>
                            <option value="1999"> 1999</option>
                            <option value="1998"> 1998</option>
                            <option value="1997"> 1997</option>
                            <option value="1996"> 1996</option>
                            <option value="1995"> 1995</option>
                            <option value="1994"> 1994</option>
                            <option value="1993"> 1993</option>
                            <option value="1992"> 1992</option>
                            <option value="1991"> 1991</option>
                            <option value="1990"> 1990</option>
                            <option value="1989"> 1989</option>
                            <option value="1988"> 1988</option>
                            <option value="1987"> 1987</option>
                            <option value="1986"> 1986</option>
                            <option value="1985"> 1985</option>
                            <option value="1984"> 1984</option>
                            <option value="1983"> 1983</option>
                            <option value="1982"> 1982</option>
                            <option value="1981"> 1981</option>
                            <option value="1980"> 1980</option>
                            <option value="1979"> 1979</option>
                            <option value="1978"> 1978</option>
                            <option value="1977"> 1977</option>
                            <option value="1976"> 1976</option>
                            <option value="1975"> 1975</option>
                            <option value="1974"> 1974</option>
                            <option value="1973"> 1973</option>
                            <option value="1972"> 1972</option>
                            <option value="1971"> 1971</option>
                            <option value="1970"> 1970</option>
                            <option value="1969"> 1969</option>
                            <option value="1968"> 1968</option>
                            <option value="1967"> 1967</option>
                            <option value="1966"> 1966</option>
                            <option value="1965"> 1965</option>
                            <option value="1964"> 1964</option>
                            <option value="1963"> 1963</option>
                            <option value="1962"> 1962</option>
                            <option value="1961"> 1961</option>
                            <option value="1960"> 1960</option>
                        </select>
                        <select id="childrenMonth" name="childrenMonth" title="생년월일 월" style="width: 30%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">월</option>
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                        <select id="childrenDay" name="childrenDay" title="생년월일 일" style="width: 29.5%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">일</option>
                            <option value="01"> 01</option>
                            <option value="02"> 02</option>
                            <option value="03"> 03</option>
                            <option value="04"> 04</option>
                            <option value="05"> 05</option>
                            <option value="06"> 06</option>
                            <option value="07"> 07</option>
                            <option value="08"> 08</option>
                            <option value="09"> 09</option>
                            <option value="10"> 10</option>
                            <option value="11"> 11</option>
                            <option value="12"> 12</option>
                            <option value="13"> 13</option>
                            <option value="14"> 14</option>
                            <option value="15"> 15</option>
                            <option value="16"> 16</option>
                            <option value="17"> 17</option>
                            <option value="18"> 18</option>
                            <option value="19"> 19</option>
                            <option value="20"> 20</option>
                            <option value="21"> 21</option>
                            <option value="22"> 22</option>
                            <option value="23"> 23</option>
                            <option value="24"> 24</option>
                            <option value="25"> 25</option>
                            <option value="26"> 26</option>
                            <option value="27"> 27</option>
                            <option value="28"> 28</option>
                            <option value="29"> 29</option>
                            <option value="30"> 30</option>
                            <option value="31"> 31</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>회원번호<br><p style="font-size: 15px;">(탑승객)</p></th>
                    <td>
                        <select id="childrenAirLine" name="childrenAirLine" title="항공사" style="width: 40%;   font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;"">
                        <option value="SA" >남아프리카항공 - Voyager</option>
                        <option value="LH" >루프트한자 - Miles &amp; More</option>
                        <option value="SN" >브뤼셀항공 - Miles &amp; More</option>
                        <option value="LX" >스위스항공 - Miles&amp;More</option>
                        <option value="SK" >스칸디나비아항공 - EuroBonus</option>
                        <option value="ZH" >심천항공 - PhoenixMiles</option>
                        <option value="SQ" >싱가포르항공 - KrisFlyer</option>
                        <option value="AV" >아비앙카항공 - LifeMiles</option>
                        <option value="OZ" selected="selected">아시아나항공 - Asiana Club</option>
                        <option value="A3" >에게안항공 - Miles+Bonus</option>
                        <option value="BR" >에바항공 - Infinity Mileagelands</option>
                        <option value="CA" >에어 차이나 - PhoenixMiles</option>
                        <option value="NZ" >에어뉴질랜드 - Airpoints</option>
                        <option value="AI" >에어인디아 - FlyingReturns</option>
                        <option value="AC" >에어캐나다 - Aeroplan</option>
                        <option value="ET" >에티오피아항공 - Sheba Miles</option>
                        <option value="OS" >오스트리아항공 - Miles &amp; More</option>
                        <option value="UA" >유나이티드항공 - MileagePlus</option>
                        <option value="MS" >이집트항공 - EGYPTAIR Plus</option>
                        <option value="NH" >전일본공수(ANA) - ANA Mileage Club</option>
                        <option value="CM" >코파항공 - ConnectMiles</option>
                        <option value="OU" >크로아티아항공 - Miles &amp; More</option>
                        <option value="TG" >타이항공 - Royal Orchid Plus</option>
                        <option value="TP" >탑포르투갈 - TAP Miles &amp; Go</option>
                        <option value="TK" >터키항공 - Miles &amp; Smiles</option>
                        <option value="LO" >폴란드항공 - Miles &amp; More</option>
                        </select>
                        <input type="text" id="childrenUserNum" name="childrenUserNum" maxlength="9" title="" value="645246448" style="width:50%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;"" />
                    </td>
                </tr>
                </tbody>
            </table><br><br><br>
            <%
                    i++;
                }
            %>
            <%
                while(j <= infant) {
            %>
            <h2>영아<%=j %></h2>
            <table>
                <tbody>
                <tr>
                    <th>성별</th>
                    <td style="font-size: 18px;">
                        <input type="radio"	id="infantMale"	name="infantMale" value="M"><label for="infantMale">&nbsp;남자</label>&nbsp;&nbsp;&nbsp;
                        <input type="radio"	id="infantFemale"	name="infantFemale" value="F"><label for="infantFemale">&nbsp;여자</label>
                    </td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>
                        <input type="text"	id="infantLastName" name="infantLastName" value="" placeholder="성(영문)" title="성"	style="width:35%; text-transform:none; ime-mode:disabled; font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;" />
                        <input type="text" id="infantFirstName" name="infantFirstName" value="" placeholder="이름" title="이름" style="width:55%; text-transform:none; ime-mode:disabled; font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;" />
                    </td>
                </tr>
                <tr>
                    <th>생년월일</th>
                    <td>
                        <select id="infantYear" name="infantYear" title="생년월일 연도" style="width: 30%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">년</option>
                            <option value="2021"> 2021</option>
                            <option value="2020"> 2020</option>
                            <option value="2019"> 2019</option>
                            <option value="2018"> 2018</option>
                            <option value="2017"> 2017</option>
                            <option value="2016"> 2016</option>
                            <option value="2015"> 2015</option>
                            <option value="2014"> 2014</option>
                            <option value="2013"> 2013</option>
                            <option value="2012"> 2012</option>
                            <option value="2011"> 2011</option>
                            <option value="2010"> 2010</option>
                            <option value="2009"> 2009</option>
                            <option value="2008"> 2008</option>
                            <option value="2007"> 2007</option>
                            <option value="2006"> 2006</option>
                            <option value="2005"> 2005</option>
                            <option value="2004"> 2004</option>
                            <option value="2003"> 2003</option>
                            <option value="2002"> 2002</option>
                            <option value="2001"> 2001</option>
                            <option value="2000"> 2000</option>
                            <option value="1999"> 1999</option>
                            <option value="1998"> 1998</option>
                            <option value="1997"> 1997</option>
                            <option value="1996"> 1996</option>
                            <option value="1995"> 1995</option>
                            <option value="1994"> 1994</option>
                            <option value="1993"> 1993</option>
                            <option value="1992"> 1992</option>
                            <option value="1991"> 1991</option>
                            <option value="1990"> 1990</option>
                            <option value="1989"> 1989</option>
                            <option value="1988"> 1988</option>
                            <option value="1987"> 1987</option>
                            <option value="1986"> 1986</option>
                            <option value="1985"> 1985</option>
                            <option value="1984"> 1984</option>
                            <option value="1983"> 1983</option>
                            <option value="1982"> 1982</option>
                            <option value="1981"> 1981</option>
                            <option value="1980"> 1980</option>
                            <option value="1979"> 1979</option>
                            <option value="1978"> 1978</option>
                            <option value="1977"> 1977</option>
                            <option value="1976"> 1976</option>
                            <option value="1975"> 1975</option>
                            <option value="1974"> 1974</option>
                            <option value="1973"> 1973</option>
                            <option value="1972"> 1972</option>
                            <option value="1971"> 1971</option>
                            <option value="1970"> 1970</option>
                            <option value="1969"> 1969</option>
                            <option value="1968"> 1968</option>
                            <option value="1967"> 1967</option>
                            <option value="1966"> 1966</option>
                            <option value="1965"> 1965</option>
                            <option value="1964"> 1964</option>
                            <option value="1963"> 1963</option>
                            <option value="1962"> 1962</option>
                            <option value="1961"> 1961</option>
                            <option value="1960"> 1960</option>
                        </select>
                        <select id="infantMonth" name="infantMonth" title="생년월일 월" style="width: 30%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">월</option>
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                        <select id="infantDay" name="infantDay" title="생년월일 일" style="width: 29.5%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;">
                            <option value="">일</option>
                            <option value="01"> 01</option>
                            <option value="02"> 02</option>
                            <option value="03"> 03</option>
                            <option value="04"> 04</option>
                            <option value="05"> 05</option>
                            <option value="06"> 06</option>
                            <option value="07"> 07</option>
                            <option value="08"> 08</option>
                            <option value="09"> 09</option>
                            <option value="10"> 10</option>
                            <option value="11"> 11</option>
                            <option value="12"> 12</option>
                            <option value="13"> 13</option>
                            <option value="14"> 14</option>
                            <option value="15"> 15</option>
                            <option value="16"> 16</option>
                            <option value="17"> 17</option>
                            <option value="18"> 18</option>
                            <option value="19"> 19</option>
                            <option value="20"> 20</option>
                            <option value="21"> 21</option>
                            <option value="22"> 22</option>
                            <option value="23"> 23</option>
                            <option value="24"> 24</option>
                            <option value="25"> 25</option>
                            <option value="26"> 26</option>
                            <option value="27"> 27</option>
                            <option value="28"> 28</option>
                            <option value="29"> 29</option>
                            <option value="30"> 30</option>
                            <option value="31"> 31</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>회원번호<br><p style="font-size: 15px;">(탑승객)</p></th>
                    <td>
                        <select id="infantAirLine" name="infantAirLine" title="항공사" style="width: 40%;   font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;"">
                        <option value="SA" >남아프리카항공 - Voyager</option>
                        <option value="LH" >루프트한자 - Miles &amp; More</option>
                        <option value="SN" >브뤼셀항공 - Miles &amp; More</option>
                        <option value="LX" >스위스항공 - Miles&amp;More</option>
                        <option value="SK" >스칸디나비아항공 - EuroBonus</option>
                        <option value="ZH" >심천항공 - PhoenixMiles</option>
                        <option value="SQ" >싱가포르항공 - KrisFlyer</option>
                        <option value="AV" >아비앙카항공 - LifeMiles</option>
                        <option value="OZ" selected="selected">아시아나항공 - Asiana Club</option>
                        <option value="A3" >에게안항공 - Miles+Bonus</option>
                        <option value="BR" >에바항공 - Infinity Mileagelands</option>
                        <option value="CA" >에어 차이나 - PhoenixMiles</option>
                        <option value="NZ" >에어뉴질랜드 - Airpoints</option>
                        <option value="AI" >에어인디아 - FlyingReturns</option>
                        <option value="AC" >에어캐나다 - Aeroplan</option>
                        <option value="ET" >에티오피아항공 - Sheba Miles</option>
                        <option value="OS" >오스트리아항공 - Miles &amp; More</option>
                        <option value="UA" >유나이티드항공 - MileagePlus</option>
                        <option value="MS" >이집트항공 - EGYPTAIR Plus</option>
                        <option value="NH" >전일본공수(ANA) - ANA Mileage Club</option>
                        <option value="CM" >코파항공 - ConnectMiles</option>
                        <option value="OU" >크로아티아항공 - Miles &amp; More</option>
                        <option value="TG" >타이항공 - Royal Orchid Plus</option>
                        <option value="TP" >탑포르투갈 - TAP Miles &amp; Go</option>
                        <option value="TK" >터키항공 - Miles &amp; Smiles</option>
                        <option value="LO" >폴란드항공 - Miles &amp; More</option>
                        </select>
                        <input type="text" id="infantUserNum" name="infantUserNum" maxlength="9" title="" value="645246448" style="width:50%;  font-size: 18px; padding:5px; border:1px solid #bcbcbc; border-radius:3px;"" />
                    </td>
                </tr>
                </tbody>
            </table><br><br><br>
            <%
                    j++;
                }
            %>
            <button class="button-1" style="left:0%; background-color: #bcbcbc;" onclick="history.back()">이전</button>
            <button id="click" class="button-1" onclick="openPayment()">결제</button>

            <br><br><br><br><br><br>

        </div>
    </div>
</div>
<script src="js/number.js"></script>
</body>
</html>