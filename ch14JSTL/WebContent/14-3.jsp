<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page import="sub1.USER" %>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//2. DB연결 후 쿼리실행 모~~두 외울것!!!
	final String HOST = "jdbc:mysql://192.168.0.156:3306/hyh";
	final String USER = "hyh";
	final String PASS = "1234";
	
	Connection conn = null;//finally에 쓸 conn 선언
	ArrayList<USER> list = new ArrayList<>();//테이블에 불러올 list 선언
	Statement stmt = null;//↓rs선언에 필요한 stmt 선언
	ResultSet rs = null;//finally에 쓸 rs 선언
	
	try{
	//2-1. JDBC 드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	
	//2-2. DB 접속
	conn = DriverManager.getConnection(HOST, USER, PASS);
	
	//2-3. 쿼리실행체 생성
	stmt = conn.createStatement();
	
	//2-4. 쿼리 실행
	//SELECT문일때는 executeQuery를 쓴다. SELECT만 ResultSet이 표시되므로 대입연산자가 필요.(콘솔이 필요한것처럼) 
	//그외 명령문은 executeUpdate를 쓴다.
	rs = stmt.executeQuery("SELECT * FROM `USER`;"); //rs객체가 불러온 데이터임!!
	
	//2-5. 결과셋 처리(SELECT 쿼리일 경우)★핵★심★
	//불러온 rs를 next()를 통해 각 컬럼을 자바빈으로 만든다.(자바빈 패키지 생성필요)
	//자바빈을 arrayList로 정렬한다. ..는 전역변수로 올렸음. 
		
	while(rs.next()){
		//자바빈 객체 생상
		USER user = new USER();
		//자바빈 객체에 넣을 rs 정보 설정
		user.setSeq(rs.getInt(1));
		user.setUid(rs.getString(2));
		user.setName(rs.getString(3));
		user.setHp(rs.getString(4));
		user.setAddr(rs.getString(5));
		user.setPos(rs.getString(6));
		user.setDep(rs.getInt(7));
		user.setRdate(rs.getString(8));
		//자바빈 객체리스트에 rs를 저장
		list.add(user);
	}
	
	} catch(Exception e) {
		e.printStackTrace();//1~5까지 에러가 뜨면 그 내용 출력
	} finally {
		//2-6. DB 종료
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
	<h3>직원목록</h3>
	<table border="1" style="border:1px solid; border-collapse: collapse;">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>휴대폰</th>
			<th>주소</th>
			<th>직급</th>
			<th>부서번호</th>
			<th>등록일</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<%
			for(USER user : list){ //USER형태의 user객체 가져올거임
		%>
		<tr>
			<td><%= user.getSeq() %></td>
			<td><%= user.getUid() %></td>
			<td><%= user.getName() %></td>
			<td><%= user.getHp() %></td>
			<td><%= user.getAddr() %></td>
			<td><%= user.getPos() %></td>
			<td><%= user.getDep() %></td>
			<td><%= user.getRdate().substring(2,10) %></td>
			<td><a href="./14-5.jsp?seq=<%= user.getSeq() %>">edit</a></td>
			<td><a href="./14-4.jsp?seq=<%= user.getSeq() %>">del</a></td>
		</tr>
		<% } %>
	</table>
	<a href="./14-1.jsp">직원 등록</a>
	
</body>
</html>