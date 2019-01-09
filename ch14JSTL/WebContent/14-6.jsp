<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String addr = request.getParameter("addr");
	String pos = request.getParameter("pos");
	String dep = request.getParameter("dep");

	final String HOST = "jdbc:mysql://192.168.0.156:3306/hyh";
	final String USER = "hyh";
	final String PASS = "1234";
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = null;
	PreparedStatement psmt = null;
	
	try{
		conn = DriverManager.getConnection(HOST, USER, PASS);
		
		//prepareStatement 준비된 쿼리, ?로 표시한다. 편리하므로 권장.
		String sql ="UPDATE `USER` SET name=?, hp=?, addr=?, pos=?, dep=? ";
			   sql+="WHERE uid=?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, name);
		psmt.setString(2, hp);
		psmt.setString(3, addr);
		psmt.setString(4, pos);
		psmt.setInt(5, Integer.parseInt(dep));//위에서 스트링으로 받아온 dep을 int로 바꾸는 방★법★★
		psmt.setString(6, uid);//↑하지만 여기선 그냥 String으로 해도 되긴함
		
		psmt.executeUpdate();
		
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		psmt.close();
		conn.close();
		response.sendRedirect("./14-3.jsp");
	}
	
	
	

%>