<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정 | BANANAMILK</title>
</head>
<body>
	<form action="/mpro/member/member_mod_c.do" method="post">
		<table>
			<tr>
				<th>아이디 </th>
				<td>${ member.id }</td>
			</tr>
			<tr>
				<th>이름 </th>
				<td><input type="text" name="name" value="${ member.name }"></td>
			</tr>
			<tr>
				<th>생년월일 </th>
				<td><input type="text" name="birth" value="${ member.birth }"></td>
			</tr>
			<tr>
				<th>전화번호 </th>
				<td><input type="text" name="tel" value="${ member.tel }"></td>
			</tr>
		</table>
		<input type="submit" value="수정">
	</form>
	<a href="/mpro/member/member_delte.do">회원탈퇴</a>
	<a href="/mpro/member/mypage.do">마이페이지로</a>
</body>
</html>