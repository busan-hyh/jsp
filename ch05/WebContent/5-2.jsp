<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>2. 내장객체 범위값 확인</h3>
	<%
		String pName = (String)pageContext.getAttribute("name");
		String rName = (String)request.getAttribute("name");
		String sName = session.getAttribute("name").toString();
		String aName = application.getAttribute("name").toString();
		//getAttribute는 Object이므로 String으로 형변환을 해야함
	%>
	<h4>pageContext name값 : <%= pName %></h4>
	<h4>request name값 : <%= rName %></h4>
	<h4>session name값 : <%= sName %></h4>
	<h4>application name값 : <%= aName %></h4>
</body>
</html>