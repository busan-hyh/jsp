<%@page import="kr.co.board1.service.MemberService"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	MemberService service = MemberService.getInstance();
	service.register(request);
	response.sendRedirect("../login.jsp?register=success");//레지스터 석세스 파라미터 들고가기
%>