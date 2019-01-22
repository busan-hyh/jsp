<%@page import="kr.co.board1.service.BoardService"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("parent");

	BoardService service = BoardService.getInstance();
	service.delete(request);
	//service.deleteHit(request);
	
	response.sendRedirect("../view.jsp?seq="+seq);
%>