<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside_${ gr }.jsp"></jsp:include>
<div id="board">
	<h3>글수정</h3>
	<div class="write">
		<form action="/farmstory/board/modify.do?gr=${ gr }&cate=${ cate }&seq=${ vo.seq }" method="post">
			<table>
				<tr>
					<td>제목</td>
					<input type="hidden" name="gr" value="${ gr }" />
					<input type="hidden" name="cate" value="${ cate }" />
						<td><input type="text" name="subject" required value="${ vo.title }"/></td>
				</tr>				
				<tr>
					<td>내용</td>
					<td>
						<textarea name="content" rows="20" required>${ vo.content }</textarea>
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
				<a href="/farmstory/board/list.do?gr=${ gr }&cate=${ cate }" class="cancel">취소</a>
				<input type="submit" class="submit" value="작성완료" />
			</div>
		</form>
	</div>
</div>
</article>
</div>
</section>
<%@ include file="../_footer.jsp" %>  