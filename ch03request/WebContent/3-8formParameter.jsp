<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="3-8request.jsp" method="post">
	<table>
		<tr>
			<td>이름 : </td>
			<td><input type="text" name="name" size="10" /></td>
		</tr>
		<tr>
			<td>주소 : </td>
			<td><input type="text" name="addr" size="30" /></td>
		</tr>
		<tr>
			<td>좋아하는 동물</td>
			<td>
			<input type="checkbox" name="pet" value="강아지" />강아지
			<input type="checkbox" name="pet" value="고양이" />고양이
			<input type="checkbox" name="pet" value="돼지" />돼지
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="전송" /></td>
		</tr>
	</table>
</form>
</body>
</html>