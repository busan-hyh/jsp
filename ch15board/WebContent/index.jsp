<%@page import="kr.co.board1.vo.MemberVO"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("member");
	if(true) {
		//로그인을 안했을때 : 세션확인
		pageContext.forward("./login.jsp");
	} else {
		pageContext.forward("./list.jsp");
	}
%>