<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>terms</title>
		<link rel="stylesheet" href="/ch19boardModel2/css/style.css" />		
	</head>

	<body>
		<div id="terms">
			<section>
			<table>
				<caption>사이트 이용약관</caption>
				<tr>
				<td>
					<textarea readonly>${vo.getTerms()}</textarea>
				<div>
					<label><input type="checkbox" name="chk1" />&nbsp;동의합니다.</label>        
				</div>
				</td>
				</tr>
			</table>
			</section>			
			<section>
			<table>
				<caption>개인정보 취급방침</caption>
				<tr>
				<td>
					<textarea readonly>${vo.privacy}</textarea>
				<div>
					<label><input type="checkbox" name="chk2" />&nbsp;동의합니다.</label>        
				</div>
				</td>
				</tr>
			</table>
			</section>
			
			<div>
				<a href="login.do" class="btnCancel">취소</a>
				<a href="register.do" class="btnNext">다음</a>
			</div>
			
		</div>
	</body>
</html>
