<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>2. 파라미터 수신</h3>
	<i>파라미터 : url/경로(uri).jsp?파라미터(uid=~~&pass=~~~형식) = get 방식</i>
	<i>파라미터가 안보이는 방식 : post 방식</i>
	<%
		String uid = request.getParameter("uid");
		String pass = request.getParameter("pass");
	%>◀스크립트
	<ul>
		<li>아 이 디 : <%= uid %></li>
		<li>비밀번호 : <%= pass %></li>◀표현식
	</ul>
	<a href="./3-1.jsp">로그인하기</a>
</body>
</html>