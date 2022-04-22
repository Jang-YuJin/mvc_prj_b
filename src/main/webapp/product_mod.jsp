<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정 | bananamilk</title>
</head>
<body>
	<form action="/mpro/seller/product_mod_c.do" method="post">
		<table>
			<tr>
				<th>코드</th>
				<th>상품명</th>
				<th>판매자ID</th>
				<th>가격</th>
				<th>대표이미지</th>
				<th>상세이미지</th>
			</tr>
			<c:forEach var="product" items="${ product }">
				<tr>
					<td>${ product.code }</td>
					<td><input type="text" name="name" value="${ product.name }"></td>
					<td>${ product.id }</td>
					<td><input type="text" name="price" value="${ product.price }"></td>
					<td><input type="text" name="representative" value="${ product.representative }"></td>
					<td><input type="text" name="details" value="${ product.details }"></td>
				</tr>
				<input type="hidden" name="code" value="${ product.code }">
			</c:forEach>
		</table>
		<input type="submit" value="수정">
	</form>
</body>
</html>