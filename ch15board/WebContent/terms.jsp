<%@page import="kr.co.board1.vo.TermsVO"%>
<%@page import="kr.co.board1.service.MemberService"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberService service = MemberService.getInstance();
	TermsVO vo = service.terms();
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
							<textarea readonly><%= vo.getTerms() %></textarea>
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
							<textarea readonly><%= vo.getPrivacy() %></textarea>
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