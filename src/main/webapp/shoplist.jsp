<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록페이지 | BANANAMILK</title>
<link rel="stylesheet" href="/mpro/asset/header.css">
<link rel="stylesheet" href="/mpro/asset/listImage.css">
<link rel="stylesheet" href="/mpro/asset/footerStyle.css">
<link rel="stylesheet" href="/mpro/asset/verticalMenu.css">
</head>
<body>
	<header>
		<div id="headerContainer">
			<div id="headerLogo"><a href="/mpro/shop/main.do"><img src="/mpro/asset/logo240x36B.png"></a></div>
			<div id="headerRight">
				<div id="headerMenu">
					<c:if test="${ id != null }"><a href="/mpro/member/logout.do">로그아웃</a> | </c:if>
						<c:if test="${ id == null }"><a href="/mpro/member/login.do">로그인</a> | </c:if>
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

	<div id="content">
        <section id="vMenu">
            <div>
                <div><a class="upper" href="/mpro/shop/list.do?code=outer">OUTER</a></div>
                <ul>
                    <li><a class="sub" href="/mpro/shop/list.do?code=outer_jk">자켓</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=outer_ct">코트</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=outer_pd">패딩</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=outer_jp">점퍼</a></li>
                </ul>
            </div>
            <div>
                <div><a class="upper" href="/mpro/shop/list.do?code=top">TOP</a></div>
                <ul>
                    <li><a class="sub" href="/mpro/shop/list.do?code=top_ss">반팔</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=top_ls">긴팔</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=top_hd">후드티</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=top_mm">맨투맨</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=top_sh">셔츠</a></li>
                </ul>
            </div>
            <div>
                <div><a class="upper" href="/mpro/shop/list.do?code=bottom">BOTTOM</a></div>
                <ul>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bottom_dn">데님</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bottom_sl">슬랙스</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bottom_sp">숏팬츠</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bottom_sk">스커트</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bottom_jg">조거</a></li>
                </ul>
            </div>
            <div>
                <div><a class="upper" href="/mpro/shop/list.do?code=bag">BAG</a></div>
                <ul>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bag_bp">백팩</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bag_cb">크로스백</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bag_ec">에코백</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bag_sb">숄더백</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=bag_td">토드백</a></li>
                </ul>
            </div>
            <div>
                <div><a class="upper" href="/mpro/shop/list.do?code=shoes">SHOES</a></div>
                <ul>
                    <li><a class="sub" href="/mpro/shop/list.do?code=shoes_sn">운동화</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=shoes_lp">로퍼</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=shoes_bt">부츠</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=shoes_sd">샌들</a></li>
                    <li><a class="sub" href="/mpro/shop/list.do?code=shoes_hl">구두</a></li>
                </ul>
            </div>
        </section>
        
        <section id="imgContainer">
            <div class="listImageContainer">
            	<c:forEach var="i" begin="1" end="4" step="1">
	                <div class="listImageDContainer">
		                <c:forEach var="k" begin="1" end="3" step="1">
			                    <c:if test="${ product[(i - 1) * 3 + k - 1].code eq null }">
			                    	<c:set var="doLoop" value="true"></c:set>
			                    </c:if>
		                	<c:if test="${ not doLoop }">
			                    <a class="product" href="/mpro/shop/detail.do?code=${ product[(i - 1) * 3 + k - 1].code }">
			                        <div class="imageD" style="background-image: url('${ product[(i - 1) * 3 + k - 1].representative }')"></div>
			                        <span class = "imageTextContainer">
				                        <div class="imageText">${ product[(i - 1) * 3 + k - 1].name }</div>
				                        <div class="imageText">${ product[(i - 1) * 3 + k - 1].price }원</div>
			                        </span>
			                    </a>
		                    </c:if>
		                </c:forEach>
		            </div>
		        </c:forEach>
		        <c:if test="${ begin != 1 }"><a href="/mpro/shop/list.do?nowPage=${ begin - 1 }&code=${ code }">[이전]</a></c:if>
		        <c:forEach begin="${ begin }" end="${ end }" var="page">
<%-- 			        <c:if test="${ nowPage == page}"> --%>
						<a href="/mpro/shop/list.do?nowPage=${ page }&code=${ code }">[ ${ page } ]</a>
<%-- 					</c:if> --%>
<%-- 					<c:if test="${ nowPage !=  page}">
						<a href="/mpro/shop/list.do?nowPage=${ page }&code=${ code }">[ ${ page } ]</a>
					</c:if> --%>
		        </c:forEach>
		        <c:if test="${ end != lastPage }"><a href="/mpro/shop/list.do?nowPage=${ end + 1 }&code=${ code }">[다음]</a></c:if>
		       <%--  <div class="listPage"><a class="preBtn" href="shoplist.jsp?nowPage=${ nowPage - 1 }&upperCategory=${ upper }&subCategory=${ sub }">이전</a> ${ nowPage } / ${ lastPage } <a class="nextBtn" href="shoplist.jsp?nowPage=${ nowPage + 1 }&upperCategory=${ upper }&subCategory=${ sub }">다음</a></div> --%>
		    </div>
        </section>
    </div>

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