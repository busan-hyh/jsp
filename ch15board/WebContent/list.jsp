<%@page import="kr.co.board1.service.BoardService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.board1.vo.BoardVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.board1.vo.MemberVO"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("member");//세션에 있는 MemberVO를 불러온다 
	if(member == null){//널체크를 안하면 널포인트에러가 뜬다
		pageContext.forward("./login.jsp");//리다이렉트는 페이지가 넘어가기전에 로딩이 되므로 에러가 뜬다
	}
	
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");
	
	BoardService service = BoardService.getInstance();
	
	//1. LIMIT용 int 
	int start = service.getLimitStart(pg);
	
	//2. 페이지번호 계산(하단에 뜨는 페이지번호)
	int total = service.getTotal();
	int pgEnd = service.getPageEnd(total);
	
	//글 카운터 번호 계산(글번호)
	int count = total-start;
	
	//3. 페이지 그룹 계산(10개씩 뜨도록)(groupStart와 groupEnd가 둘 다 쓰이지만 return은 1개만 되므로 배열을 써보자!)
	int[] groupStartEnd = service.getPageGroupStartEnd(pg, pgEnd);
	
	//리스트VO
	Connection conn = DBConfig.getConnection();
	
	PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LIST);
	psmt.setInt(1, start);//LIMIT용 int 
	ResultSet rs = psmt.executeQuery();
	
	ArrayList<BoardVO> boardList = new ArrayList<>();
	
	while(rs.next()){
		BoardVO vo = new BoardVO();
		vo.setSeq(rs.getInt(1));
		vo.setParent(rs.getInt(2));
		vo.setComment(rs.getInt(3));
		vo.setCate(rs.getString(4));
		vo.setTitle(rs.getString(5));
		vo.setContent(rs.getString(6));
		vo.setFile(rs.getInt(7));
		vo.setHit(rs.getInt(8));
		vo.setUid(rs.getString(9));
		vo.setRegip(rs.getString(10));
		vo.setRdate(rs.getString(11));
		vo.setNick(rs.getString("nick"));//숫자로 순서를 표시해도 되고 컬럼명을 지정해도 됨
		
		boardList.add(vo);
		}
	
	rs.close();
	psmt.close();
	conn.close();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글목록</title> 
		<link rel="stylesheet" href="./css/style.css" />
	</head>
	<body>
		<div id="board">
			<h3>글목록</h3>
			<!-- 리스트 -->
			<div class="list">
				<p class="logout"><%= member.getNick() %>님! 반갑습니다. <a href="./proc/logout.jsp">[로그아웃]</a><p>
				<table>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>글쓴이</td>
						<td>날짜</td>
						<td>조회</td>
					</tr>

					<%
						for(BoardVO vo : boardList) {
					%>
					<tr>
						<td><%= count-- %></td>
						<td><a href="./view.jsp?seq=<%= vo.getSeq() %>"><%= vo.getTitle() %></a>&nbsp;[<%= vo.getComment() %>]</td>
						<td><%= vo.getNick() %></td>
						<td><%= vo.getRdate().substring(2,10) %></td>
						<td><%= vo.getHit() %></td>
					</tr>
					<% } %>
				</table>
			</div>
			<!-- 페이징 -->
			<nav class="paging">
				<span> 
				<% if(groupStartEnd[0] > 1) { %>
				<a href="./list.jsp?pg=<%= groupStartEnd[0]-1 %>" class="prev">이전</a>
				<% } %>
				<% for(int current=groupStartEnd[0]; current<=groupStartEnd[1]; current++) { %>
				<a href="./list.jsp?pg=<%= current %>" class="num"><%= current %></a>
				<% } %>
				<% if(groupStartEnd[1] < pgEnd) { %>
				<a href="./list.jsp?pg=<%= groupStartEnd[1]+1 %>" class="next">다음</a>
				<% } %>
				</span>
			</nav>
			<a href="./write.jsp" class="btnWrite">글쓰기</a>
		</div>
	</body>

</html>

