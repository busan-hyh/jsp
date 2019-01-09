<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="sub1.USER" %>
<%
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");
	
	final String HOST = "jdbc:mysql://192.168.0.156:3306/hyh";
	final String USER = "hyh";
	final String PASS = "1234";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	USER user = new USER();//try>if안에 있으면 못쓰므로 여기로 뺀다.
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(HOST, USER, PASS);
		stmt = conn.createStatement();
		
		String sql = "SELECT * FROM `USER` WHERE seq="+seq;
		rs = stmt.executeQuery(sql);
		
		if(rs.next()){//한번만 반복하므로 while이 아니라 if를 권장
			user.setSeq(rs.getInt(1));
			user.setUid(rs.getString(2));
			user.setName(rs.getString(3));
			user.setHp(rs.getString(4));
			user.setAddr(rs.getString(5));
			user.setPos(rs.getString(6));
			user.setDep(rs.getInt(7));
			user.setRdate(rs.getString(8));
			
		}
		
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		rs.close();
		stmt.close();
		conn.close();
	}
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>직원 수정</h3>
	<form action="./14-6.jsp" method="get">
	<table border="1" style="border:1px solid; border-collapse: collapse;">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="uid" readonly value="<%= user.getUid() %>" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="<%= user.getName() %>"/></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="hp" maxLength="13" value="<%= user.getHp() %>"/></td>
		</tr>
		<tr>
			<td>지역</td>
			<td>
				<select name="addr">
					<option>서울</option>
					<option>구리</option>
					<option>광주</option>
					<option>전주</option>
					<option>부산</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>직급</td>
			<td>
				<select name="pos">
					<option>사장</option>
					<option>이사</option>
					<option>부장</option>
					<option>팀장</option>
					<option>대리</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>부서</td>
			<td>
				<select name="dep">
					<option value="101">경영지원부</option>
					<option value="102">인사부</option>
					<option value="103">개발부</option>
					<option value="104">영업1부</option>
					<option value="105">영업2부</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="수정하기" /></td>
		</tr>
	</table>
	</form>
</body>
</html>