<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의내역 | BANANAMILK</title>
</head>
<body>
	<a href="/mpro/shop/main.do">bananamilk</a><br>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>상품명</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="inquiry" items="${ inquiry }">
			<tr>
				<td>${ inquiry.inquiry_id }</td>
				<td><a href="/mpro/inquiry/inquiryViewDetail.do?inquiry_id=${ inquiry.inquiry_id }">${ inquiry.title }</a></td>
				<td>${ inquiry.name }</td>
				<td>${ inquiry.write_date }</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${ begin != 1 }"><a href="/mpro/shop/detail.do?nowPage=${ begin - 1 }">[이전]</a></c:if>
		        <c:forEach begin="${ begin }" end="${ end }" var="page">
						<a href="/mpro/shop/detail.do?in_nowPage=${ page }">[ ${ page } ]</a>
		        </c:forEach>
		    <c:if test="${ end != lastPage }"><a href="/mpro/shop/detail.do?in_nowPage=${ end + 1 }">[다음]</a></c:if>
</body>
</html>