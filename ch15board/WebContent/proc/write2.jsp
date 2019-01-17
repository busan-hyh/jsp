<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("member");
	request.setCharacterEncoding("UTF-8");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String uid = member.getUid();
	String regip = request.getRemoteAddr();
	
	Connection conn = DBConfig.getConnection();
	
	PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_BOARD);
	psmt.setString(1, subject);
	psmt.setString(2, content);
	psmt.setString(3, uid);
	psmt.setString(4, regip);
	psmt.executeUpdate();
	
	psmt.close();
	conn.close();
	
	response.sendRedirect("../list.jsp");
%>