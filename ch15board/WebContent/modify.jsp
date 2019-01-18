<%@page import="kr.co.board1.vo.BoardVO"%>
<%@page import="kr.co.board1.service.BoardService"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardService service = BoardService.getInstance();
	BoardVO vo = service.view(request);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>글수정</title> 
	<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div id="board">
	<h3>글쓰기</h3>
	<div class="write">
	<form action="./proc/modify.jsp" method="post">
		<!-- post타입은 파라미터전송을 못하므로 히든으로 심어준다. -->
		<inpyt type="hidden" name="seq" value="<%= vo.getSeq() %>" />
		<table>
			<tr>
			<td>제목</td>
			<td><input type="text" name="subject" placeholder="제목을 입력하세요." required value="<%= vo.getTitle() %>" /></td>
			</tr>				
			<tr>
			<td>내용</td>
			<td>
				<textarea name="content" rows="20" required><%= vo.getContent() %></textarea>
			</td>
			</tr>
			<tr>
			<td>첨부</td>
			<td>
				<input type="file" name="file" />
			</td>
			</tr>
		</table>
		<div class="btns">
			<a href="./view.jsp?<%= vo.getSeq() %>" class="cancel">취소</a>
			<input type="submit" class="submit" value="수정완료" />
		</div>
	</form>
	</div>
	</div>
</body>
</html>