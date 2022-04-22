<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 답변 등록 | BANANAMILK</title>
</head>
<body>
	<form action="/mpro/inquiry/inquiry_answer.do" method="post">
		<table>
			<tr>
				<th>답변 제목 </th>
				<td><input type="text" name=title></td>
			</tr>
			<tr>
				<th>답변 내용 </th>
				<td><textarea name="content"></textarea></td>
			</tr>
		</table>
		<input type="hidden" name="pi_id" value="${ pi_id }">
		<input type="hidden" name="code" value="${ code }">
		<input type="submit" value="등록">
	</form>
</body>
</html>