<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리 | BANANAMILK</title>
</head>
<body>
	<form action="/mpro/seller/product_del.do" method="post">
		<table>
			<tr>
				<th width=5%>삭제</th>
				<th width=10%>상품코드</th>
				<th width=10%>상품명</th>
				<th width=10%>가격</th>
				<th width=30%>대표이미지</th>
				<th width=30%>상세이미지</th>
				<th width=5%>수정</th>
			</tr>
			<c:forEach var="product" items="${ product }">
				<tr>
					<td width=5%><input type="checkbox" name="del" value="${ product.code }"></td>
					<td width=10%>${ product.code }</td>
					<td width=10%>${ product.name }</td>
					<td width=10%>${ product.price }</td>
					<td width=30%>${ product.representative }</td>
					<td width=30%>${ product.details }</td>
					<td width=5%><a href="/mpro/seller/product_mod.do?code=${ product.code }">수정</a></td>
				</tr>
			</c:forEach>
		</table><br>
		<input type="submit" value="삭제">
	</form><br>
	<a href="/mpro/seller/product_reg.do">상품등록</a><br>
	<c:if test="${ begin != 1 }"><a href="/mpro/seller/product_management.do?nowPage=${ begin - 1 }">[이전]</a></c:if>
	<c:forEach begin="${ begin }" end="${ end }" var="page">
		<a href="/mpro/seller/product_management.do?nowPage=${ page }">[ ${ page } ]</a>
	</c:forEach>
	<c:if test="${ end != lastPage }"><a href="/mpro/seller/product_management.do?nowPage=${ end + 1 }">[다음]</a></c:if>
	<br>
	<a href="/mpro/seller/seller_main.do">메인으로 이동</a>
</body>
</html>