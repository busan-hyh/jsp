<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside_${ gr }.jsp"></jsp:include>
<div id="board">
	<h3>글보기</h3>
	<div class="view">
		<form action="#" method="post">
			<table>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="${ vo.title }" readonly />
					</td>
				</tr>
				<c:if test="${ vo.file } != 0">
				<tr>
					<td>첨부파일</td>
					<td>
						<a href="#">테스트.hwp</a>
						<span>3회 다운로드</span>
					</td>
				</tr>
				</c:if>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="content" rows="20" readonly>${ vo.content }</textarea>
					</td>
				</tr>
			</table>
			<div class="btns">
				<a href="/farmstory/board/delete.do?gr=${ gr }&cate=${ cate }&seq=${ vo.seq }" class="cancel del">삭제</a>
				<a href="/farmstory/board/modify.do?gr=${ gr }&cate=${ cate }&seq=${ vo.seq }" class="cancel mod">수정</a>
				<a href="/farmstory/board/list.do?gr=${ gr }&cate=${ cate }" class="cancel">목록</a>
			</div>
		</form>
	</div><!-- view 끝 -->
	
	<!-- 댓글리스트 -->
	<section class="comments">
		<h3>댓글목록</h3>
		
		<div class="comment">
			<span>
				<span>홍길동</span>
				<span>18-03-01</span>
			</span>
			<textarea>테스트 댓글입니다.</textarea>
			<div>
				<a href="#" class="del">삭제</a>
				<a href="#" class="mod">수정</a>
			</div>
		</div>
	
		<p class="empty">
			등록된 댓글이 없습니다.
		</p>
		
	</section>
	
	<!-- 댓글쓰기 -->
	<section class="comment_write">
		<h3>댓글쓰기</h3>
		<div>
			<form>
			<input type="hidden" name="parent" value="${ vo.seq }">
			<input type="hidden" name="uid" value="${ member.uid }">
			<input type="hidden" name="nick" value="${ member.nick }">
				<textarea name="comment" rows="5"></textarea>
				<div class="btns">
					<a href="#" class="cancel">취소</a>
					<input type="submit" class="submit" value="작성완료" />
				</div>
			</form>
		</div>
	</section>
	<!-- 댓글작성스크립트 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(function(){
			var btnComment = $('.comment_write .submit');//이번엔 추가하자마자 ajax를 통해 바로 추가되는 형식으로(페이지 새로고침이 아님)
			
			btnComment.click(function(){
				var parent	= $('.comment_write input[name=parent]').val();
				var uid		= $('.comment_write input[name=uid]').val();
				var nick	= $('.comment_write input[name=nick]').val();
				var content = $('.comment_write textarea').val();
				
				var json = {"parent":parent, "uid":uid, "nick":nick, "content":content};//json은 '가 아니라 " 반드시!!
				
				$.ajax({
					url:'/farmstory/board/comment.do',
					type:'POST',
					dataType:'json',//주로 json or xml(쇼핑몰)
					data:json,//POST타입을 전송할때 데이터는 data옵션에 넣음
					success:function(result){
						//방금쓴 json을 다시 받아옴(날짜를 더해서)
						console.log(result);
						var comments = $('.comments');
						var comment = $('.comments > .comment');//선택자 
						
						var commentCloned = comment.clone();
						commentCloned.find('span > .nick').text(result.nick);
						commentCloned.find('span > .date').text(result.date);
						commentCloned.find('textarea').text(result.content);
						comments.append(commentCloned);
					}
				});
				return false;//submit실행을 막기위해
			});
		});
	</script>
</div>
<!-- board 끝 -->

<!-- 내용끝 -->
</article>
</div>
</section>
<%@ include file="../_footer.jsp" %>  