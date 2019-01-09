<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");

	final String HOST = "jdbc:mysql://192.168.0.156:3306/hyh";
	final String USER = "hyh";
	final String PASS = "1234";
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(HOST, USER, PASS);
	Statement stmt = conn.createStatement();
	
	String sql="DELETE FROM `USER` WHERE SEQ="+seq;
	stmt.executeUpdate(sql);
	
	stmt.close();
	conn.close();
	
	response.sendRedirect("./14-3.jsp");

%>