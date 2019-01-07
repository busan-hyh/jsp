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
	<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	
	if(uid.equals("abcd") && pass.equals("1234")){//string 비교는 .equals 임!! == 이 아니라!!!
		User user = new User();
		user.setUid("abcd");
		user.setPass("1234");
		user.setName("홍길동");
	
		session.setAttribute("user", user);//세션지정
		response.sendRedirect("./loginResult.jsp?result=success");
	} else {
		response.sendRedirect("./loginResult.jsp?result=fail");
	}
	%>
	
</body>
</html>