<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 문의 관리 | BANANAMILK</title>
</head>
<body>
	<a href="/mpro/seller/seller_main.do">판매자 메인 이동</a><br>
	<form action="/mpro/seller/inquiry_answer.do">
		<table>
			<tr>
				<th>글번호</th>
				<th>상품코드</th>
				<th>상품명</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>답변하기</th>
			</tr>
			<c:forEach var="inquiry" items="${ inquiry }">
				<tr>
					<td>${ inquiry.inquiry_id }</td>
					<td>${ inquiry.code }</td>
					<td>${ inquiry.name }</td>
					<td><a href="/mpro/inquiry/inquiryViewDetail.do?inquiry_id=${ inquiry.inquiry_id }">${ inquiry.title }</a></td>
					<td>${ inquiry.id }</td>
					<td>${ inquiry.write_date }</td>
					<td><c:if test="${ inquiry.pi_id eq '0' }"><a href="/mpro/inquiry/inquiry_answerView.do?pi_id=${ inquiry.inquiry_id }&code=${ inquiry.code }">답변 </a></c:if></td><!-- pi_id로 inquiry_id를 넣어준 이유는 이걸 부모로 하는 답글을 쓰려고 -->
				</tr>
			</c:forEach>
		</table>
	</form>
	<div>
			<c:if test="${ in_begin != 1 }"><a href="/mpro/seller/inquiry.do?in_nowPage=${ in_begin - 1 }">[이전]</a></c:if>
		        <c:forEach begin="${ in_begin }" end="${ in_end }" var="page">
						<a href="/mpro/seller/inquiry.do?in_nowPage=${ page }">[ ${ page } ]</a>
		        </c:forEach>
		    <c:if test="${ in_end != in_lastPage }"><a href="/mpro/seller/inquiry.do?in_nowPage=${ in_end + 1 }">[다음]</a></c:if>
	</div>
</body>
</html>