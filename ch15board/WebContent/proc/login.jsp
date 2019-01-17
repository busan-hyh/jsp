<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	
	String redirectUrl = null;
	
	Connection conn = DBConfig.getConnection();
	
	PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LOGIN);
	psmt.setString(1, uid);
	psmt.setString(2, pass);
	ResultSet rs = psmt.executeQuery(); //select문은 resultSet필요
	
	//5단계 세션저장
	if(rs.next()){
		//회원일 경우(쿼리참조!) 세션에 저장
		MemberVO vo = new MemberVO();//자동으로 SID라는 브라우저 식별값을 추가함.
		vo.setSeq(rs.getInt(1)); //public은 sql처럼 바로 불러올 수있지만 private는 set으로 불러온다.
		vo.setUid(rs.getString(2));
		vo.setPass(rs.getString(3));
		vo.setName(rs.getString(4));
		vo.setNick(rs.getString(5));
		vo.setEmail(rs.getString(6));
		vo.setHp(rs.getString(7));
		vo.setGrade(rs.getInt(8));
		vo.setZip(rs.getString(9));
		vo.setAddr1(rs.getString(10));
		vo.setAddr2(rs.getString(11));
		vo.setRegip(rs.getString(12));
		vo.setRdate(rs.getString(13));
		
		session.setAttribute("member", vo);
		
		redirectUrl = "../list.jsp";
	} else {
		//회원이 아닐 경우
		redirectUrl = "../login.jsp?result=fail";
	}
	
	rs.close();
	psmt.close();
	conn.close();
	
	response.sendRedirect(redirectUrl);
%>