<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>2. JSTL Function 함수 라이브러리 태그</h3>
	<%
		String str = "Hello Korea";
		int idx = str.indexOf("e");//문자열가공을 위해 많이 씀 앞에서 e찾기(1)
		int ldx = str.lastIndexOf("e");//뒤에서 e찾기(9)
		int len = str.length();//문자열 길이(11)
		
		String r1 = str.substring(6,9);
		String r2 = str.replace("Korea", "Busan");
	%>
	<p>idx : <%= idx %> </p>
	<p>ldx : <%= ldx %> </p>
	<p>len : <%= len %> </p>
	<p>r1 : <%= r1 %> </p>
	<p>r2 : <%= r2 %> </p>
	
	<h4>Function 라이브러리 활용</h4>
	<c:set var="str" value="Hello Korea" />
	<p>idx : ${ fn:indexOf(str, "e") }</p>
	<p>len : ${ fn:length(str) }</p>
	<p>r1 : ${ fn:substring(str, 6,9) }</p>
	<p>r2 : ${ fn:replace(str, "Korea", "Busan") }</p>
	
</body>
</html>