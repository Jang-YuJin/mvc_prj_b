<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의확인 | BANANAMILK</title>
</head>
<body>
	<table>
		<tr>
			<th>제목 : </th>
			<td>${ inquiry.title }</td>
		</tr>
		<tr>
			<th>내용 : </th>
			<td>${ inquiry.content }</td>
		</tr>
	</table>
	<c:if test="${fn:contains(id, 'member')}"><a href="/mpro/inquiry/member_inquiryView.do">문의내역으로 가기</a></c:if>
	<c:if test="${fn:contains(id, 'seller')}"><a href="/mpro/seller/inquiry.do">문의내역으로 가기</a></c:if>
</body>
</html>