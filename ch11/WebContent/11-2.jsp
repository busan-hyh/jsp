<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>2. 표현언어 기본객체</h3>
	<%
		pageContext.setAttribute("name", "김유신");
		request.setAttribute("name", "김춘추");
		session.setAttribute("name", "장보고");
		application.setAttribute("name", "이순신");
		//자주 쓰이는 set용 객체들
	%>
	
	<ul>
		<li>pageContext의 name : ${ pageScope.name }</li>
		<li>request의 name : ${ requestScope.name }</li>
		<li>session의 name : ${ sessionScope.name }</li>
		<li>application의 name : ${ applicationScope.name }</li>
		<li>requestScope와 sessionScope를 주로씀</li>
	</ul>
	
</body>
</html>