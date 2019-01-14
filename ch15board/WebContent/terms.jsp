<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//2. DB연결 후 쿼리실행 모~~두 외울것!!!
	final String HOST = "jdbc:mysql://192.168.0.126:3306/hyh";
	final String USER = "hyh";
	final String PASS = "1234";
	
	//2-1. JDBC 드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	
	//2-2. DB 접속
	Connection conn = DriverManager.getConnection(HOST, USER, PASS);
	
	//2-3. 쿼리실행체 생성
	Statement stmt = conn.createStatement();
	
	//2-4. 쿼리 실행
	//SELECT문일때는 executeQuery를 쓴다. SELECT만 ResultSet이 표시되므로 대입연산자가 필요.(콘솔이 필요한것처럼) 
	//그외 명령문은 executeUpdate를 쓴다.
	ResultSet rs = stmt.executeQuery("SELECT * FROM `JSP_TERMS`;"); //rs객체가 불러온 데이터임!!
	
	//2-5. 데이터 설정
	String terms = null;
	String privacy = null;
	if(rs.next()){
		terms = rs.getString(1);
		privacy = rs.getString(2);
	}
	
	//2-6. 서버닫기
	rs.close();
	stmt.close();
	conn.close();
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>terms</title>
		<link rel="stylesheet" href="./css/style.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			//$(document).ready(function(){});//익명함수, 핸들러
			$(document).ready(function(){
				$('.btnNext').click(function(){//선택자함수.중에서 버튼이벤트, 클릭함수. 콜백(예전엔 onClick썼음)
					var rs1 = $('input[name=chk1]').is(':checked');//속성 선택자. chk1 is checked면 true
					var rs2 = $('input[name=chk2]').is(':checked');
					if(!rs1){
						alert('이용약관에 동의하세요');
						return false;//안넘어가게
					} else if(!rs2) {
						alert('개인정보약관에 동의하세요');
						return false;
					} else { return true; }
				});
			});
		</script>	
	</head>

	<body>
		<div id="terms">
			<section>
				<table>
					<caption>사이트 이용약관</caption>
					<tr>
						<td>
							<textarea readonly><%= terms %></textarea>
							<div>
								<label><input type="checkbox" name="chk1" />&nbsp;동의합니다.</label>        
							</div>
						</td>
					</tr>
				</table>
			</section>			
			<section>
				<table>
					<caption>개인정보 취급방침</caption>
					<tr>
						<td>
							<textarea readonly><%= privacy %></textarea>
							<div>
								<label><input type="checkbox" name="chk2" />&nbsp;동의합니다.</label>        
							</div>
						</td>
					</tr>
				</table>
			</section>
			
			<div>
				<a href="./index.jsp" class="btnCancel">취소</a>
				<a href="./register.jsp" class="btnNext">다음</a>
			</div>
			
		</div>
	</body>
</html>