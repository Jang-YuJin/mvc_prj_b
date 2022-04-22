<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입점 | BANANAMILK</title>
 <link rel="stylesheet" href="/mpro/asset/join.css">
</head>
<body>
	<header>
		<div id="logo">
			<a href="/mpro/shop/main.do"><img src="/mpro/asset/logo240x36B.png"></a>
		</div>
	</header>
		<form method="post" action="/mpro/seller/entryConfirm.do"><!-- 여기 mpro 지워야 할수도 있을 듯############### -->
			<div>대표자</div>
			<input class="name" type="text" name="name">
			<div>전화번호</div>
			<input class="tel" type="text" name="tel">
			<div>주소</div>
			<input class="address" type="text" name="address">
			<div>회사명</div>
			<input class="name" type="text" name="com_name">
			<div>사업자번호</div>
			<input class="birth" type="text" name="cr_no">-
			<input class="birth" type="text" name="cr_no">-
			<input class="birth" type="text" name="cr_no">
			<div class="agree">
                <div class="agreeTitle">약관동의</div>
                <textarea readonly>약관동의 내용 Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusantium, necessitatibus dolorum tenetur reprehenderit facilis modi sed pariatur sequi sapiente consequatur, facere, assumenda temporibus impedit rerum libero obcaecati cupiditate hic labore?</textarea>
            </div>
			<input type="submit" value="입점신청">
		</form>
</body>
</html>