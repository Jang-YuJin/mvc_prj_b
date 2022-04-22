<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>상세페이지 | BANANAMILK</title>
        <link rel="stylesheet" href="/mpro/asset/header.css">
        <link rel="stylesheet" href="/mpro/asset/shopDetail.css">
        <link rel="stylesheet" href="/mpro/asset/footerStyle.css">
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
    
	    <section id="top">
	        <div id="imgTitle" style="background-image: url('${ product[0].representative }')"></div>
	        
	        <div id="imgDesc">
	            <div id="productName">
	                ${ product[0].name }
	            </div>
	            <div id="productPrice">
	                ${ product[0].price }원
	            </div>
	            <div id="productBtn">
	                <a id="purchase" href="/mpro/member/order.do?code=${ product[0].code }"><div>구매하기</div></a>
	                <a  id="shoppingBasket" href="/mpro/member/cart.do?code=${ product[0].code }"><div>장바구니</div></a>
	            </div>
	        </div>
	    </section>
	
		<section id="imgDetail" style="background-image: url('${ product[0].details }')">
		</section>

		<section id="reviewContainer">
			<table class="tableList">
				<tr>
					<th colspan="3">리뷰</th>
				</tr>
				<tr>
					<th class="title">제목</th>
					<th class="writer">작성자</th>
					<th class="writeD">작성일</th>
				</tr>
				<c:forEach var="dto" items="${ review }">
					<tr>
						<td class="title"><a href="#">${ dto.title }</a></td>
						<td class="writer">${ dto.id }</td>
						<td class="writeD">${ dto.write_date }</td>
					</tr>
				</c:forEach>
			</table>
			<div>
			<c:if test="${ begin != 1 }"><a href="/mpro/shop/detail.do?nowPage=${ begin - 1 }&code=${ code }">[이전]</a></c:if>
		        <c:forEach begin="${ begin }" end="${ end }" var="page">
<%-- 			        <c:if test="${ nowPage == page}"> --%>
						<a href="/mpro/shop/detail.do?nowPage=${ page }&code=${ code }">[ ${ page } ]</a>
<%-- 					</c:if> --%>
<%-- 					<c:if test="${ nowPage !=  page}">
						<a href="/mpro/shop/list.do?nowPage=${ page }&code=${ code }">[ ${ page } ]</a>
					</c:if> --%>
		        </c:forEach>
		    <c:if test="${ end != lastPage }"><a href="/mpro/shop/detail.do?nowPage=${ end + 1 }&code=${ code }">[다음]</a></c:if>
			<%-- <div class="tablePage"><a class="preBtn" href="shopdetail.jsp?nowPage=${ nowPage - 1 }&code=${ code }">이전</a> ${ nowPage } / ${ lastPage } <a class="nextBtn" href="shopdetail.jsp?nowPage=${ nowPage + 1 }&code=${ code }">다음</a></div> --%>
			</div>
	        <a class="writeBtn" href="#">리뷰작성</a>
		</section>
		
		
		
		<section id="reviewContainer">
			<table class="tableList">
				<tr>
					<th colspan="3">문의</th>
				</tr>
				<tr>
					<th class="title">제목</th>
					<th class="writer">작성자</th>
					<th class="writeD">작성일</th>
				</tr>
				<c:forEach var="dto" items="${ inquiry }">
					<tr>
						<td class="title"><a href="#">${ dto.title }</a></td>
						<td class="writer">${ dto.id }</td>
						<td class="writeD">${ dto.write_date }</td>
					</tr>
				</c:forEach>
			</table>
			<div>
			<c:if test="${ in_begin != 1 }"><a href="/mpro/shop/detail.do?in_nowPage=${ in_begin - 1 }&code=${ code }">[이전]</a></c:if>
		        <c:forEach begin="${ in_begin }" end="${ in_end }" var="page">
						<a href="/mpro/shop/detail.do?in_nowPage=${ page }&code=${ code }">[ ${ page } ]</a>
		        </c:forEach>
		    <c:if test="${ in_end != in_lastPage }"><a href="/mpro/shop/detail.do?in_nowPage=${ in_end + 1 }&code=${ code }">[다음]</a></c:if>
			</div>
	        <a class="writeBtn" href="/mpro/inquiry/member_reg.do?code=${ product[0].code }">문의작성</a><!-- 여기로 넘어온 product에 대한 list는 1개밖에 없음 -->
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