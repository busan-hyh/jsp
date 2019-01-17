<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();//세션값 삭제
	response.sendRedirect("../login.jsp");
%>