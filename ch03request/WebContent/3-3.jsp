<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원 정보</h3>
	<%
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobby");
		String addr = request.getParameter("addr");
	%>
	<table border="1">
		<tr>
			<td>이름</td>
			<td><%= name %></td>
		</tr>
		<tr>
			<td>성별</td>
			<td><%= gender %></td>
		</tr>
		<tr>
			<td>취미</td>
			<td>
			<% for(String hobby : hobbies){ %>
			<%= hobby %>,
			<% } %>
			</td>
		</tr>
		<tr>
			<td>지역</td>
			<td><%= addr %></td>
		</tr>
	</table>
</body>
</html>