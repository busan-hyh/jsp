<%@page import="kr.co.board1.service.BoardService"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardService service = BoardService.getInstance();
	service.delete(request);
	
	response.sendRedirect("../list.jsp");
%>