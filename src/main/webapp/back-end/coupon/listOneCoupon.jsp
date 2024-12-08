<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.coupon.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  CouponVO couponVO = (CouponVO) request.getAttribute("couponVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>單筆優惠券 - listOneCoupon.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>單筆優惠券 - listOneCoupon.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>優惠券編號</th>
		<th>優惠碼</th>
		<th>建立日期</th>
		<th>有效日期</th>
		<th>折扣金額</th>
	</tr>
	<tr>
		<td><%=couponVO.getCop_id()%></td>
		<td><%=couponVO.getCop_code()%></td>
		<td><%=couponVO.getCrt_date()%></td>
		<td><%=couponVO.getEnd_date()%></td>
		<td><%=couponVO.getDiscount()%></td>
	</tr>
</table>

</body>
</html>