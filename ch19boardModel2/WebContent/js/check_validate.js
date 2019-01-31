//무결성 체크

$(function(){
	$('#regForm').submit(function(){//form을 submit할때 실행됨
		var uid = $('input[name=uid]').val();
		var pw1 = $('input[name=pw1]').val();
		var pw2 = $('input[name=pw2]').val();
		
		//중복체크
		if(!isUidOk){
			alert('아이디 이미 사용중');
			return false;
		}
		if(!isNickOk){
			alert('닉네임 이미 사용중');
			return false;
		}
		if(!isEmailOk){
			alert('이메일 이미 사용중');
			return false;
		}
		if(!isHpOk){
			alert('폰번호 이미 사용중');
			return false;
		}
		
		//아이디 최소 자리수 체크
		if(uid.length < 4){
			alert('아이디는 최소 4자 이상');
			return false;
		}
		//비번 최소 자리수 체크
		if(pw1.length < 4){
			
			return false;
		}
		//비번 일치여부 확인
		if(pw1 != pw2){
			alert('비밀번호가 일치하지 않');
			return false;
		}
		
		return true; //위의 if관문을 통과해야 데이터를 날린다. 
	});
});