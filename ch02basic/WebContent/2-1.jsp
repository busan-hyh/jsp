<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title</title>
</head>
<body>
	<h3>1. JAVA 조건문</h3>
	<%
		//▼코드 블럭: 서버에서(톰캣) 실행한다.
		int num1 = 1;
		int num2 = 2;
		int num3 = 3;
		int num4 = 4;
		//▼디자인 블럭, html 블럭: 클라이언트에서(브라우저) 실행한다.
	%>
	<h4>if</h4>
	<%
		if(num1 < num2){//◀하드코딩된 스태틱 태그
			out.println("<i>num1이 num2보다 작다</i>");//◀ 서버에서 생성한 태그, 다이내믹 태그
		}
	%>
	<h4>if~else</h4>
	<% if(num1 > num2) { %>
		<i>num1이 num2보다 크다</i>
	<% } else { %>
		<i>num1이 num2보다 작다</i>
	<% } %>
	<h4>if~else if</h4>
	<% if(num1 > num2) { %>
		<i>num1이 num2보다 크다</i>
	<% } else if(num2 > num3) { %>
		<i>num2가 num3보다 크다</i>
	<% } else if(num3 > num4) { %>
		<i>num3이 num4보다 크다</i>
	<% } else { %>
		<i>num4가 가장 크다</i>
	<% } %>
</body>
</html>