<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 | BANANAMILK</title>
</head>
<body>
	<a href="/mpro/member/mypage.do">마이페이지 이동</a>
	<form action="/mpro/member/cart_order.do" method="post">
		<table>
			<tr>
				<th></th>
				<th>상품명</th>
				<th>가격</th>
			</tr>
			<c:forEach var="list" items="${ mylist }">
				<tr>
					<td><input type="checkbox" name="order" value="${ list.mylist_id }"></td>
					<td>${ list.product_name }</td>
					<td>${ list.price }</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="선택주문">
	</form>
</body>
</html>