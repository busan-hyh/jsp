<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String nick = request.getParameter("nick");
	
	final String HOST = "jdbc:mysql://192.168.0.126:3306/hyh";
	final String USER = "hyh";
	final String PASS = "1234";
	
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection conn = DriverManager.getConnection(HOST, USER, PASS);
	Statement stmt = conn.createStatement();
	
	ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `JSP_MEMBER` WHERE nick='"+nick+"'");
	
	int count = 0;
	
	if(rs.next()){
		count = rs.getInt(1);//rs의 첫번째 컬럼만 가져옴
	}
	
	rs.close();
	stmt.close();
	conn.close();
	
	// count를 JSON 데이터로 변환한다.
	//String json = "{result:"+count+"}"; 이렇게 만드는건 컬럼이 많아지면 쫌 불편쓰 제이슨 라이브러리를 쓰자!
	//simple json의 jar파일을 받아 lib폴더에 넣는다.
	//패키지 확인은 Java Resources에서 확인가능
	JSONObject json = new JSONObject();
	json.put("result",count);
	//json.put("name",count); 이런식으로 추가 가능
	
	out.println(json);//이녀석이 Ajax의 data가 된다.
	
%>