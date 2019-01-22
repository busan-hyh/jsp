<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.co.board1.service.BoardService"%>
<%@page import="kr.co.board1.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//여기는 api페이지
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");
	BoardService service = BoardService.getInstance();
	ArrayList<BoardVO> comList = service.listComment(seq);
	//원래 view.jsp에 있던걸 ajax를 통해 비동기화하기 위해 따로 떼어놓음
	
	//gson.jar을 받아와서(라이브러리에 넣기)
	Gson gson = new Gson();
	String json = gson.toJson(comList);
	//json파일로 만들어 result를 만든다.
	
	out.print(json);
	
%>