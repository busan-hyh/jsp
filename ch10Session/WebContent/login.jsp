<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./loginProc.jsp" method="post">
	<table>
		<tr>
		<td>아이디</td>
		<td><input type="text" name="uid" size="10"></td>
		</tr>
		<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pass" size="10"></td>
		</tr>
		<tr>
		<td colspan="2" align="right"><input type="submit" value="로그인"></td>
		</tr>
	</table>
	</form>
</body>
</html>