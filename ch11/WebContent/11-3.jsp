<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>3. 표현언어 연산자</h3>
	<%
		String str1 = null;//초기화
		String str2 = "";//빈 문자열
		String str3 = "Hello!";
		
		request.setAttribute("str1", str1);
		request.setAttribute("str2", str2);
		request.setAttribute("str3", str3);
		
		int num1 = 1;
		int num2 = 2;
		
		request.setAttribute("num1", num1);
		request.setAttribute("num2", num2);
	%>
	1. 문자열이 비어있는지 확인하는 empty연산자
	<ul>
		<li>${ empty str1 }</li>
		<li>${ empty str2 }</li>
		<li>${ empty str3 }</li>
	</ul>
	2. 문자열 비교 eq연산자
	<ul>
		<li>${ str3 eq "Hello!" }</li>
	</ul>
	3. 수학연산자 gt/ge/lt/le/eq/ne
	<ul>
		<li>> : gt ${ num1 gt num2 }</li>
		<li>>= : ge ${ num1 ge num2 }</li>
		<li>< : lt ${ num1 lt num2 }</li>
		<li><= : le ${ num1 le num2 }</li>
		<li>== : eq ${ num1 eq num2 }</li>
		<li>!= : ne ${ num1 ne num2 }</li>
	</ul>
</body>
</html>