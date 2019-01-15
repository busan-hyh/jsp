<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<link rel="stylesheet" href="./css/style.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			//$().ready(); = jQuery().ready(); .은 메서드 체인이라고 부름
			//$(document).ready(function(){}); = $(function(){}) <-이렇게 줄여쓸 수 있다
			$(function(){
				//var input = $('input[name=uid]');name이 uid인 input 태그의 객체
				//input.focusout(function(){});이벤트함수, 핸들러가 들어감(익명함수) 이 두줄을 아래처럼 줄여쓸 수 있다
				$('input[name=uid]').focusout(function(){//여기의 this는 name이 uid인 input 태그 객체이다
					var tag = $(this); //this는 'input[name=uid]'
					var uid = tag.val();
					var url = './proc/checkUid.jsp?uid='+uid; //uid를 가져가야 하니까 uid변수를 파라미터로 가져감 
					
					$.ajax({ //전형적인 에이젝스 함수의 옵션들 4개
						url: url,
						type: 'get',
						dataType: 'json',
						success: function(data){
							if(data.result == 1){
								$('.resultId').css('color', 'red').text('이미 사용중');
								tag.focus();
							} else {
								$('.resultId').css('color', 'green').text('사용가능');
							}
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<div id="member">
			<section class="register">
				<form action="./proc/register.jsp" method="POST">
					<section>
						<table>
							<caption>사이트 이용정보 입력</caption>
							<tr>
								<td>아이디</td>
								<td>
									<input type="text" name="uid" placeholder="아이디를 입력" required />
									<span class="resultId"></span>
								</td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="pw1" placeholder="비밀번호를 입력" required /></td>
							</tr>
							<tr>
								<td>비밀번호확인</td>
								<td><input type="password" name="pw2" placeholder="비밀번호를 확인" required /></td>
							</tr>
						</table>
					</section>
					<section>
						<table>
							<caption>개인정보 입력</caption>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" placeholder="이름을 입력" required /></td>
							</tr>
							<tr>
								<td>별명</td>
								<td>
									<span class="info">공백없이 한글, 영문, 숫자만 입력가능</span>
									<div>
										<input type="text" name="nick" placeholder="별명을 입력" required />
									</div>
									<span class="resultNick"></span>
								</td>
							</tr>
							<tr>
								<td>EMAIL</td>
								<td><input type="email" name="email" placeholder="이메일을 입력" 	required /></td>
							</tr>
							<tr>
								<td>휴대폰</td>
								<td><input type="text" name="hp" placeholder="-포함 13자리를 입력" maxlength="13" required /></td>
							</tr>
							<tr>
								<td>주소</td>
								<td>
									<div>
										<input type="text" name="zip" id="zip" placeholder="우편번호" readonly />
										<button type="button" class="btnFind">주소검색</button>
									</div>
									<div>
										<input type="text" name="addr1" id="addr1" size="50" placeholder="주소를 검색하세요." readonly />
									</div>
									<div>
										<input type="text" name="addr2" id="addr2" size="50" placeholder="상세주소를 입력하세요." />
									</div>
								</td>
							</tr>
						</table>
			
					</section>
					<div>
						<a href="#" class="cancel">취소</a> 
						<input type="submit" class="join" value="회원가입" />
					</div>
				</form>
			</section>
		</div>
	</body>
</html>