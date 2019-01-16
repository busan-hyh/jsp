<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pw1");
	String name = request.getParameter("name");
	String nick = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp = request.getParameter("hp");
	String zip = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr();//브라우저 아이피

	final String HOST = "jdbc:mysql://192.168.0.126:3306/hyh";
	final String USER = "hyh";
	final String PASS = "1234";
	
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection conn = DriverManager.getConnection(HOST, USER, PASS);
	
	String  sql  = "INSERT INTO `JSP_MEMBER` SET ";
	sql += "uid=?,";
	sql += "pass=PASSWORD(?),"; //암호형식 MD5 으로 저장 
	sql += "name=?,";
	sql += "nick=?,";
	sql += "email=?,";
	sql += "hp=?,";
	sql += "zip=?,";
	sql += "addr1=?,";
	sql += "addr2=?,";
	sql += "regip=?,";
	sql += "rdate=NOW()";
	/*String sql = "INSERT INTO `JSP_MEMBER` (seq, uid, pass, name, nick, email, hp, regip, rdate) ";
		sql += "VALUES ('"+uid+"', '"+pass+"', '"+name+"', '"+nick+"','"+email+"','"+hp+"','"+regip+"', NOW());";*/
	//String sql = "INSERT INTO `USER` SET uid='2', name='3', hp='4', addr='5', pos='6', dep='7'";

	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, uid);
	psmt.setString(2, pass);
	psmt.setString(3, name);
	psmt.setString(4, nick);
	psmt.setString(5, email);
	psmt.setString(6, hp);
	psmt.setString(7, zip);
	psmt.setString(8, addr1);
	psmt.setString(9, addr2);
	psmt.setString(10, regip);
	psmt.executeUpdate();
	
	psmt.close();
	conn.close();
	
	response.sendRedirect("../login.jsp?register=success");//레지스터 석세스 파라미터 들고가기
%>