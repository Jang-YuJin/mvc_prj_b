<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역 | BANANAMILK</title>
</head>
<body>
	<a href="/mpro/member/mypage.do">마이페이지 이동</a>
	<table>
		<tr>
			<th>상품명</th>
			<th>가격</th>
			<th>주문날짜</th>
			<th>구분</th>
		</tr>
		<c:forEach var="list" items="${ mylist }">
			<tr>
				<td>${ list.product_name }</td>
				<td>${ list.price }</td>
				<td>${ list.order_date }</td>
				<td>${ list.sort_name }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>