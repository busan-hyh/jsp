<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>500에러발생 페이지</h3>
	<%
		String uid = request.getParameter("uid");
	%>
	uid : <%= uid.toString() %>
	▲null point 에러 발생
</body>
</html>