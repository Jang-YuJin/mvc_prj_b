<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록 | bananamilk</title>
</head>
<body>
	<form action="/mpro/seller/product_regInfo.do" method="post">
		<table>
			<tr>
				<th>코드</th>
				<th>상품명</th>
				<th>가격</th>
				<th>대표이미지</th>
				<th>상세이미지</th>
			</tr>
			<tr>
				<td>
					<select name="code">
						<option value="outer_jk">아우터_자켓</option>
						<option value="outer_ct">아우터_코트</option>
						<option value="outer_pd">아우터_패딩</option>
						<option value="outer_jp">아우터_점퍼</option>
						<option value="top_ss">상의_반팔</option>
						<option value="top_ls">상의_긴팔</option>
						<option value="top_hd">상의_후드</option>
						<option value="top_mm">상의_맨투맨</option>
						<option value="top_sh">상의_셔츠</option>
						<option value="bottom_dn">하의_데님</option>
						<option value="bottom_sl">하의_슬랙스</option>
						<option value="bottom_sp">하의_반바지</option>
						<option value="bottom_sk">하의_치마</option>
						<option value="bottom_jg">하의_조거</option>
						<option value="bag_bp">가방_백팩</option>
						<option value="bag_cb">가방_크로스백</option>
						<option value="bag_ec">가방_에코백</option>
						<option value="bag_sb">가방_숄더백</option>
						<option value="bag_td">가방_토드백</option>
					</select>
				</td>
				<td><input type="text" name="name"></td>
				<td><input type="text" name="price"></td>
				<td><input type="text" name="representative"></td>
				<td><input type="text" name="details"></td>
			</tr>
		</table>
		<input type="submit" value="등록">
	</form>
</body>
</html>