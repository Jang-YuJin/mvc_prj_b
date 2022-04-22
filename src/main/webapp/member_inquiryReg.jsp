<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의등록 | BANANAMILK</title>
</head>
<body>
	<form action="/mpro/inquiry/member_reg_c.do?code=${ code }" method="post">
		<table>
			<tr>
				<th>문의 제목 </th>
				<td><input type="text" name=title></td>
			</tr>
			<tr>
				<th>문의 내용 </th>
				<td><textarea name="content"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="등록">
	</form>
</body>
</html>