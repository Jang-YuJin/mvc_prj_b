<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>없어질 애들 잘 뽑혔나 확인용 페이지 나중에 삭제 예정</title>
</head>
<body>
	<table>
		<tr>
			<th>code</th>
			<th>name</th>
			<th>id</th>
		</tr>
		<c:forEach var="product" items="${ product }">
			<tr>
				<td>${ product.code }</td>
				<td>${ product.name }</td>
				<td>${ product.id }</td>
			</tr>
		</c:forEach>
	</table><br>
	<a href="/mpro/seller/seller_main.do">판매자 메인</a>	
</body>
</html>