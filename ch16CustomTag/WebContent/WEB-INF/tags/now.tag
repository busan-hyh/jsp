<%@tag import="java.util.Calendar"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%
	Calendar cal = Calendar.getInstance();//new가 안됨. 프라이빗이므로. 싱글톤객체!
%>
<h4>현재날짜 : <%= cal.get(Calendar.YEAR) %>년 <%= cal.get(Calendar.MONTH)+1 %>월 <%= cal.get(Calendar.DATE) %>일</h4>