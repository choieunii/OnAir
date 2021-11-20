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
    </style>
</head>
<body>
<div style="height:68px; background: #535F68; overflow:hidden;">
    <h2 style="color:#fff; padding:15px; font-weight:normal;">일반 결제</h2>
</div>
<div class="paymentBody" style="padding:15px;" >
    <h2>결제 정보</h2>
    <form method="post" action="#">
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
                    <label id="checkPoint">1,200,000won</label>&nbsp;&nbsp;&nbsp;
                    <button class="checkPoint">보유 금액 확인</button>
                </td>
            </tr>
            <tr>
                <th>결제 후 잔액</th>
                <td>
                    <label id="balance">200,000won</label>
                </td>
            </tr>
            </tbody>
        </table><br><br>
        <input type="submit" value="결제" style="position:absolute; left:20%;width:60%; border-radius: 5px;">
    </form>
</div>
</body>
</html>