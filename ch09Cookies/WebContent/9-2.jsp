<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>2. 쿠키값 확인하기</h3>
	<%
		Cookie[] cookies = request.getCookies();
	
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			String value = cookie.getValue();
			
			out.println(name + " : " + value + "<br>");
		}
	%>
</body>
</html>