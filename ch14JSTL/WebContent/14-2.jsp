<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 14-1에서 전송된 파라미터 수신
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String addr = request.getParameter("addr");
	String pos = request.getParameter("pos");
	String dep = request.getParameter("dep");
	
	//2. DB연결 후 쿼리실행
	final String HOST = "jdbc:mysql://192.168.0.156:3306/hyh";
	final String USER = "hyh";
	final String PASS = "1234";
	
	//	2-1. JDBC 드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	
	//	2-2. DB 접속
	Connection conn = DriverManager.getConnection(HOST, USER, PASS);
	
	//	2-3. 쿼리실행체 생성
	Statement stmt = conn.createStatement();
	
	//	2-4. 쿼리 실행
	String 	sql  = "INSERT INTO `USER` (uid, name, hp, addr, pos, dep, rdate) ";// )이후 한칸 띄우기!!
			sql += "VALUES ('"+uid+"', '"+name+"', '"+hp+"', '"+addr+"', '"+pos+"', "+dep+" , NOW());";//string은 '안에', 각 변수는 "+안에+"
	
	stmt.executeUpdate(sql);
			
	//	2-5. 결과셋 처리(SELECT 쿼리일 경우)
	// 여기선 필요없음. insert이므로
	
	//	2-6. DB 종료
	conn.close();
	
	//3. 리다이렉트
%>
<h2>등록완료</h2>