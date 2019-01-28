<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>1. JTSL 기초</h3>
	<%	//변수선언
		String str = "Hello~";
		out.println("<p>"+str+"</p>");
	%>
	<c:set var="str" value="Hello~" />
	<p><b>${ str }</b></p>
	
	<%	//if문
		int num1 = 1;
		int num2 = 2;
		
		if (num1 < num2){
			out.println("<p>num1이 num2보다 작다</p>");
		}
	%>
	<c:set var="num1" value="1"/>
	<c:set var="num2" value="2"/>
	<c:if test="${ num1 lt num2 }">
		<p><b>num1이 num2보다 작다.</b> test는 조건</p>
	</c:if>	
	<%	//if-else문
		if (num1 > num2){
			out.println("<p>num1이 num2보다 크다</p>");
		} else {
			out.println("<p>num1이 num2보다 작다</p>");
		}
	%>
	<c:choose>
		<c:when test="${ num1 gt num2 }">
			<p><b>num1이 num2보다 크다</b></p>
		</c:when>
		<c:otherwise>
			<p><b>num1이 num2보다 작다</b></p>
		</c:otherwise>
	</c:choose>
	<%	//for문
		for(int i=1; i<=5; i++){
			out.println("<p>Hello JSTL!</p>");
		}
	%>
	<c:forEach begin="1" end="5">
		<p><b>Hello JSTL!</b></p>
	</c:forEach>
	<%	//for문에 변수k선언
		int sum = 0;
		for(int k=1; k<=10; k++){
			sum += k;
		}
		out.println("<p>1~10까지 합 "+sum+"</p>");
	%>
	<c:set var="sum" value="0" />
	<c:forEach var="k" begin="1" end="10">
		<c:set var="sum" value="${ sum + k }" />
	</c:forEach>
	<p><b>1~10까지 합 ${ sum }</b></p>
</body>
</html>