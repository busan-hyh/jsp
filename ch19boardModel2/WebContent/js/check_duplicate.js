//id, nick, mail, hp 중복체크
//
var isUidOk = false;
var isNickOk = false;
var isEmailOk = false;
var isHpOk = false;
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
					isUidOk = false;
					tag.focus();
				} else {
					$('.resultId').css('color', 'green').text('사용가능');
					isUidOk = true;
				}
			}
		});
	});
	//닉네임 중복체크
	$('input[name=nick]').focusout(function(){
		var tag = $(this);
		var nick = tag.val();
		
		$.ajax({
			url: './proc/checkNick.jsp?nick='+nick,
			type: 'get',
			dataType: 'json',
			success: function(data){
				if(data.result == 1){
					$('.resultNick').css('color', 'red').text('이미 사용중');
					isNickOk = false;
					tag.focus();
				} else {
					$('.resultNick').css('color', 'green').text('사용가능');
					isNickOk = true;
				}
			}
		});
	});
	
	//메일 중복체크
	$('input[name=email]').focusout(function(){
		var tag = $(this);
		var email = tag.val();
		
		$.ajax({
			url: './proc/checkMail.jsp?email='+email,
			type: 'get',
			dataType: 'json',
			success: function(data){
				if(data.result == 1){
					$('.resultMail').css('color', 'red').text('이미 사용중');
					isEmailOk = false;
					tag.focus();
				} else {
					$('.resultMail').css('color', 'green').text('사용가능');
					isEmailOk = true;
				}
			}
		});
	});
	//폰 중복체크
	$('input[name=hp]').focusout(function(){
		var tag = $(this);
		var hp = tag.val();
		
		$.ajax({
			url: './proc/checkHp.jsp?hp='+hp,
			type: 'get',
			dataType: 'json',
			success: function(data){
				if(data.result == 1){
					$('.resultHp').css('color', 'red').text('이미 사용중');
					isHpOk = false;
					tag.focus();
				} else {
					$('.resultHp').css('color', 'green').text('사용가능');
					isHpOk = true;
				}
			}
		});
	});
});