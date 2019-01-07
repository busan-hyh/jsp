<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sub1.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>로그인 결과</h3>
	<%
		request.setCharacterEncoding("UTF-8");
		String result = request.getParameter("result");
		
		if(result.equals("success")){
			User user = (User)session.getAttribute("user");
	%>

		<p><%= user.getName() %>님 환영합니다</p>
		<a href="./logout.jsp">로그아웃</a>
	<%
		} else {
	%>
		<p>해당 회원이 없습니다.</p>
	<% } %>
</body>
</html>