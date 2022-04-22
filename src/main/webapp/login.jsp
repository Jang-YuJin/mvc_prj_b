<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 | BANANAMILK</title>
</head>
<body>
	<a href="/mpro/shop/main.do">bananamilk</a>
	<form action="/mpro/member/loginConfirm.do" method="post">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="password"><br>
		<input type="submit" value="로그인"><br>
		<a href="/mpro/member/join.do">회원가입</a> 
	</form>
</body>
</html>