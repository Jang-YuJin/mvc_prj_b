<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 | BANANAMILK</title>
    <link rel="stylesheet" href="/mpro/asset/join.css">
</head>
<body>
	<header>
		<div id="logo">
			<a href="/mpro/shop/main.do"><img src="/mpro/asset/logo240x36B.png"></a>
		</div>
	</header>
	
	<section>
		<div id="title">회원가입</div>
		<form method="post" action="/mpro/member/joinConfirm.do"><!-- 여기 mpro 지워야 할수도 있을 듯############### -->
			<div>이름</div>
			<input class="name" type="text" name="name">
			<div>아이디</div>
			<input class="id" type="text" name="id">
			<div>비밀번호</div>
			<input type="password" name="pw">
			<div>비밀번호 확인</div>
			<input type="password" name="pw_ch">
			<div>전화번호</div>
			<input class="tel" type="text" name="tel">
			<div>주소</div>
			<input class="address" type="text" name="address">
			<div>생년월일</div>
			<input class="birth" type="text" name="birth" placeholder="년">
			<input class="birth" type="text" name="birth" placeholder="월">
			<input class="birth" type="text" name="birth" placeholder="일">
			<div class="agree">
                <div class="agreeTitle">약관동의</div>
                <textarea readonly>약관동의 내용 Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusantium, necessitatibus dolorum tenetur reprehenderit facilis modi sed pariatur sequi sapiente consequatur, facere, assumenda temporibus impedit rerum libero obcaecati cupiditate hic labore?</textarea>
            </div>
			<input type="submit" value="가입">
		</form>
	</section>
</body>
</html>