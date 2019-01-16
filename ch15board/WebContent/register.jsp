<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<link rel="stylesheet" href="./css/style.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="./js/check_validate.js"></script>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		    function execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수
		
		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
		
		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    
		                } else {
		                    
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('zip').value = data.zonecode;
		                document.getElementById("addr1").value = addr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("addr2").focus();
		            }
		        }).open();
		    }
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
									<input type="text" name="nick" placeholder="별명을 입력" required />
									<span class="resultNick"></span>
								</td>
							</tr>
							<tr>
								<td>EMAIL</td>
								<td>
								<input type="email" name="email" placeholder="이메일을 입력" 	required />
								<span class="resultMail"></span>
								</td>
							</tr>
							<tr>
								<td>휴대폰</td>
								<td>
								<input type="text" name="hp" placeholder="-포함 13자리를 입력" maxlength="13" required />
								<span class="resultHp"></span>
								</td>
							</tr>
							<tr>
								<td>주소</td>
								<td>
									<div>
										<input type="text" name="zip" id="zip" placeholder="우편번호" readonly />
										<button type="button" class="btnFind" onclick="execDaumPostcode()" >주소검색</button>
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