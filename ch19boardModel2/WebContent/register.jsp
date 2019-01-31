<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<link rel="stylesheet" href="/ch19boardModel2/css/style.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(function(){
				//필드의 키보드이벤트 인식해서 4자이상 쓸때마다 확인하기
				$('input[name=uid]').keyup(function(){
					//↑의 벨류 얻기
					var tag = $(this);
					var value = $(this).val();
					//console.log('입력값 : '+value);
					if(value.length >= 4){
						//console.log('실행!');
						var api = '/ch19boardModel2/member/usercheck.do?uid='+value;
						//ajax 통신이 있지만 지금 필요한건 json으로 받는것뿐이므로↓
						//제이슨 전용 통신함수 : $.getJSON('url', function(){}); 전형적인 콜백함수.. url로 콜/함수로 백
						//UserCheckService.java에게 콜하고 JSON을 백받는다. JAON은 (data)가 된다.
						$.getJSON(api, function(data){
							if(data.result == 1){
								$('.resultId').css('color', 'red').text('이미 사용중');
								isUidOk = false;
								tag.focus();
							} else {
								$('.resultId').css('color', 'green').text('사용가능');
								isUidOk = true;
							}
						});
					}
				});
			})
		</script>
	</head>
	<body>
		<div id="member">
		<section class="register">
		<form action="/ch19boardModel2/member/register.do" method="POST">
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
				<a href="/ch19boardModel2/member/login.do" class="cancel">취소</a> 
				<input type="submit" class="join" value="회원가입" />
			</div>
		</form>
		</section>
		</div>
	</body>
</html>