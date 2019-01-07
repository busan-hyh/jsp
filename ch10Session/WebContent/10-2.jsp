<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>2. Session값 확인</h3>
	<%
		String name = (String)session.getAttribute("NAME");//기본형은 Object이므로 형변환
		
	%>
	<h4>세션 name : <%= name %></h4>
	
</body>
</html>