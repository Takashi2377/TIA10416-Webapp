<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.coupon.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  CouponVO couponVO = (CouponVO) request.getAttribute("couponVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�浧�u�f�� - listOneCoupon.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�浧�u�f�� - listOneCoupon.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�u�f��s��</th>
		<th>�u�f�X</th>
		<th>�إߤ��</th>
		<th>���Ĥ��</th>
		<th>�馩���B</th>
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