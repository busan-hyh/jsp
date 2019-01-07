<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>2. 파라미터 출력</h3>
	<%-- useBean태그를 이용한 파라미터 수신 --%>
	<jsp:useBean id="user" class="ch08.User">
		<jsp:setProperty name="user" property="name" />
		<jsp:setProperty name="user" property="gender" />
		<jsp:setProperty name="user" property="hobby" />
		<jsp:setProperty name="user" property="addr" />
	</jsp:useBean>
	<table border="1">
		<tr>
			<td>이름</td>
			<td><jsp:getProperty name="user" property="name" /></td>
		</tr>
		<tr>
			<td>성별</td>
			<td><jsp:getProperty name="user" property="gender" /></td>
		</tr>
		<tr>
			<td>취미</td>
			<td><jsp:getProperty name="user" property="hobby" /></td>
		</tr>
		<tr>
			<td>지역</td>
			<td><jsp:getProperty name="user" property="addr" /></td>
		</tr>
	</table>
</body>
</html>