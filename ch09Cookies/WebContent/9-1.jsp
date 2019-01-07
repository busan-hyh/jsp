<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>1. 쿠키</h3>
	<%
		Cookie ck1 = new Cookie("NAME", "홍길동"); //쿠키 생성
		ck1.setMaxAge(60*3); //쿠키의 수명 설정. 60초*3, 얼마나 보관할지.
		response.addCookie(ck1); //Client로 response에 실어보낸다
	%>
	<h4>쿠키생성, 클라이언트 전송</h4>
</body>
</html>