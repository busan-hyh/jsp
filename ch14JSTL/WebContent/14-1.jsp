<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>직원 등록</h3>
	<form action="./14-2.jsp" method="get">
	<table border="1" style="border:1px solid; border-collapse: collapse;">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="uid" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="hp" maxLength="13" /></td>
		</tr>
		<tr>
			<td>지역</td>
			<td>
				<select name="addr">
					<option>서울</option>
					<option>구리</option>
					<option>광주</option>
					<option>전주</option>
					<option>부산</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>부서</td>
			<td>
				<select name="pos">
					<option value="101">경영지원부</option>
					<option value="102">인사부</option>
					<option value="103">개발부</option>
					<option value="104">영업1부</option>
					<option value="105">영업2부</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="등록하기" /></td>
		</tr>
	</table>
	</form>
</body>
</html>