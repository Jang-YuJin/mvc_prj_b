<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BANANAMILK</title>
<link rel="stylesheet" href="/mpro/asset/header.css">
<link rel="stylesheet" href="/mpro/asset/promotionImg.css">
<link rel="stylesheet" href="/mpro/asset/listImage.css">
<link rel="stylesheet" href="/mpro/asset/footerStyle.css">
</head>
<body>
<header>
		<div id="headerContainer">
			<div id="headerLogo"><a href="/mpro/shop/main.do"><img src="/mpro/asset/logo240x36B.png"></a></div>
			<div id="headerRight">
				<div id="headerMenu">
					<c:if test="${ id != null }"><a href="/mpro/member/logout.do">로그아웃</a> | </c:if>
						<c:if test="${ id == null }"><a href="/mpro/member/login.do?">로그인</a> | </c:if>
					<a href="/mpro/member/join.do">회원가입</a> | 
					<a href="/mpro/member/mypage.do">마이페이지</a> | 
					<a href="#">장바구니 | 
					<a href="#">주문조회</a></div>
				<div id="searchContainer">
					<form>
						<input id="searchBlock" type="text" name="search">
						<input id="searchBtn" type="submit" value="검색">
					</form>
				</div>
			</div>
		</div>
	</header>

	<nav>
		<ul id="navi">
			<li><a href="/mpro/shop/main.do">HOME</a></li>
			<li><a href="/mpro/shop/list.do?code=outer">OUTER</a></li>
			<li><a href="/mpro/shop/list.do?code=top">TOP</a></li>
			<li><a href="/mpro/shop/list.do?code=bottom">BOTTOM</a></li>
			<li><a href="/mpro/shop/list.do?code=bag">BAG</a></li>
			<li><a href="/mpro/shop/list.do?code=shoes">SHOES</a></li>
		</ul>
	</nav>

	<section>
		<a href="#"><div id="promotion"></div></a>
	</section>

	<section class="listImageContainer">
		<c:forEach var="i" begin="0" end="3">
			<div>
				<a href="/mpro/shop/detail.do?code=${ upper[i] }_${ sub[i] }_${ num[i] }"><div class="image" style="background-image: url('${ product[i].representative }')"></div></a>
				<a href="/mpro/shop/detail.do?code=${ upper[4 + i] }_${ sub[4 + i] }_${ num[4 + i] }"><div class="image" style="background-image: url('${ product[4 + i].representative }')"></div></a><!-- 4+i 해야할수도 -->
				<a href="/mpro/shop/detail.do?code=${ upper[8 + i] }_${ sub[8 + i] }_${ num[8 + i] }"><div class="image" style="background-image: url('${ product[8 + i].representative }')"></div></a>
			</div>
		</c:forEach>
	</section>

	<footer>
		<div id="intro">
			ⓒBANANAMILK<br> 
			대표자 : 장유진<br> 
			청주시 청원구 오창과학단지<br>
			043-1234-1234
		</div>
		<div id="info">
			영업시간<br> 
			평일 09:00 ~ 18:00<br> 
			토요일 09:00 ~ 15:00<br> 
			<a href="/mpro/seller/entry.do">입점</a>
		</div>
	</footer>
</body>
</html>